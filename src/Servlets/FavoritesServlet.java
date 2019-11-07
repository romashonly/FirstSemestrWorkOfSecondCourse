package Servlets;

import DAO.CarsDAO;
import DAO.FavoritesDAO;
import Helpers.Helper;
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

public class FavoritesServlet extends HttpServlet {

    private FavoritesDAO favoritesDAO = new FavoritesDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String idOfCar = request.getParameter("idOfFavorite");

        try {
            favoritesDAO.removeFavorite(idOfCar);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        response.setContentType("text/html");

        Map<String, Object> root = new HashMap<>();

        try {
            root.put("favorites", favoritesDAO.getFavoritesOfUser(user));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Helper.render(request, response, "favorites.ftl", root);
    }
}
