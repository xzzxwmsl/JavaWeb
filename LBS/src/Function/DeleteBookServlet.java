package Function;

import Bean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteBookServlet",urlPatterns = "/DeleteBook")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Book book=new Book();
            book.setId(request.getParameter("Id"));
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql = String.format("select IfBorrowed from booklist where Id = '%s'", book.getId());
            System.out.println(sql);
            // 获取Statement
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String flag = resultSet.getString("IfBorrowed");
            System.out.println(sql);
            resultSet.close();
            statement.close();

            if(flag.equals("1")){
                conn.close();
                request.setAttribute("Reason","书籍已被借出，尚未归还");
                request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
            }else {
                sql = "delete from booklist where Id= ? ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,book.getId());
                System.out.println(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
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
