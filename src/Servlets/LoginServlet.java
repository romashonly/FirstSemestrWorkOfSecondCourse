package Servlets;

import DAO.UsersDAO;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    public void init() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        String remember_me = request.getParameter("remember_me");


        if (user != null) {
            response.sendRedirect("/catalog");
        }
        else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (remember_me != null && remember_me.equals("on")) {
                Cookie[] cookies = request.getCookies();

                for (Cookie cookie :
                        cookies) {
                    System.out.println(cookie.getName());
                    System.out.println(cookie.getValue());
                }

                Cookie cookie = new Cookie("remember_me", "login-" + login + "-password-" + password);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }

            try {

                UsersDAO usersDAO = new UsersDAO();

                if (usersDAO.checkCorrectLoginAndPassword(login, password)) {

                    user = usersDAO.getUserByLogin(login, password);

                    session.setAttribute("current_user", user);
                    response.sendRedirect("/catalog");
                } else {
                    response.sendRedirect("/login?isCorrect=false");
                }
            }
            catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
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
            session.removeAttribute("current_user");
        }

        if (request.getRequestURI().equals("/wants_logout")) {

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
