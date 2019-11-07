package Servlets;

import DAO.CarsDAO;
import Helpers.Helper;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CatalogServlet extends HttpServlet {

    @Override
    public void init() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        String brand = request.getParameter("brandCar");
        String model = request.getParameter("modelCar");
        String minCost = request.getParameter("minCost");
        String maxCost = request.getParameter("maxCost");

        CarsDAO carsDAO = new CarsDAO();
        Map<String, Object> root = new HashMap<>();

        try {

            if (brand != null) {
                root.put("cars", carsDAO.getFilterCars(brand, model, minCost, maxCost));
            }
            else {
                root.put("cars", carsDAO.getAllCars());
            }

            Helper.render(request, response, "catalog.ftl", root);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
