import DAO.UsersDAO;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class FilterClass implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User) session.getAttribute("current_user");

        if (user != null) {
            chain.doFilter(req, resp);
        }
        else {

            Cookie[] cookies = ((HttpServletRequest) req).getCookies();

            String login = null;
            String password = null;

            UsersDAO usersDAO = new UsersDAO();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                }
                else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }

            if (login != null && password != null) {
                try {
                    session.setAttribute("current_user", usersDAO.getUserByLogin(login, password));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                chain.doFilter(req, resp);
            }
            else {
                ((HttpServletResponse) resp).sendRedirect("/login");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
