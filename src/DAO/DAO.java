package DAO;

import Models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO {

    public boolean hasUser(User user) {

        if (user.getId() == 404 && user.getName().equals("404")) {
            return false;
        }
        else {
            return true;
        }
    }

    public User getUserFromListOfUsers(String idOfUser) throws IOException, SQLException, ClassNotFoundException {

        List<User> users = getAllUsers();

        if (idOfUser != null) {
            for (User user : users) {
                if (Integer.toString(user.getId()).equals(idOfUser)) {
                    return user;
                }
            }
        }

        return new User();
    }

    public List<User> getAllUsers() throws IOException, SQLException, ClassNotFoundException {

        List<User> users = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from users");

        while (rs.next()) {
            String[] infUser = new String[10];

            infUser[0] = rs.getString("id");
            infUser[1] = rs.getString("login");
            infUser[2] = rs.getString("password");
            infUser[3] = rs.getString("phone_number");
            infUser[4] = rs.getString("name");
            infUser[5] = rs.getString("serName");
            infUser[6] = rs.getString("date_birth");
            infUser[7] = rs.getString("date_registration");
            infUser[8] = rs.getString("avatar");
            infUser[9] = rs.getString("city");

            users.add(new User(
                    Integer.parseInt(infUser[0]),
                    infUser[1],
                    infUser[2],
                    infUser[3],
                    infUser[4],
                    infUser[5],
                    infUser[6],
                    infUser[7],
                    infUser[8],
                    infUser[9])
            );
        }

        return users;
    }

}
