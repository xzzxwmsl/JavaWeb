package Function;

import Bean.DB;
import Bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "UpdateUserServlet",urlPatterns="/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            User user=new User();
            user.setUserId(request.getParameter("UserId"));
            user.setName(request.getParameter("Name"));
            user.setSex(request.getParameter("Sex"));
            user.setSdept(request.getParameter("Sdept"));
            user.setGrade(request.getParameter("Grade"));
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "update user set Name=?,Sex=?,Sdept=?,Grade=? where UserId=?";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSex());
            preparedStatement.setString(3,user.getSdept());
            preparedStatement.setString(4,user.getGrade());
            preparedStatement.setString(5,user.getUserId());
            System.out.println(user.getUserId()+user.getName()+user.getSdept());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String sql2 = "update loanbook set UserName=? where UserId=?";
            PreparedStatement preparedStatement1=conn.prepareStatement(sql2);
            preparedStatement1.setString(1,user.getName());
            preparedStatement1.setString(2,user.getUserId());
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
        }

        request.getRequestDispatcher("jsp/Operation/OperationSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
