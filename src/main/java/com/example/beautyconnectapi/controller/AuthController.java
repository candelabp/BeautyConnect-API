package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioDTO;
import com.example.beautyconnectapi.model.dto.usuario.UsuarioResponseDTO;
import com.example.beautyconnectapi.model.enums.Rol;
import com.example.beautyconnectapi.service.ClienteService;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import com.example.beautyconnectapi.service.UsuarioService;
import com.google.firebase.auth.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final PrestadorDeServicioService prestadorServicioService;

    public AuthController(UsuarioService usuarioService, ClienteService clienteService, PrestadorDeServicioService prestadorServicioService) {
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.prestadorServicioService = prestadorServicioService;
    }

    // DTO que recibe el backend
    @Data
    @Getter
    @Setter
    public static class RegistroRequest {
        private String idToken;
        private String mail;
        private Rol rol;
        private String uid;
        private ClienteDTO clienteDTO;
        private PrestadorDeServicioDTO prestadorDTO;
    }

    /**
     * Registro manual (correo y contraseña)
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroRequest request) {
        try {
            // 1. Verificar token con Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
            String uid = decodedToken.getUid();

            if (usuarioService.existsByMail(request.mail) || usuarioService.existsByUid(uid)) {
                return ResponseEntity.ok("Usuario ya registrado");
            }

            // 2. Guardar usuario en base de datos
            UsuarioDTO usuarioDto = (new UsuarioDTO(
                    request.mail,
                    request.rol,
                    uid
            ));

            // 3. Guardar en tabla correspondiente según rol
            if (request.rol == Rol.CLIENTE) {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setNombre(request.getClienteDTO().getNombre());
                clienteDTO.setApellido(request.getClienteDTO().getApellido());
                clienteDTO.setTelefono(request.getClienteDTO().getTelefono());
                clienteDTO.setUsuario(usuarioDto);

                ClienteResponseDTO registrado = clienteService.registrarCliente(clienteDTO);
                return ResponseEntity.ok(registrado);
            } else if (request.rol == Rol.PRESTADOR_DE_SERVICIO) {
                PrestadorDeServicioDTO prestadorDTO = new PrestadorDeServicioDTO();
                prestadorDTO.setNombre(request.getPrestadorDTO().getNombre());
                prestadorDTO.setApellido(request.getPrestadorDTO().getApellido());
                prestadorDTO.setTelefono(request.getPrestadorDTO().getTelefono());
                prestadorDTO.setUsuarioDTO(usuarioDto);

                PrestadorDeServicioResponseDTO registrado = prestadorServicioService.registrar(prestadorDTO);
                return ResponseEntity.ok(registrado);
            }
            return ResponseEntity.badRequest().body("Rol inválido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Registro con Google
     */
    @PostMapping("/google")
    public ResponseEntity<?> registerWithGoogle(@RequestBody RegistroRequest request) {
        try {
            // 1. Verificar token de Google con Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();

            if (usuarioService.existsByMail(email) || usuarioService.existsByUid(uid)) {
                UsuarioResponseDTO usuario = usuarioService.findByMail(email);
                if (usuario.getRol() == Rol.CLIENTE) {
                    ClienteResponseDTO clienteDTO = clienteService.findByUsuarioId(usuario.getId());
                    return ResponseEntity.ok(clienteDTO);
                } else if (usuario.getRol() == Rol.PRESTADOR_DE_SERVICIO) {
                    PrestadorDeServicioResponseDTO prestadorDTO = prestadorServicioService.findByUsuarioId(usuario.getId());
                    return ResponseEntity.ok(prestadorDTO);
                }
            } else {
                // 2. Guardar usuario si no existe
                UsuarioDTO usuarioDto = (new UsuarioDTO(
                        email,
                        request.rol,
                        uid
                ));

            // 3. Guardar en tabla correspondiente según rol si no existía
            if (request.rol == Rol.CLIENTE) {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setNombre("");
                clienteDTO.setApellido("");
                clienteDTO.setTelefono("");
                clienteDTO.setUsuario(usuarioDto);

                ClienteResponseDTO registrado = clienteService.registrarCliente(clienteDTO);
                return ResponseEntity.ok(registrado);
            } else if (request.rol == Rol.PRESTADOR_DE_SERVICIO) {
                PrestadorDeServicioDTO prestadorDTO = new PrestadorDeServicioDTO();
                prestadorDTO.setNombre("");
                prestadorDTO.setApellido("");
                prestadorDTO.setTelefono("");
                prestadorDTO.setUsuarioDTO(usuarioDto);

                PrestadorDeServicioResponseDTO registrado = prestadorServicioService.registrar(prestadorDTO);
                return ResponseEntity.ok(registrado);
            }}


            return ResponseEntity.badRequest().body("Rol inválido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en registro con Google: " + e.getMessage());
        }
    }
}