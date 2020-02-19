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

@WebServlet(name = "UpdateOrder", urlPatterns = "/UpdateOrder")
public class UpdateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql="";
            String action=request.getParameter("action");

            PreparedStatement preparedStatement;

            String orderid = request.getParameter("orderid");

            String flag="fail";

            //该购物车项加一
                flag="success";
                sql = "UPDATE commodityorder SET status=? WHERE orderid =?";
                preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,action);
                preparedStatement.setString(2,orderid);
                preparedStatement.executeUpdate();
                preparedStatement.close();



            //构造返回json
            JSONObject jsonObject = new JSONObject();
            response.setContentType("text/html; charset=UTF-8");
            try {
                jsonObject.put("status",flag);
                response.getWriter().print(jsonObject);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
