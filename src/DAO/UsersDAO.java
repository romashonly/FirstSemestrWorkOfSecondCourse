package DAO;

import Models.User;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UsersDAO extends DAO {

    public User getUserByLogin(String login, String password) throws SQLException, IOException, ClassNotFoundException {

        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return new User();
    }

    public boolean checkCorrectLoginAndPassword(String login, String password) throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {

        if (login != null && password != null) {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            String passHash = Arrays.toString(messageDigest.digest(password.getBytes()));

            User user = getUserByLogin(login, password);

            return user.getLogin().equals(login) && user.getPassword().equals(password);
        }

        return false;
    }

    public boolean addUserIntoBD(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO public.users" + "(id, login, password, phone_number, name, sername, date_birth, date_registration, avatar, city)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(query);
        preparedStatement.setString(1, Integer.toString(user.getId()));
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getPhone_number());
        preparedStatement.setString(5, user.getName());
        preparedStatement.setString(6, user.getSerName());
        preparedStatement.setString(7, user.getDate_birth());
        preparedStatement.setString(8, user.getDate_registration());
        preparedStatement.setString(9, user.getAvatar());
        preparedStatement.setString(10, user.getCity());

        preparedStatement.executeUpdate();

        return true;
    }
}
