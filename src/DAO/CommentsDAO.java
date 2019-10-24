package DAO;

import Models.Car;
import Models.Comment;
import Models.Message;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsDAO extends DAO {

    public List<Comment> getAllComments() throws SQLException, IOException, ClassNotFoundException {

        List<Comment> comments = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();

        CarsDAO carsDAO = new CarsDAO();

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from comments");

        while (rs.next()) {
            String[] infComment = new String[5];

            infComment[0] = rs.getString("id");
            infComment[1] = rs.getString("id_sender");
            infComment[2] = rs.getString("id_car");
            infComment[3] = rs.getString("text");
            infComment[4] = rs.getString("date_sending");

            comments.add(new Comment(
                    Integer.parseInt(infComment[0]),
                    getUserFromListOfUsers(infComment[1]),
                    carsDAO.getCarFromAllCars(infComment[2]),
                    infComment[3],
                    infComment[4])
            );
        }

        return comments;
    }

    public List<Comment> getCommentsForCar(Car car) throws SQLException, IOException, ClassNotFoundException {

        List<Comment> allComments = getAllComments();
        List<Comment> commentsOfCar = new ArrayList<>();

        for (Comment comment : allComments) {

            if (comment.getCar().getId() == car.getId()) {
                commentsOfCar.add(comment);
            }
        }

        return commentsOfCar;
    }

    public boolean addCommentToBD(Comment comment) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO public.comments" + "(id, id_sender, id_car, text, date_sending)" + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(query);
        preparedStatement.setString(1, Integer.toString(comment.getId()));
        preparedStatement.setString(2, Integer.toString(comment.getSender().getId()));
        preparedStatement.setString(3, Integer.toString(comment.getCar().getId()));
        preparedStatement.setString(4, comment.getText());
        preparedStatement.setString(5, comment.getDate_sending());

        preparedStatement.executeUpdate();

        return true;
    }
}
