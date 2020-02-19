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

@WebServlet(name = "Address", urlPatterns = "/Address")
public class Address extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql="";
            PreparedStatement preparedStatement;

            String action=request.getParameter("action");
            String username=request.getParameter("username");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");

            String flag="fail";

            if(action.equals("add")){//增加地址
                flag="success";
                sql = "insert into address(username,address,tel) values(?,?,?)";
                preparedStatement=conn.prepareStatement(sql);

                preparedStatement.setString(1,username);
                preparedStatement.setString(2,address);
                preparedStatement.setString(3,tel);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            else if (action.equals("delete")){//删除
                flag="success";
                sql = "delete from address where username =? and address =? and tel=?";

                preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,address);
                preparedStatement.setString(3,tel);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            else {
                response.getWriter().print("{'status':'error'}");
                response.getWriter().flush();
                response.getWriter().close();
            }


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
