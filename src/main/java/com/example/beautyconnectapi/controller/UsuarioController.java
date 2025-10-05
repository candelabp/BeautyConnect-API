package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.config.firebase.FirebaseRoleService;
import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.entity.Auth;
import com.example.beautyconnectapi.model.enums.Rol;
import com.example.beautyconnectapi.service.ClienteService;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import com.example.beautyconnectapi.service.UsuarioService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final PrestadorDeServicioService prestadorServicioService;

    // Beans inyectados (definidos en tu FirebaseConfig nuevo)
    private final FirebaseAuth firebaseAuth;
    private final FirebaseRoleService roleService;

    @PostMapping("/save")
    public UsuarioResponseDTO saveUsuario(@Valid @RequestBody UsuarioDTO usuarioDto) {
        return usuarioService.saveUsuario(usuarioDto);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioResponseDTO getUsuarioById(@PathVariable Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId);
    }

    // ✅ Lecturas deben ser GET
    @GetMapping("/obtenerPorEmail/{mail}")
    public UsuarioResponseDTO findByMail(@PathVariable String mail) {
        return usuarioService.findByMail(mail);
    }

    @GetMapping("/existePorUid/{uid}")
    public boolean existsByUid(@PathVariable String uid) {
        return usuarioService.existsByUid(uid);
    }

    // ✅ Baja lógica: mejor usar DELETE (si es soft delete, podés dejar PATCH pero semánticamente no es ideal)
    @PatchMapping("/delete/{usuarioId}")
    public UsuarioResponseDTO deleteUsuario(@PathVariable Long usuarioId) {
        return usuarioService.deleteUsuario(usuarioId);
    }

    /**
     * Registro por email/password (idToken viene del login de Firebase en el front)
     * Recomendación: NO confiar en request.getRol(); definilo según el flujo.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Auth request) {
        try {
            // 1) Verificar ID token
            FirebaseToken decoded = firebaseAuth.verifyIdToken(request.getIdToken());
            String uid = decoded.getUid();
            String mail = request.getMail() != null ? request.getMail() : decoded.getEmail();

            if (mail == null) {
                return ResponseEntity.badRequest().body("Email no disponible en token ni en request");
            }

            // 2) Evitar duplicados
            if (usuarioService.existsByMail(mail) || usuarioService.existsByUid(uid)) {
                // devolver entidad asociada al rol existente
                UsuarioResponseDTO usuario = usuarioService.findByMail(mail);
                return ResponseEntity.ok(resolveEntidadPorRol(usuario));
            }

            // 3) Determinar rol (SUGERENCIA: decidilo en backend)
            Rol rol = request.getRol() != null ? request.getRol() : Rol.CLIENTE;

            // 4) Setear custom claim
            roleService.asignarRol(uid, rol.name());

            // 5) Persistir usuario
            UsuarioDTO usuarioDto = new UsuarioDTO(mail, rol, uid);

            // 6) Crear entidad según rol
            return ResponseEntity.ok(crearEntidadPorRol(rol, usuarioDto, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Registro con Google (idToken de Google via Firebase)
     */
    @PostMapping("/google")
    public ResponseEntity<?> registerWithGoogle(@RequestBody Auth request) {
        try {
            FirebaseToken decoded = firebaseAuth.verifyIdToken(request.getIdToken());
            String uid = decoded.getUid();
            String mail = decoded.getEmail();

            if (mail == null) {
                return ResponseEntity.badRequest().body("Email de Google no disponible en el token");
            }

            if (usuarioService.existsByMail(mail) || usuarioService.existsByUid(uid)) {
                UsuarioResponseDTO usuario = usuarioService.findByMail(mail);
                return ResponseEntity.ok(resolveEntidadPorRol(usuario));
            }

            // Definí rol por defecto o por flujo de UI (NO confiar ciegamente en request.rol)
            Rol rol = request.getRol() != null ? request.getRol() : Rol.CLIENTE;

            roleService.asignarRol(uid, rol.name());

            UsuarioDTO usuarioDto = new UsuarioDTO(mail, rol, uid);

            return ResponseEntity.ok(crearEntidadPorRol(rol, usuarioDto, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en registro con Google: " + e.getMessage());
        }
    }

    // ----------------- helpers privados -----------------

    private Object crearEntidadPorRol(Rol rol, UsuarioDTO usuarioDto, Auth request) {
        switch (rol) {
            case CLIENTE -> {
                ClienteDTO c = new ClienteDTO();
                if (request.getClienteDTO() != null) {
                    c.setNombre(nz(request.getClienteDTO().getNombre()));
                    c.setApellido(nz(request.getClienteDTO().getApellido()));
                    c.setTelefono(nz(request.getClienteDTO().getTelefono()));
                    c.setDomicilio(request.getClienteDTO().getDomicilio()); // puede ser null y está bien
                } else {
                    c.setNombre("");
                    c.setApellido("");
                    c.setTelefono("");
                    c.setDomicilio(null);
                }
                c.setUsuario(usuarioDto);
                ClienteResponseDTO registrado = clienteService.registrarCliente(c);
                return registrado;
            }
            case PRESTADOR_DE_SERVICIO -> {
                PrestadorDeServicioDTO p = new PrestadorDeServicioDTO();
                if (request.getPrestadorDTO() != null) {
                    p.setNombre(nz(request.getPrestadorDTO().getNombre()));
                    p.setApellido(nz(request.getPrestadorDTO().getApellido()));
                    p.setTelefono(nz(request.getPrestadorDTO().getTelefono()));
                } else {
                    p.setNombre("");
                    p.setApellido("");
                    p.setTelefono("");
                }
                p.setUsuario(usuarioDto);
                PrestadorDeServicioResponseDTO registrado = prestadorServicioService.registrar(p);
                return registrado;
            }
            default -> throw new IllegalArgumentException("Rol inválido: " + rol);
        }
    }

    private Object resolveEntidadPorRol(UsuarioResponseDTO usuario) {
        if (usuario.getRol() == Rol.CLIENTE) {
            return clienteService.findByUsuarioId(usuario.getId());
        } else if (usuario.getRol() == Rol.PRESTADOR_DE_SERVICIO) {
            return prestadorServicioService.findByUsuarioId(usuario.getId());
        } else {
            // si agregás roles nuevos, manejalos acá
            return usuario;
        }
    }

    private static String nz(String s) { return s == null ? "" : s; }
}
