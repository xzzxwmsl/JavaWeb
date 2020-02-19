package Function;

import Bean.DB;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateUser", urlPatterns = "/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加信息的SQL语句
            String sql = "";

            PreparedStatement preparedStatement;

            String username = request.getParameter("username");
            String tel = request.getParameter("tel");
            String sex = request.getParameter("sex");

            String flag = "fail";

            flag = "success";
            sql = "UPDATE user SET tel=?,sex=? WHERE username=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tel);
            preparedStatement.setString(2, sex);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            preparedStatement.close();


            //构造返回json
            JSONObject jsonObject = new JSONObject();
            response.setContentType("text/html; charset=UTF-8");
            try {
                jsonObject.put("status", flag);
                response.getWriter().print(jsonObject);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
