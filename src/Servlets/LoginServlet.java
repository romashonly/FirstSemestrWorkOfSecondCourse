package Servlets;

import DAO.UsersDAO;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        if (user != null) {
            response.sendRedirect("/catalog");
        }
        else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

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

        if (request.getRequestURI().equals("/logout")) {
            session.removeAttribute("current_user");
        }

        if (user != null) {
            response.sendRedirect("/catalog");
        } else {

            PrintWriter writer = response.getWriter();

            response.setContentType("text/html");

            Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
            Template template = cfg.getTemplate("login.ftl");

            try {
                template.process(null, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
