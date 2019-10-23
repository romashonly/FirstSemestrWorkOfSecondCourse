package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private static Connection connection;

    public static synchronized ConnectionSingleton getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/semestr_work",
                    "postgres",
                    "]qwe"
            );
        }

        return connection;
    }
}
