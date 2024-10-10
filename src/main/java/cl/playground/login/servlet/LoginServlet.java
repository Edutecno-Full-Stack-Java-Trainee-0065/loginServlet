package cl.playground.login.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");

        if ("admin".equals(usuario) && "password".equals(contrasena)) {

            // Creando cookie
            Cookie userCookie = new Cookie("usuario", usuario);
            userCookie.setMaxAge(30 * 60); // 30 minutos
            userCookie.setPath("/");
            resp.addCookie(userCookie);

            resp.sendRedirect("bienvenida.jsp");
        } else {
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
