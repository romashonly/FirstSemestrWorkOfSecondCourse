package Servlets;

import DAO.CarsDAO;
import DAO.ChatsDAO;
import DAO.CommentsDAO;
import DAO.UsersDAO;
import Models.Chat;
import Models.Comment;
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

public class ProfileServlet extends HttpServlet {

    private User userForPage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        String textOfMessage = request.getParameter("textOfMessage");

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {
                ChatsDAO chatsDAO = new ChatsDAO();

                if (textOfMessage != null) {

                    Chat chat;

                    if (!chatsDAO.checkHasChat(user, userForPage)) {
                        chat = new Chat(0, user, userForPage, "now");
                        chatsDAO.addChatToBD(chat);
                    }
                    else {
                        chat = chatsDAO.getChat(user, userForPage);
                    }

                    Message message = new Message(1, user, userForPage, chat, textOfMessage, "now");
                    chatsDAO.addMessageToBD(message);

                    response.sendRedirect("/chats?id=" + chat.getId());
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

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        UsersDAO usersDAO = new UsersDAO();

        String id_otherUser = request.getParameter("id");

        if (user != null) {
            try {
                template = cfg.getTemplate("profile.ftl");
                Map<String, Object> root = new HashMap<>();

                if (id_otherUser != null) {
                    userForPage = usersDAO.getUserFromListOfUsers(id_otherUser);
                }
                else {
                    userForPage = user;
                }

                root.put("id", userForPage.getId());
                root.put("login", userForPage.getLogin());
                root.put("phone_number", userForPage.getPhone_number());
                root.put("name", userForPage.getName());
                root.put("serName", userForPage.getSerName());
                root.put("date_birth", userForPage.getDate_birth());
                root.put("date_registration", userForPage.getDate_registration());
                root.put("avatar", userForPage.getAvatar());
                root.put("city", userForPage.getCity());

                template.process(root, writer);
            } catch (TemplateException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            response.sendRedirect("/login");
        }
    }
}
