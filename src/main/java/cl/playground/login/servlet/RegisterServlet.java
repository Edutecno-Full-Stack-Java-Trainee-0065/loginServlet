package cl.playground.login.servlet;

import cl.playground.login.model.Usuario;
import cl.playground.login.service.UsuarioService;
import cl.playground.login.service.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private final UsuarioService usuarioService;

    public RegisterServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Capturamos los parametros de la REQUEST
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        // COnstruimos el objetp en base a los parametros
        Usuario usuario = new Usuario(
                username,
                password,
                email,
                nickname
        );

        // Confirmamos la creacion efectiva o registro del nuevo usuario.
        if (usuarioService.registrarUsuario(usuario, confirmPassword)) {
            resp.sendRedirect("login");
        } else {
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
