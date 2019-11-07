package Servlets;

import DAO.CarsDAO;
import DAO.ChatsDAO;
import DAO.CommentsDAO;
import DAO.UsersDAO;
import Helpers.Helper;
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

    private ChatsDAO chatsDAO = new ChatsDAO();
    private UsersDAO usersDAO = new UsersDAO();
    private User userForPage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        try {
            Chat chat;

            if (chatsDAO.checkHasChat(user, userForPage) < 0) {
                int idChat = chatsDAO.getAllChats().size();
                chat = new Chat(idChat, user, userForPage, "now");
                chatsDAO.addChatToBD(chat);
            }
            else {
                chat = chatsDAO.getChat(user, userForPage);
            }

            response.sendRedirect("/chats?id=" + chat.getId());
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        response.setContentType("text/html");

        String id_otherUser = request.getParameter("id");

        try {

            Map<String, Object> root = new HashMap<>();

            if (id_otherUser != null) {
                if (user != null && id_otherUser.equals(Integer.toString(user.getId()))) {
                    userForPage = user;
                    root.put("other_user", "false");
                }
                else {
                    userForPage = usersDAO.getUserFromListOfUsers(id_otherUser);
                    root.put("other_user", "true");
                }
            }
            else {
                userForPage = user;
                root.put("other_user", "false");
            }

            root.put("user", userForPage);

            Helper.render(request, response, "profile.ftl", root);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
