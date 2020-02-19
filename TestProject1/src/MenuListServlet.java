import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONException;

@WebServlet(name = "MenuListServlet", urlPatterns = "/MenuList")
public class MenuListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
//        System.out.println(user);
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true";
            // 数据库用户名
            String username = "xzz";
            // 数据库密码
            String password = "19981003xzz";
            // 创建Connection连接
            Connection conn = DriverManager.getConnection(url, username, password);
            // 添加图书信息的SQL语句
            String sql = "select * from project_tree";
            // 获取Statement
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Map<String, JSONObject> map = new HashMap<>();
            while (rs.next()) {
                JSONObject json = new JSONObject();
                String categoryId = rs.getString("CATEGORY_ID");
                json.put("name", rs.getString("MODULE_NAME"));
                json.put("href", rs.getString("HREF"));
                json.put("type", rs.getString("TYPE"));
                json.put("class", rs.getString("CLASS"));
                json.put("icon", rs.getString("ICON"));
                json.put("categoryId", categoryId);
                json.put("parentId", rs.getString("PARENT_CATEGORY_ID"));
                json.put("htmlId", rs.getString("HTML_ID"));
                map.put(categoryId, json);
            }
            rs.close();
            st.close();
            conn.close();
            for (JSONObject json : map.values()) {
                String parentId = json.getString("parentId");
                JSONObject parent = map.get(parentId);
                if (parent != null) {
                    parent.append("childs", json);
                }
            }
            JSONObject json = map.get("0");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(json);
            response.getWriter().flush();
            System.out.println(json);
        } catch (ClassNotFoundException | SQLException | JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
