package Servlets;

import DAO.CarsDAO;
import DAO.UsersDAO;
import Models.Car;
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

public class NewPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {

                CarsDAO carsDAO = new CarsDAO();

                String  id = request.getParameter("id");
                String  id_owner = request.getParameter("id_owner");
                String  brand_car = request.getParameter("brand_car");
                String  model_car = request.getParameter("model_car");
                String  year_issue = request.getParameter("year_issue");
                String  date_posting = request.getParameter("date_posting");
                String  color = request.getParameter("color");
                String  mileage = request.getParameter("mileage");
                String  engine = request.getParameter("engine");
                String  body_type = request.getParameter("body_type");
                String  gearBox_type = request.getParameter("gearBox_type");
                String  driveUnit_type = request.getParameter("driveUnit_type");
                String  rudder_type = request.getParameter("rudder_type");
                String  condition_type = request.getParameter("condition_type");
                String  image = request.getParameter("image");
                String  cost = request.getParameter("cost");

                if (
                        id != null
                        && id_owner != null
                        && brand_car != null
                        && model_car != null
                        && year_issue != null
                        && date_posting != null
                        && color != null
                        && mileage != null
                        && engine != null
                        && body_type != null
                        && gearBox_type != null
                        && driveUnit_type != null
                        && rudder_type != null
                        && condition_type != null
                        && image != null
                        && cost != null
                ) {
                    Car newCar = new Car(
                            Integer.parseInt(id),
                            carsDAO.getUserFromListOfUsers(id_owner),
                            brand_car,
                            model_car,
                            year_issue,
                            date_posting,
                            color,
                            mileage,
                            engine,
                            body_type,
                            gearBox_type,
                            driveUnit_type,
                            rudder_type,
                            condition_type,
                            image,
                            Integer.parseInt(cost)
                    );

                    carsDAO.addCarsToBD(newCar);
                    response.sendRedirect("/post_car?type=ok");
                }
                else {
                    response.sendRedirect("/post_car?completed=false");
                }
            }
            catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("current_user");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

//        if (user != null) {
            try {
                template = cfg.getTemplate("addNewPost.ftl");
                template.process(null, writer);
            }
            catch (TemplateException e) {
                e.printStackTrace();
            }
//        }
//        else {
//            response.sendRedirect("/login");
//        }
    }
}
