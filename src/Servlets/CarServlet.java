package Servlets;

import DAO.CarsDAO;
import DAO.ChatsDAO;
import DAO.CommentsDAO;
import DAO.UsersDAO;
import Models.Car;
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

public class CarServlet extends HttpServlet {

    private Car car;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        String textOfComment = request.getParameter("textOfComment");

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {
                CommentsDAO commentsDAO = new CommentsDAO();

                if (textOfComment != null) {
                    Comment comment = new Comment(1, user, car, textOfComment, "now");
                    commentsDAO.addCommentToBD(comment);
                    response.sendRedirect("/catalog/car?id=" + car.getId());
                }
                else {
                    response.sendRedirect("/catalog/car?id=" + car.getId());
                }
            }
            catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("current_user");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        String id_car = request.getParameter("id");

        CarsDAO carsDAO = new CarsDAO();
        CommentsDAO commentsDAO = new CommentsDAO();

        try {

//            if (user != null) {
                template = cfg.getTemplate("detailCar.ftl");

                car = carsDAO.getCarFromAllCars(id_car);

                Map<String, Object> root = new HashMap<>();
                root.put("id", car.getId());
                root.put("owner", car.getOwner());
                root.put("brand_car", car.getBrand_car());
                root.put("model_car", car.getModel_car());
                root.put("year_issue", car.getYear_issue());
                root.put("date_posting", car.getDate_posting());
                root.put("color", car.getColor());
                root.put("mileage", car.getMileage());
                root.put("engine", car.getEngine());
                root.put("body_type", car.getBody_type());
                root.put("gearBox_type", car.getGearBox_type());
                root.put("driveUnit_type", car.getDriveUnit_type());
                root.put("rudder_type", car.getRudder_type());
                root.put("condition_type", car.getCondition_type());
                root.put("image", car.getImage());
                root.put("cost", car.getCost());

                root.put("comments", commentsDAO.getCommentsForCar(car));

                template.process(root, writer);
//            } else {
//                response.sendRedirect("/login");
//            }

        } catch (TemplateException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
