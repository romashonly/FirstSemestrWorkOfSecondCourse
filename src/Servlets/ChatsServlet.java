package Servlets;

import DAO.ChatsDAO;
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

public class ChatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        String id_chat = request.getParameter("id");

        ChatsDAO chatsDAO = new ChatsDAO();

        if (user != null) {

            try {

                Map<String, Object> root = new HashMap<>();

                if (id_chat != null) {
                    template = cfg.getTemplate("chatsBetweenUsers.ftl");

                    root.put("messages", chatsDAO.getMessages(chatsDAO.getChat(id_chat)));

                }
                else {
                    template = cfg.getTemplate("chats.ftl");

                    root.put("chats", chatsDAO.getAllChats());

                }

                template.process(root, writer);
            }
            catch (TemplateException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
