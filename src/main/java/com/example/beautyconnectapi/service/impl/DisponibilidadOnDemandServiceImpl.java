package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.model.dto.horarioDisponible.HorariosDisponiblesResponse;
import com.example.beautyconnectapi.model.entity.JornadaLaboral;
import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.repository.JornadaLaboralRepository;
import com.example.beautyconnectapi.repository.ProfesionalServicioRepository;
import com.example.beautyconnectapi.repository.TurnoRepository;
import com.example.beautyconnectapi.service.DisponibilidadOnDemandService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DisponibilidadOnDemandServiceImpl implements DisponibilidadOnDemandService {

    private final JornadaLaboralRepository jornadaRepo;
    private final TurnoRepository turnoRepo;
    private final ProfesionalServicioRepository profServRepo;

    // Activa/desactiva logs fácilmente
    private static final boolean DEBUG = true;

    @Override
    public HorariosDisponiblesResponse generarIniciosDisponibles(Long profId, Long servicioId, LocalDate fecha, int stepMin) {
        // 1) obtener duración desde ProfesionalServicio
        var ps = profServRepo.findByProfesional_IdAndServicio_Id(profId, servicioId)
                .orElseThrow(() -> new EntityNotFoundException("Relación Profesional-Servicio no encontrada"));
        int duracion = ps.getDuracion();
        if (duracion <= 0) throw new IllegalArgumentException("Duración de servicio inválida");

        // 2) franjas de jornada para el día
        var franjas = jornadaRepo.findAllByProfesional_IdAndDiaAndActivoTrue(profId, fecha.getDayOfWeek());
        if (franjas.isEmpty()) {
            if (DEBUG) {
                System.out.println("[DEBUG] generarIniciosDisponibles()");
                System.out.println("  fecha=" + fecha + " dow=" + fecha.getDayOfWeek());
                System.out.println("  duracion=" + duracion + " stepMin=" + stepMin);
                System.out.println("  franjas=0 -> no hay jornada laboral activa ese día");
            }
            return HorariosDisponiblesResponse.builder()
                    .fecha(fecha).duracionMin(duracion).inicios(List.of()).build();
        }

        // 3) turnos ya reservados del día (cualquier servicio)
        var ocupados = turnoRepo.findAllByProfesionalServicio_Profesional_IdAndFecha(profId, fecha);

        // DEBUG: estado de entrada
        if (DEBUG) {
            System.out.println("[DEBUG] generarIniciosDisponibles()");
            System.out.println("  fecha=" + fecha + " dow=" + fecha.getDayOfWeek());
            System.out.println("  profId=" + profId + " servicioId=" + servicioId);
            System.out.println("  duracion=" + duracion + " stepMin=" + stepMin);

            System.out.println("  franjas=" + franjas.size());
            for (var f : franjas) {
                System.out.println("    - " + f.getDia() + " " + f.getHoraInicio() + " -> " + f.getHoraFin() + " activo=" + f.getActivo());
            }

            System.out.println("  turnos=" + ocupados.size());
            for (var t : ocupados) {
                var psTurno = t.getProfesionalServicio();
                int d = (psTurno != null && psTurno.getDuracion() != null) ? psTurno.getDuracion() : 0;
                int start = toMin(t.getHora());
                int end = start + d;
                System.out.println("    - turno: " + t.getHora() + " -> " + toHHmm(end) + " (dur=" + d + "m)");
            }
        }

        // 4) restar ocupados a la jornada → bloques libres
        List<Rango> jornada = franjasToRangos(franjas);
        List<Rango> busy = turnosToRangos(ocupados);

        // BYPASS DE PRUEBA (para descartar problemas en subtract):
        // List<Rango> libres = new ArrayList<>(jornada); // <- descomenta esto SOLO para probar
        List<Rango> libres = subtract(jornada, busy);     // <- lógica real

        if (DEBUG) {
            System.out.println("  jornada(min)=" + jornadaToStr(jornada));
            System.out.println("  busy(min)=" + jornadaToStr(busy));
            System.out.println("  libres(min)=" + jornadaToStr(libres));
        }

        // 5) generar inicios cada 'stepMin' que permitan completar 'duracion'
        List<String> inicios = new ArrayList<>();
        for (Rango r : libres) {
            for (int t = r.start; t + duracion <= r.end; t += stepMin) {
                inicios.add(toHHmm(t));
            }
        }

        if (DEBUG) {
            System.out.println("  inicios=" + inicios.size() + (inicios.isEmpty() ? "" : " -> " + inicios.subList(0, Math.min(10, inicios.size())) + (inicios.size() > 10 ? " ..." : "")));
        }

        return HorariosDisponiblesResponse.builder()
                .fecha(fecha)
                .duracionMin(duracion)
                .inicios(inicios)
                .build();
    }

    // ===== helpers =====
    private record Rango(int start, int end) {} // [start, end) en minutos

    private static int toMin(LocalTime t) { return t.getHour() * 60 + t.getMinute(); }
    private static String toHHmm(int m) { return String.format("%02d:%02d", m / 60, m % 60); }

    private static List<Rango> franjasToRangos(List<JornadaLaboral> franjas) {
        return franjas.stream()
                .map(f -> new Rango(toMin(f.getHoraInicio()), toMin(f.getHoraFin())))
                .sorted(Comparator.comparingInt(r -> r.start))
                .toList();
    }

    private static List<Rango> turnosToRangos(List<Turno> turnos) {
        List<Rango> res = new ArrayList<>();
        for (Turno t : turnos) {
            // Caso típico: Turno tiene LocalDate fecha y LocalTime hora (inicio)
            // y la duración está en el ProfesionalServicio asociado al turno.
            var ps = t.getProfesionalServicio();
            if (ps == null || ps.getDuracion() == null) continue; // o lanzar error si corresponde

            int start = toMin(t.getHora());    // ajusta el getter si tu campo se llama distinto
            int end   = start + ps.getDuracion();
            res.add(new Rango(start, end));
        }
        // ordenar por inicio
        res.sort(Comparator.comparingInt(r -> r.start));
        return res;
    }

    /** resta intervalos: base = libres (jornada), rest = ocupados (turnos) */
    private static List<Rango> subtract(List<Rango> base, List<Rango> rest) {
        List<Rango> result = new ArrayList<>(base);
        for (Rango o : rest) {
            List<Rango> next = new ArrayList<>();
            for (Rango b : result) {
                // sin superposición
                if (o.end <= b.start || o.start >= b.end) {
                    next.add(b);
                    continue;
                }
                // recortes
                if (o.start > b.start) next.add(new Rango(b.start, Math.min(o.start, b.end)));
                if (o.end   < b.end)   next.add(new Rango(Math.max(o.end, b.start), b.end));
            }
            result = next;
        }
        // limpiar vacíos
        return result.stream().filter(r -> r.end > r.start).toList();
    }

    // Utilidad para logs legibles
    private static String jornadaToStr(List<Rango> xs) {
        if (xs == null || xs.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < xs.size(); i++) {
            Rango r = xs.get(i);
            sb.append("(").append(toHHmm(r.start)).append(" -> ").append(toHHmm(r.end)).append(")");
            if (i < xs.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
