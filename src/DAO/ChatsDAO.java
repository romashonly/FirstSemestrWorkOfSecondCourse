package DAO;

import Models.Chat;
import Models.Message;
import Models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatsDAO extends DAO {

    public List<Chat> getAllChats() throws SQLException, ClassNotFoundException, IOException {

        List<Chat> chats = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from chats");

        while (rs.next()) {
            String[] infChat = new String[4];

            infChat[0] = rs.getString("id");
            infChat[1] = rs.getString("id_userfirst");
            infChat[2] = rs.getString("id_usersecond");
            infChat[3] = rs.getString("date_creating");
            chats.add(new Chat(
                    Integer.parseInt(infChat[0]),
                    getUserFromListOfUsers(infChat[1]),
                    getUserFromListOfUsers(infChat[2]),
                    infChat[3])
            );
        }

        return chats;
    }

    public List<Message> getAllMessages() throws SQLException, ClassNotFoundException, IOException {

        List<Message> messages = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from messages");

        while (rs.next()) {
            String[] infMessage = new String[6];

            infMessage[0] = rs.getString("id");
            infMessage[1] = rs.getString("id_sender");
            infMessage[2] = rs.getString("id_destuser");
            infMessage[3] = rs.getString("id_chat");
            infMessage[4] = rs.getString("text");
            infMessage[5] = rs.getString("date_sending");

            messages.add(new Message(
                    Integer.parseInt(infMessage[0]),
                    getUserFromListOfUsers(infMessage[1]),
                    getUserFromListOfUsers(infMessage[2]),
                    getChat(infMessage[3]),
                    infMessage[4],
                    infMessage[5])
            );
        }

        return messages;
    }

    public Chat getChat(String id_chat) throws SQLException, IOException, ClassNotFoundException {

        List<Chat> allChats = getAllChats();

        for (Chat chat : allChats) {
            if (chat.getId() == Integer.parseInt(id_chat)) {
                return chat;
            }
        }

        return new Chat();
    }

    public List<Message> getMessages(Chat chat) throws SQLException, IOException, ClassNotFoundException {

        List<Message> allMessages = getAllMessages();
        List<Message> messages = new ArrayList<>();

        for (Message message : allMessages) {
            if (message.getChat().getId() == chat.getId()) {
                messages.add(message);
            }
        }

        return messages;
    }

    public boolean checkHasChat(User user_first, User user_second) throws SQLException, IOException, ClassNotFoundException {

        List<Chat> allChats = getAllChats();

        for (Chat chat : allChats) {
            if ( (chat.getUser_first().getId() == user_first.getId()
                    && chat.getUser_second().getId() == user_second.getId())
                    ||
                    (chat.getUser_first().getId() == user_second.getId()
                            && chat.getUser_second().getId() == user_first.getId())
            ) {
                return true;
            }
        }

        return false;
    }

    public Chat getChat(User user_first, User user_second) throws SQLException, IOException, ClassNotFoundException {

        List<Chat> allChats = getAllChats();

        for (Chat chat : allChats) {
            if ( (chat.getUser_first().getId() == user_first.getId()
                    && chat.getUser_second().getId() == user_second.getId())
                    ||
                    (chat.getUser_first().getId() == user_second.getId()
                            && chat.getUser_second().getId() == user_first.getId())
            ) {
                return chat;
            }
        }

        return new Chat();
    }

    public boolean addMessageToBD(Message message) {
        return true;
    }

    public boolean addChatToBD(Chat chat) {
        return true;
    }
}
