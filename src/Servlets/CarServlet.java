package Servlets;

import DAO.*;
import Helpers.Helper;
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

    private CommentsDAO commentsDAO = new CommentsDAO();
    private CarsDAO carsDAO = new CarsDAO();
    private FavoritesDAO favoritesDAO = new FavoritesDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        String textOfComment = request.getParameter("textOfComment");
        String addToFavorites = request.getParameter("addToFavorites");

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {

                if (addToFavorites != null && addToFavorites.equals("true")) {
                    int id = favoritesDAO.getAllFavorites().size();
                    Favorite favorite = new Favorite(id, carsDAO.getCarFromAllCars(Integer.toString(car.getId())), user, "now");
                    favoritesDAO.addFavoriteToBD(favorite);
                    response.sendRedirect("/favorites");
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

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        String id_car = request.getParameter("id");

        try {

            car = carsDAO.getCarFromAllCars(id_car);

            Map<String, Object> root = new HashMap<>();

            root.put("car", car);
            root.put("comments", commentsDAO.getCommentsForCar(car));


            Helper.render(request, response, "detailCar.ftl", root);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
