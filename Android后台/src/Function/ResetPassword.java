package Function;

import Bean.DB;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(name = "ResetPassword",urlPatterns="/Reset")
public class ResetPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String flag = "fail";
        String username = new String(request.getParameter("username").getBytes(ISO_8859_1), UTF_8);
        String password = new String(request.getParameter("password").getBytes(ISO_8859_1), UTF_8);
        try {
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "update user set password = ? where username = ?";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            System.out.println(sql1);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,username);
            flag="success";
            preparedStatement.executeUpdate();
            preparedStatement.close();


            conn.close();

        } catch (Exception e) {
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
