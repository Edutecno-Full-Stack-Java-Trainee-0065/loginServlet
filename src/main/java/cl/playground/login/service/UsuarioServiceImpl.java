package cl.playground.login.service;

import cl.playground.login.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private static final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioServiceImpl() {
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario(
                    "admin",
                    "password",
                    "admin@algo.com",
                    "admincito"
            ));
        }
    }

    @Override
    public boolean registrarUsuario(Usuario usuario, String confirmPassword) {
        // Validaciones
        if (!validarDatosRegistro(usuario, confirmPassword)) {
            return false;
        }

        // Verificar si el username ya existe
        if (buscarPorUsername(usuario.getUsername()) != null) {
            return false;
        }

        // Verificar si el email ya esta registrado
        if (usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()))) {
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    @Override
    public boolean autenticarUsuario(String username, String password) {
        return usuarios.stream()
                .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        return usuario.orElse(null);
    }

    private boolean validarDatosRegistro(Usuario usuario, String confirmPassword) {
        // Validamos campos vacios o nulos
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            return false;
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            return false;
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            return false;
        }
        if (usuario.getNickname() == null || usuario.getNickname().trim().isEmpty()) {
            return false;
        }

        // Validamos que las contraseñas coincidan
        if (!usuario.getPassword().equals(confirmPassword)) {
            return false;
        }

        return true;
    }
}
