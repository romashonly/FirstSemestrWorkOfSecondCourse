package DAO;

import Models.Car;
import Models.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO extends DAO {

    public List<Car> getAllCars() throws SQLException, ClassNotFoundException, IOException {

        List<Car> cars = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from cars");

        while (rs.next()) {
            String[] infCar = new String[16];

            infCar[0] = rs.getString("id");
            infCar[1] = rs.getString("id_owner");
            infCar[2] = rs.getString("brand_car");
            infCar[3] = rs.getString("model_car");
            infCar[4] = rs.getString("year_issue");
            infCar[5] = rs.getString("date_posting");
            infCar[6] = rs.getString("color");
            infCar[7] = rs.getString("mileage");
            infCar[8] = rs.getString("engine");
            infCar[9] = rs.getString("body_type");
            infCar[10] = rs.getString("gearbox_type");
            infCar[11] = rs.getString("driveunit_type");
            infCar[12] = rs.getString("rudder_type");
            infCar[13] = rs.getString("condition_type");
            infCar[14] = rs.getString("image");
            infCar[15] = rs.getString("cost");


            cars.add(new Car(
                    Integer.parseInt(infCar[0]),
                    getUserFromListOfUsers(infCar[1]),
                    infCar[2],
                    infCar[3],
                    infCar[4],
                    infCar[5],
                    infCar[6],
                    infCar[7],
                    infCar[8],
                    infCar[9],
                    infCar[10],
                    infCar[11],
                    infCar[12],
                    infCar[13],
                    infCar[14],
                    Integer.parseInt(infCar[15]))
            );
        }

        return cars;
    }

    public List<Car> getFilterCars(String brand, String model, String minCost, String maxCost) throws SQLException, IOException, ClassNotFoundException {

        List<Car> allCars = getAllCars();
        List<Car> resultCars = new ArrayList<>();

        if (!minCost.equals("") && !maxCost.equals("")) {
            for (Car car : allCars) {
                if (car.getBrand_car().equals(brand) && car.getModel_car().equals(model) && car.getCost() >= Integer.parseInt(minCost) && car.getCost() <= Integer.parseInt(maxCost)) {
                    resultCars.add(car);
                }
            }
        }
        else if (!maxCost.equals("")) {
            for (Car car : allCars) {
                if (car.getBrand_car().equals(brand) && car.getModel_car().equals(model) && car.getCost() <= Integer.parseInt(maxCost)) {
                    resultCars.add(car);
                }
            }
        }
        else if (!minCost.equals("")) {
            for (Car car : allCars) {
                if (car.getBrand_car().equals(brand) && car.getModel_car().equals(model) && car.getCost() >= Integer.parseInt(minCost)) {
                    resultCars.add(car);
                }
            }
        }
        else {
            for (Car car : allCars) {
                if (car.getBrand_car().equals(brand) && car.getModel_car().equals(model)) {
                    resultCars.add(car);
                }
            }
        }

        return resultCars;
    }

    public Car getCarFromAllCars(String id_car) throws SQLException, IOException, ClassNotFoundException {

        List<Car> cars = getAllCars();

        if (id_car != null) {
            for (Car car : cars) {
                if (Integer.toString(car.getId()).equals(id_car)) {
                    return car;
                }
            }
        }

        return new Car();
    }

    public List<Car> getCarsOfUser(User user) throws SQLException, ClassNotFoundException, IOException {

        List<Car> allCars = new CarsDAO().getAllCars();
        List<Car> myCars = new ArrayList<>();

        for (Car car : allCars) {
            if (car.getOwner().getId() == user.getId()) {
                myCars.add(car);
            }
        }

        return myCars;
    }

    public boolean addCarsToBD(Car car) throws SQLException, ClassNotFoundException {

        String query = "INSERT INTO public.cars" + "(id, id_owner, brand_car, model_car, year_issue, date_posting, color, mileage, engine, body_type, gearbox_type, driveunit_type, rudder_type, condition_type, image, cost)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, car.getId());
        preparedStatement.setInt(2, car.getOwner().getId());
        preparedStatement.setString(3, car.getBrand_car());
        preparedStatement.setString(4, car.getModel_car());
        preparedStatement.setString(5, car.getYear_issue());
        preparedStatement.setString(6, car.getDate_posting());
        preparedStatement.setString(7, car.getColor());
        preparedStatement.setString(8, car.getMileage());
        preparedStatement.setString(9, car.getEngine());
        preparedStatement.setString(10, car.getBody_type());
        preparedStatement.setString(11, car.getGearBox_type());
        preparedStatement.setString(12, car.getDriveUnit_type());
        preparedStatement.setString(13, car.getRudder_type());
        preparedStatement.setString(14, car.getCondition_type());
        preparedStatement.setString(15, car.getImage());
        preparedStatement.setInt(16, car.getCost());

        preparedStatement.executeUpdate();

        return true;

    }

    public boolean removeCar(String idOfCar) throws SQLException, ClassNotFoundException {

        String query = "DELETE FROM public.cars WHERE id = '?';";
        PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(query);

        preparedStatement.setString(1, idOfCar);

        preparedStatement.executeUpdate();

        return true;
    }
}
