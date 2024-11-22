package cl.playground.login.servlet;

import cl.playground.login.model.Usuario;
import cl.playground.login.service.UsuarioService;
import cl.playground.login.service.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final UsuarioService usuarioService;

    public LoginServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");

        if (usuarioService.autenticarUsuario(usuario, contrasena)) {
            // Obtener al usuario completo a traves de su username
            Usuario usuarioAutenticado = usuarioService.buscarPorUsername(usuario);

            // Crear sesion y guardar la informacion del usuario
            HttpSession session = req.getSession();
            session.setAttribute("nickname", usuarioAutenticado.getNickname());

            resp.sendRedirect("bienvenida.jsp");
        } else {
            resp.sendRedirect("login.jsp?error=1");
        }
    }
}
