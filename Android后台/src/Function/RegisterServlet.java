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

@WebServlet(name = "RegisterServlet", urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String result = "fail";
        String username = new String(request.getParameter("username").getBytes(ISO_8859_1), UTF_8);
        String password = new String(request.getParameter("password").getBytes(ISO_8859_1), UTF_8);
        String tel = new String(request.getParameter("tel").getBytes(ISO_8859_1), UTF_8);
        String sex = new String(request.getParameter("sex").getBytes(ISO_8859_1), UTF_8);
        try {
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "insert into user(username,password,sex,tel,type) values(?,?,?,?,?)";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,sex);
            preparedStatement.setString(4,tel);
            preparedStatement.setString(5,"USER");
            preparedStatement.executeUpdate();
            result="success";
            preparedStatement.close();
            conn.close();
        } catch (Exception  e) {
            e.printStackTrace();
            request.setAttribute("Reason","ID重复");
        }

        JSONObject json=new JSONObject();
        response.setContentType("text/html; charset=UTF-8");
        try {
            json.put("status",result);
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
