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

@WebServlet(name = "UpdateCart", urlPatterns = "/UpdateCart")
public class UpdateCart extends HttpServlet {
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

            String username=request.getParameter("username");
            String cid = request.getParameter("cid");

            String flag="fail";
            if(action.equals("add")){//该购物车项加一
                flag="success";

                String t="fail";
                Statement st = conn.createStatement();
                String query = String.format("select * from cart where username = '%s' and cid = '%s'", username, cid);
                ResultSet rs = st.executeQuery(query);
                if (!rs.next()) {
                    t = "fail";//购物车中没有这个商品
                }else {
                    t="success";//有这个商品
                }
                rs.close();

                if(t.equals("success")){
                    flag="success";
                    sql = "UPDATE Cart SET num=num+1 WHERE username=? and cid =?";
                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,cid);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }else {

                    String src="";
                    //获取src路径
                    {
                        st=conn.createStatement();
                        query = String.format("select * from commodity where cid = '%s'", cid);
                        rs = st.executeQuery(query);
                        if (!rs.next()) {
                            src = "Pictures/Commodity/NULL.jpg";//没有这个商品
                        }else {
                            src=rs.getString("src");//有这个商品
                        }
                        st.close();
                    }
                    //////////

                    sql="insert into cart(username,cid,num,src) values(?,?,?,?)";

                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,cid);
                    preparedStatement.setInt(3,1);
                    preparedStatement.setString(4,src);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                }

            }
            else if (action.equals("minus")){//该购物车数量减一
                flag="success";
                sql = "UPDATE Cart SET num=num-1 WHERE username=? and cid =?";
                preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,cid);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }else if(action.equals("delete")){//从购物车中删除该商品
                flag= "success";
                sql = "delete from cart where username =? and cid =?";
                preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,cid);
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
