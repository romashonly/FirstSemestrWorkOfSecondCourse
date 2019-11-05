package Servlets;

import DAO.UsersDAO;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {

    @Override
    public void init() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

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

            template = cfg.getTemplate("consentLogout.ftl");

            try {
                template.process(null, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        else {
            if (user != null) {
                response.sendRedirect("/catalog");
            }
            else {

                template = cfg.getTemplate("login.ftl");

                try {
                    template.process(null, writer);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
