package com.gestion.estudiantes.controller;

import com.gestion.estudiantes.model.Usuario;
import com.gestion.estudiantes.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            String tokenString = usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());

            Usuario usuarioAutenticado = usuarioService.getUsuarioByUsername(usuario.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Long estudianteId = (usuarioAutenticado.getEstudiante() != null)
                    ? usuarioAutenticado.getEstudiante().getId()
                    : null;

            Map<String, Object> response = new HashMap<>();

            response.put("token", tokenString);
            response.put("estudiante_id", estudianteId);

            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            usuarioService.registrar(usuario);
            // Generar un token para el usuario recién registrado
            String tokenString = usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put("token", tokenString);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Método para obtener el perfil del usuario autenticado
    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("token_")) {
            return ResponseEntity.status(401).body("No autorizado");
        }

        String username = token.split("_")[1]; // Extraer el username del token simple
        Usuario usuario = usuarioService.getUsuarioByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(usuario);
    }
}
