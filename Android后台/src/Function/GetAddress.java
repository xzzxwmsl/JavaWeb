package Function;
import Bean.DB;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetAddress", urlPatterns = "/GetAddress")
public class GetAddress extends HttpServlet {
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
            String username=request.getParameter("username");

            String sql = "select * from address where username = '" + username +"'" ;
            // 获取Statement
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List list = new ArrayList();
            while (resultSet.next()) {

                Map map = new HashMap();
                map.put("address",resultSet.getString("address"));
                map.put("username",resultSet.getString("username"));
                map.put("tel",resultSet.getInt("tel"));
                list.add(map);

            }
            //获取完毕

            //构造返回json
            JSONObject jsonObject = new JSONObject();
            response.setContentType("text/html; charset=UTF-8");
            try {
                jsonObject.put("status","success");
                jsonObject.put("value",list);

                response.getWriter().print(jsonObject);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }

            resultSet.close();
            statement.close();
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
