package Servlets;

import DAO.UsersDAO;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        if (user != null) {
            response.sendRedirect("/catalog");
        }
        else {
            try {
                UsersDAO usersDAO = new UsersDAO();

                String id = request.getParameter("id");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String phone_number = request.getParameter("phone_number");
                String name = request.getParameter("name");
                String serName = request.getParameter("serName");
                String date_birth = request.getParameter("date_birth");
                String date_registration = request.getParameter("date_registration");
                String avatar = request.getParameter("avatar");
                String city = request.getParameter("city");

                if (id != null && login != null && password != null && phone_number != null && name != null && serName != null && date_birth != null && date_registration != null && avatar != null && city != null) {
                    user = new User(Integer.parseInt(id), login, password, phone_number, name, serName, date_birth, date_registration, avatar, city);
                    usersDAO.addUserIntoBD(user);
                    session.setAttribute("current_user", user);
                    response.sendRedirect("/registration?type=ok");
                }
                else {
                    response.sendRedirect("/registration?completed=false");
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

        String type = request.getParameter("type");
        String completedFields = request.getParameter("completed");

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        if (type != null && type.equals("ok")) {

            template = cfg.getTemplate("registrationOK.ftl");

            try {
                template.process(null, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        else {
            if (user != null) {
                response.sendRedirect("/catalog");
            } else {

                String messageCompletedFields = " ";

                if (completedFields != null && completedFields.equals("false")) {
                    messageCompletedFields = "All fields must be filled";
                    System.out.println(messageCompletedFields);
                }

                Map<String, Object> root = new HashMap<>();
                root.put("message", messageCompletedFields);

                template = cfg.getTemplate("registration.ftl");

                try {
                    template.process(root, writer);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
