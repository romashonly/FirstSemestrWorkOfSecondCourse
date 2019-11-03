package Servlets;

import DAO.CarsDAO;
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        Configuration cfg = (Configuration) getServletContext().getAttribute("cfg");
        Template template;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        CarsDAO carsDAO = new CarsDAO();

        try {

            if (user != null) {
                template = cfg.getTemplate("catalogUser.ftl");
            } else {
                template = cfg.getTemplate("catalogAnonim.ftl");
            }

            Map<String, Object> root = new HashMap<>();
            root.put("cars", carsDAO.getAllCars());

            template.process(root, writer);
        } catch (TemplateException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
