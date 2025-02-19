package com.gestion.estudiantes.repository.usuario;

import com.gestion.estudiantes.model.Usuario;
import com.gestion.estudiantes.repository.UsuarioRepository;
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
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    public String autenticar(String username, String password) {
        // Busca el usuario por nombre de usuario
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Verifica la contraseña (deberías usar hashing en un entorno real)
        if (usuario.getPassword().equals(password)) {
            return "token_generado"; // Aquí deberías generar un token JWT
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }
}
