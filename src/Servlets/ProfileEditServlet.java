package Servlets;

import DAO.UsersDAO;
import Helpers.Helper;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class ProfileEditServlet extends HttpServlet {

    UsersDAO usersDAO = new UsersDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        Part p = request.getPart("photo");
        if (p.getSubmittedFileName() != null && !p.getSubmittedFileName().equals("")) {
            String localdir = "uploads/avatars";
            String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String[] filename_data = p.getSubmittedFileName().split("\\.");
            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
            String fullpath = pathDir + File.separator + filename;
            p.write(fullpath);

            String avatar = "/" + localdir + "/" + filename;
            user.setAvatar(avatar);
        }

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String passHash = Arrays.toString(messageDigest.digest(request.getParameter("password").getBytes()));

        user.setName(request.getParameter("name"));
        user.setSerName(request.getParameter("ser_name"));
        user.setDate_birth(request.getParameter("age"));
        user.setCity(request.getParameter("city"));
        user.setPhone_number(request.getParameter("phone_number"));
        user.setLogin(request.getParameter("login"));

        user.setPassword(passHash);

        try {
            usersDAO.editUserInfo(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        response.setContentType("text/html");

        Map<String, Object> root = new HashMap<>();

        root.put("user", user);

        Helper.render(request, response, "editProfile.ftl", root);
    }
}
