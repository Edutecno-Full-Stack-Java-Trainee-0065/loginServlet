package cl.playground.login.service;

import cl.playground.login.model.Usuario;

public interface UsuarioService {
    boolean registrarUsuario(Usuario usuario, String confirmPassword);
    boolean autenticarUsuario(String username, String password);
    Usuario buscarPorUsername(String username);
}
