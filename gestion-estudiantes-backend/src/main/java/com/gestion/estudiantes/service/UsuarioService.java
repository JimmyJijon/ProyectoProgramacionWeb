package com.gestion.estudiantes.service;

import com.gestion.estudiantes.model.Usuario;
import com.gestion.estudiantes.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        // Verifica si el usuario ya existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    public String autenticar(String username, String password) {
        // Busca el usuario por nombre de usuario
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar credenciales directamente
        if (usuario.getPassword().equals(password)) {
            // Generar un "token" simple (puede ser solo el username o algo más)
            return "token_" + username + "_" + System.currentTimeMillis();
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }

    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username); // Busca el usuario por su nombre
    }
}
