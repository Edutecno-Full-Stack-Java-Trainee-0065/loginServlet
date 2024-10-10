package cl.playground.login.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");

        if ("admin".equals(usuario) && "password".equals(contrasena)) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", usuario);

            resp.sendRedirect("bienvenida.jsp");
        } else {
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
