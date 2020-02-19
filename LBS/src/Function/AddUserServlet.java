package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bean.*;
@WebServlet(name = "AddUserServlet", urlPatterns = "/AddUser")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            String UserId=new String(request.getParameter("UserId"));
            String Name=new String(request.getParameter("Name"));
            String Sdept=new String(request.getParameter("Sdept"));
            String Grade=new String(request.getParameter("Grade"));
            String Sex=new String(request.getParameter("Sex"));
            String Password=new String(request.getParameter("Password"));

            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "insert into login(Id,Password,Type) values(?,?,?)";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            preparedStatement.setString(1,UserId);
            preparedStatement.setString(2,Password);
            preparedStatement.setString(3,"USER");
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String sql2 = "insert into user(UserId,LoanBook,Name,Sex,Sdept,Grade) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement1=conn.prepareStatement(sql2);
            preparedStatement1.setString(1,UserId);
            preparedStatement1.setInt(2,0);
            preparedStatement1.setString(3,Name);
            preparedStatement1.setString(4,Sex);
            preparedStatement1.setString(5,Sdept);
            preparedStatement1.setString(6,Grade);
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
