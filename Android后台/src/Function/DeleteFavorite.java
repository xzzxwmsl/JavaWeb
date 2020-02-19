package Function;

import Bean.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteFavorite", urlPatterns = "/DeleteFavorite")
public class DeleteFavorite extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String result="fail";
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            String username = request.getParameter("username");
            String cid = request.getParameter("cid");

            Statement statement = conn.createStatement();
            statement.close();

            String sql = "delete from favorite where username = ? and cid = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,cid);
            System.out.println(sql);
            preparedStatement.executeUpdate();
            result="success";
            preparedStatement.close();
            conn.close();


        } catch (Exception e) {

        }

        JSONObject json=new JSONObject();
        response.setContentType("text/html; charset=UTF-8");
        try {
            json.put("status",result);
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
        doPost(request, response);
    }
}
