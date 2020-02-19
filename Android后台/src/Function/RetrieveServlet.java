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
import org.json.JSONException;
import org.json.JSONObject;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(name = "RetrieveServlet", urlPatterns = "/Retrieve")
public class RetrieveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String flag = "";
        String username = new String(request.getParameter("username").getBytes(ISO_8859_1), UTF_8);
        String tel = new String(request.getParameter("tel").getBytes(ISO_8859_1), UTF_8);
        try {
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            Statement st = conn.createStatement();
            String query = String.format("select * from user where username = '%s' and tel = '%s'", username, tel);
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                flag = "fail";
                throw new RuntimeException("用户名或密码错误");
            }else {
                flag="success";
            }
        } catch ( SQLException | RuntimeException | ClassNotFoundException e) {
            String s= e.getMessage();
            e.printStackTrace();
        }

        JSONObject json=new JSONObject();
        response.setContentType("text/html; charset=UTF-8");
        try {
            json.put("status",flag);
            System.out.println("最后构造得到的json是："+json.toString());
            response.getWriter().print(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
