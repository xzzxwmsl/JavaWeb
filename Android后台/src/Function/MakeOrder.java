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
import java.util.Iterator;

import Bean.*;
import org.json.JSONException;
import org.json.JSONObject;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(name = "MakeOrder", urlPatterns = "/MakeOrder")
public class MakeOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String result = "fail";
        String jsondata=request.getParameter("json");
        System.out.println("----------------------------------------------"+jsondata);

        try{
            JSONObject jsonObject= new JSONObject(jsondata);
            String username = jsonObject.getString("username");
            String tel = jsonObject.getString("tel");
            String orderid = jsonObject.getString("orderid");
            String address = jsonObject.getString("address");
            String status = jsonObject.getString("status");

            JSONObject commodity = jsonObject.getJSONObject("commodity");

            System.out.println(commodity.toString());

            Iterator<String> keys = commodity.keys();

            String [] CID =new  String[10];
            String [] NUM =new String[10];
            int index=0;
            while(keys.hasNext()){
                CID[index]=(String) keys.next();
                NUM[index]=commodity.getString(CID[index]);
                index++;
            }

            String cid="",num="";

            for(int i=0;i<index;i++){
                cid+=CID[i];
                num+=NUM[i];
                if(i!=index-1){
                    cid+=",";
                    num+=",";
                }
            }

            //////////////////////////

            try {
                Class.forName(DB.driver);
                Connection conn = DriverManager.getConnection(DB.url, DB.user,
                        DB.password);
                // 添加图书信息的SQL语句

                String sql1 = "insert into commodityorder(orderid,username,cid,num,address,status,tel) values(?,?,?,?,?,?,?)";
                // 获取Statement
                PreparedStatement preparedStatement=conn.prepareStatement(sql1);

                preparedStatement.setString(1,orderid);
                preparedStatement.setString(2,username);
                preparedStatement.setString(3,cid);
                preparedStatement.setString(4,num);
                preparedStatement.setString(5,address);
                preparedStatement.setString(6,status);
                preparedStatement.setString(7,tel);
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

            ////////////////





        }catch (JSONException e){

        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
