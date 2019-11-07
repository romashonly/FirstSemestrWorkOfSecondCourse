package Servlets;

import DAO.ChatsDAO;
import DAO.UsersDAO;
import Helpers.Helper;
import Models.Chat;
import Models.Message;
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

    private ChatsDAO chatsDAO = new ChatsDAO();
    private UsersDAO usersDAO = new UsersDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {

            String textOfNewMessage = request.getParameter("newText");
            Chat chat = chatsDAO.getChat(request.getParameter("id_chat"));
            User destUser;

            if (user.getId() == chat.getUser_first().getId()) {
                destUser = chat.getUser_second();
            }
            else {
                destUser = chat.getUser_first();
            }

            int id = chatsDAO.getAllMessages().size();
            Message newMessage = new Message(id, user, destUser, chat, textOfNewMessage, "now");

            chatsDAO.addMessageToBD(newMessage);

            response.sendRedirect("/chats?id=" + chat.getId());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        response.setContentType("text/html");

        String id_chat = request.getParameter("id");

        String idDestUser = request.getParameter("idDestUser");

        try {

            Map<String, Object> root = new HashMap<>();

            root.put("user", user);

            if (id_chat != null) {

                root.put("messages", chatsDAO.getMessages(chatsDAO.getChat(id_chat)));
                root.put("chat", chatsDAO.getChat(id_chat));
                Helper.render(request, response, "chatsBetweenUsers.ftl", root);
            }
            else {
                root.put("chats", chatsDAO.getChatsOfUser(user));
                Helper.render(request, response, "chats.ftl", root);
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
