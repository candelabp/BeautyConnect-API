package com.example.beautyconnectapi.controller;

import com.example.beautyconnectapi.config.firebase.FirebaseConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final PrestadorDeServicioService prestadorServicioService;
    private final FirebaseConfig firebaseConfig;

    public UsuarioController(UsuarioService usuarioService, ClienteService clienteService, PrestadorDeServicioService prestadorServicioService, FirebaseConfig firebaseConfig) {
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.prestadorServicioService = prestadorServicioService;
        this.firebaseConfig = firebaseConfig;
    }


    @PostMapping("/save")
    public UsuarioResponseDTO saveUsuario(@RequestBody UsuarioDTO usuarioDto){
        return usuarioService.saveUsuario(usuarioDto);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioResponseDTO getUsuarioById(@PathVariable Long usuarioId){
        return usuarioService.getUsuarioById(usuarioId);
    }

    @PatchMapping("/delete/{usuarioId}")
    public UsuarioResponseDTO deleteUsuario(@PathVariable Long usuarioId){
        return usuarioService.deleteUsuario(usuarioId);
    }

    @PatchMapping("/obtenerPorEmail/{mail}")
    public UsuarioResponseDTO findByMail(@PathVariable String mail){
        return usuarioService.findByMail(mail);
    }

    @PatchMapping("/existePorUid/{uid}")
    public boolean existsByUid(@PathVariable String uid) {
        return usuarioService.existsByUid(uid);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Auth request) {
        try {
            // 1. Verificar token con Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
            String uid = decodedToken.getUid();

            if (usuarioService.existsByMail(request.getMail()) || usuarioService.existsByUid(uid)) {
                return ResponseEntity.ok("Usuario ya registrado");
            }

            firebaseConfig.asignarRol(uid, request.getRol().toString());

            // 2. Guardar usuario en base de datos
            UsuarioDTO usuarioDto = (new UsuarioDTO(
                    request.getMail(),
                    request.getRol(),
                    uid
            ));

            // 3. Guardar en tabla correspondiente según rol
            if (request.getRol() == Rol.CLIENTE) {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setNombre(request.getClienteDTO().getNombre());
                clienteDTO.setApellido(request.getClienteDTO().getApellido());
                clienteDTO.setTelefono(request.getClienteDTO().getTelefono());
                clienteDTO.setDomicilio(request.getClienteDTO().getDomicilio());
                clienteDTO.setUsuario(usuarioDto);

                ClienteResponseDTO registrado = clienteService.registrarCliente(clienteDTO);
                return ResponseEntity.ok(registrado);
            } else if (request.getRol() == Rol.PRESTADOR_DE_SERVICIO) {
                PrestadorDeServicioDTO prestadorDTO = new PrestadorDeServicioDTO();
                prestadorDTO.setNombre(request.getPrestadorDTO().getNombre());
                prestadorDTO.setApellido(request.getPrestadorDTO().getApellido());
                prestadorDTO.setTelefono(request.getPrestadorDTO().getTelefono());
                prestadorDTO.setUsuario(usuarioDto);

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
    public ResponseEntity<?> registerWithGoogle(@RequestBody Auth request) {
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
                        request.getRol(),
                        uid
                ));

                // 3. Guardar en tabla correspondiente según rol si no existía
                if (request.getRol() == Rol.CLIENTE) {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setNombre("");
                    clienteDTO.setApellido("");
                    clienteDTO.setTelefono("");
                    clienteDTO.setDomicilio(null);
                    clienteDTO.setUsuario(usuarioDto);

                    ClienteResponseDTO registrado = clienteService.registrarCliente(clienteDTO);
                    return ResponseEntity.ok(registrado);
                } else if (request.getRol() == Rol.PRESTADOR_DE_SERVICIO) {
                    PrestadorDeServicioDTO prestadorDTO = new PrestadorDeServicioDTO();
                    prestadorDTO.setNombre("");
                    prestadorDTO.setApellido("");
                    prestadorDTO.setTelefono("");
                    prestadorDTO.setUsuario(usuarioDto);

                    PrestadorDeServicioResponseDTO registrado = prestadorServicioService.registrar(prestadorDTO);
                    return ResponseEntity.ok(registrado);
                }}


            return ResponseEntity.badRequest().body("Rol inválido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en registro con Google: " + e.getMessage());
        }
    }
}
