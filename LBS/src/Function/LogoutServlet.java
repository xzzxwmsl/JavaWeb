package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isLogin = (Boolean)session.getAttribute("isLogin");
        String username = (String) session.getAttribute("username");
        String UserType = (String) session.getAttribute("UserType");
        session.setAttribute("isLogin", false);
        session.removeAttribute("username");
        session.removeAttribute("UserType");
        response.sendRedirect("index.jsp");
    }
}
