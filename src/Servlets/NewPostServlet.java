package Servlets;

import DAO.CarsDAO;
import DAO.UsersDAO;
import Helpers.Helper;
import Models.Car;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class NewPostServlet extends HttpServlet {

    private CarsDAO carsDAO = new CarsDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            try {
                int  id = carsDAO.getAllCars().size();
                int  id_owner = user.getId();
                String  brand_car = request.getParameter("brand_car");
                String  model_car = request.getParameter("model_car");
                String  year_issue = request.getParameter("year_issue");
//                String  date_posting = request.getParameter("date_posting");
                String  date_posting = "now";
                String  color = request.getParameter("color");
                String  mileage = request.getParameter("mileage");
                String  engine = request.getParameter("engine");
                String  body_type = request.getParameter("body_type");
                String  gearBox_type = request.getParameter("gearBox_type");
                String  driveUnit_type = request.getParameter("driveUnit_type");
                String  rudder_type = request.getParameter("rudder_type");
                String  condition_type = request.getParameter("condition_type");

                Part p = request.getPart("image");
                String localdir = "uploads/cars";
                String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
                File dir = new File(pathDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                String[] filename_data = p.getSubmittedFileName().split("\\.");
                String filename = Math.random() + "." + filename_data[filename_data.length - 1];
                String fullpath = pathDir + File.separator + filename;
                p.write(fullpath);

                String image = "/" + localdir + "/" + filename;

                String  cost = request.getParameter("cost");

                if (
                        brand_car != null
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
                        && cost != null
                ) {
                    Car newCar = new Car(
                            id,
                            carsDAO.getUserFromListOfUsers(Integer.toString(id_owner)),
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
                    response.sendRedirect("/my_posts");
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

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        Map<String, Object> root = new HashMap<>();
        Helper.render(request, response, "addNewPost.ftl", root);
    }
}
