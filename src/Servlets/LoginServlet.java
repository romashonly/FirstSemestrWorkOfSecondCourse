package Servlets;

import DAO.UsersDAO;
import Helpers.Helper;
import Models.User;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String remember_me = request.getParameter("remember_me");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String passHash = Arrays.toString(messageDigest.digest(password.getBytes()));

        if (remember_me != null && remember_me.equals("on")) {

            Cookie cookieLogin = new Cookie("login", login);
            Cookie cookiePassword = new Cookie("password", password);
            cookieLogin.setMaxAge(24 * 60 * 60);
            cookiePassword.setMaxAge(24 * 60 * 60);

            response.addCookie(cookieLogin);
            response.addCookie(cookiePassword);
        }

        try {

            UsersDAO usersDAO = new UsersDAO();

            if (usersDAO.checkCorrectLoginAndPassword(login, passHash)) {

                HttpSession session = request.getSession();
                User user = usersDAO.getUserByLogin(login, passHash);

                session.setAttribute("current_user", user);
                response.sendRedirect("/catalog");
            } else {
                response.sendRedirect("/login?isCorrect=false");
            }
        }
        catch(SQLException | ClassNotFoundException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        Map<String, Object> root = new HashMap<>();

        if (request.getRequestURI().equals("/logout")) {

            Cookie cookieLogin = new Cookie("login", "");
            Cookie cookiePassword = new Cookie("password", "");
            cookieLogin.setMaxAge(0);
            cookiePassword.setMaxAge(0);

            response.addCookie(cookieLogin);
            response.addCookie(cookiePassword);

            session.removeAttribute("current_user");

            response.sendRedirect("/login");
        }
        else if (request.getRequestURI().equals("/wants_logout")) {

            Helper.render(request, response, "consentLogout.ftl", root);
        }
        else {
            if (user != null) {
                response.sendRedirect("/catalog");
            }
            else {

                Helper.render(request, response, "login.ftl", root);
            }
        }
    }
}
