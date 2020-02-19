package Function;

import Bean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteUserServlet",urlPatterns = "/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            User user = new User();

            user.setUserId(request.getParameter("UserId"));

            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql = String.format("SELECT COUNT(*) as loannumber FROM loanbook WHERE UserId = '%s'", user.getUserId());
            System.out.println(sql);
            // 获取Statement
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String flag = resultSet.getString("loannumber");
            System.out.println(sql+" flag");
            resultSet.close();
            statement.close();

            if(flag.equals("0")){
                sql = "delete from user where UserId= ? ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,user.getUserId());
                System.out.println(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();

                sql = "delete from login where Id= ? ";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql);
                preparedStatement2.setString(1,user.getUserId());
                System.out.println(sql);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();

                conn.close();
            }else {
                conn.close();
                request.setAttribute("Reason","该读者有借书未还，不能删除");
                request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);

            }

        } catch (Exception  e) {
            e.printStackTrace();
            request.setAttribute("Reason","出错");
            request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
        }

        request.getRequestDispatcher("jsp/Operation/OperationSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
