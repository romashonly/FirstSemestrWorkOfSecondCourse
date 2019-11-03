package Servlets;

import DAO.UsersDAO;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
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

                int id = usersDAO.getAllUsers().size();
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String phone_number = request.getParameter("phone_number");
                String name = request.getParameter("name");
                String serName = request.getParameter("serName");
                String date_birth = request.getParameter("date_birth");
                String date_registration = request.getParameter("date_registration");

                Part p = request.getPart("photo");
                String localdir = "uploads";
                String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
                File dir = new File(pathDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                String[] filename_data = p.getSubmittedFileName().split("\\.");
                String filename = Math.random() + "." + filename_data[filename_data.length - 1];
                String fullpath = pathDir + File.separator + filename;
                p.write(fullpath);

                String avatar = new String("/" + localdir + "/" + filename);

                String city = request.getParameter("city");

                if (login != null && password != null && phone_number != null && name != null && serName != null && date_birth != null && date_registration != null && avatar != null && city != null) {

                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    String passHash = Arrays.toString(messageDigest.digest(password.getBytes()));

                    user = new User(id, login, passHash, phone_number, name, serName, date_birth, date_registration, avatar, city);
                    usersDAO.addUserIntoBD(user);
                    session.setAttribute("current_user", user);
                    response.sendRedirect("/registration?type=ok");
                }
                else {
                    response.sendRedirect("/registration?completed=false");
                }
            }
            catch(SQLException | ClassNotFoundException | NoSuchAlgorithmException e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("current_user");

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
//            if (user != null) {
//                response.sendRedirect("/catalog");
//            } else {

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
//            }
        }
    }
}
