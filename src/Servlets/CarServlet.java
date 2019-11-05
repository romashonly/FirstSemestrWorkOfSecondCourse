package Servlets;

import DAO.*;
import Models.*;
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
        String idOfCar = request.getParameter("idOfCar");

        CommentsDAO commentsDAO = new CommentsDAO();
        CarsDAO carsDAO = new CarsDAO();
        FavoritesDAO favoritesDAO = new FavoritesDAO();

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {

                if (idOfCar != null) {
                    int id = favoritesDAO.getAllFavorites().size();
                    Favorite favorite = new Favorite(id, carsDAO.getCarFromAllCars(idOfCar), user, "now");
                    favoritesDAO.addFavoriteToBD(favorite);
                }
                else if (textOfComment != null) {
                    int id = commentsDAO.getAllComments().size();
                    Comment comment = new Comment(id, user, car, textOfComment, "now");
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

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        String id_car = request.getParameter("id");

        CarsDAO carsDAO = new CarsDAO();
        CommentsDAO commentsDAO = new CommentsDAO();

        try {

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

        } catch (TemplateException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
