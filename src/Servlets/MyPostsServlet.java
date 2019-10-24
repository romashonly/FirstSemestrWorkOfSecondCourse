package Servlets;

import DAO.CarsDAO;
import DAO.MyPostsDAO;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MyPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        MyPostsDAO myPostsDAO = new MyPostsDAO();

        try {

            if (user != null) {
                template = cfg.getTemplate("myPosts.ftl");

                Map<String, Object> root = new HashMap<>();
                root.put("myCars", myPostsDAO.getMyPosts(user));

                template.process(root, writer);
            } else {
                response.sendRedirect("/login");
            }

        } catch (TemplateException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
