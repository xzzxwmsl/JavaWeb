package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.*;

@WebServlet(name = "FindUserServlet",urlPatterns = "/FindUser")
public class FindUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql="";
            String action = request.getParameter("action");
            String UserId = request.getParameter("UserId");

            if(action.equals("1")){
                String Name=request.getParameter("Name");
                String Sex=request.getParameter("Sex");
                String Sdept=request.getParameter("Sdept");
                String Grade=request.getParameter("Grade");

                if(UserId==null) UserId="'%%'";
                else UserId="'%"+UserId+"%'";

                if(Name==null) Name="'%%'";
                else Name="'%"+Name+"%'";

                if(Sex==null) Sex="'%%'";
                else Sex="'%"+Sex+"%'";

                if(Sdept==null) Sdept="'%%'";
                else Sdept="'%"+Sdept+"%'";

                if(Grade==null) Grade="'%%'";
                else Grade="'%"+Grade+"%'";

                sql = "select * from user where UserId like "+UserId+" and Name like "+ Name +" and Sex like "+Sex+" and Sdept like "+Sdept+" and Grade like "+Grade;
            }else sql="select * from user where UserId = " +UserId;

            // 获取Statement
            System.out.println(sql);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<User> list = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User();
                user.setGrade(resultSet.getString("Grade"));
                user.setLoanBook(resultSet.getInt("LoanBook"));
                user.setName(resultSet.getString("Name"));
                user.setSdept(resultSet.getString("Sdept"));
                user.setSex(resultSet.getString("Sex"));
                user.setUserId(resultSet.getString("UserId"));
                System.out.println(user);
                list.add(user);
            }
            System.out.println("XXX");
            request.setAttribute("list", list);
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("jsp/Manager/Search.jsp")
                .forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
