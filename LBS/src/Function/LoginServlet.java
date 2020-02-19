package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;

import Bean.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String error = "";
        String Type="";
        String username = new String(request.getParameter("username").getBytes(ISO_8859_1), UTF_8);
        String password = new String(request.getParameter("password").getBytes(ISO_8859_1), UTF_8);
        try {
            Class.forName(DB.driver);Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            Statement st = conn.createStatement();
            String query = String.format("select * from login where Id = '%s' and Password = '%s'", username, password);
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                throw new RuntimeException("用户名或密码错误");
            }
            Type=rs.getString("Type");
            System.out.println("XXXXXXX"+Type);
        } catch (ClassNotFoundException | SQLException | RuntimeException e) {
            error = e.getMessage();
            e.printStackTrace();
        }


        if (error.equals("")) {
            session.setAttribute("isLogin", true);
            session.setAttribute("Id", username);
            session.setAttribute("UserType",Type);
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("isLogin", false);
            response.sendRedirect("jsp/handle_error.jsp?error=" + URLEncoder.encode(error, "UTF-8") + "&redirect=index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
