package DAO;

import Models.Car;
import Models.Favorite;
import Models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FavoritesDAO extends DAO {

    public List<Favorite> getAllFavorites() throws SQLException, ClassNotFoundException, IOException {

        List<Favorite> favorites = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from favorites");

        while (rs.next()) {
            String[] infFavorite = new String[4];

            infFavorite[0] = rs.getString("id");
            infFavorite[1] = rs.getString("id_car");
            infFavorite[2] = rs.getString("id_user");
            infFavorite[3] = rs.getString("date_adding");

            favorites.add(new Favorite(
                    Integer.parseInt(infFavorite[0]),
                    new CarsDAO().getCarFromAllCars(infFavorite[1]),
                    getUserFromListOfUsers(infFavorite[2]),
                    infFavorite[3])
            );
        }

        return favorites;
    }

    public List<Favorite> getMyFavorites(User user) throws SQLException, ClassNotFoundException, IOException {

        List<Favorite> allFavorites = getAllFavorites();
        List<Favorite> myFavorites = new ArrayList<>();

        for (Favorite favorite : allFavorites) {
            if (favorite.getUser().getId() == user.getId()) {
                myFavorites.add(favorite);
            }
        }

        return myFavorites;
    }
}
