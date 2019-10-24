package DAO;

import Models.Car;
import Models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyPostsDAO extends DAO {

    public List<Car> getMyPosts(User user) throws SQLException, ClassNotFoundException, IOException {

        List<Car> allCars = new CarsDAO().getAllCars();
        List<Car> myCars = new ArrayList<>();

        for (Car car : allCars) {
            if (car.getOwner().getId() == user.getId()) {
                myCars.add(car);
            }
        }

        return myCars;
    }
}
