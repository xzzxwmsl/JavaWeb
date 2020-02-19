package Function;

import Bean.DB;
import Bean.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "UpdateBookServlet",urlPatterns="/UpdateBook")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Book book=new Book();
            book.setId(request.getParameter("Id"));
            book.setName(request.getParameter("Name"));
            book.setAuthor(request.getParameter("Author"));
            book.setPublish(request.getParameter("Publish"));
            book.setPublishDate(request.getParameter("PublishDate"));
            book.setType(request.getParameter("Type"));
            book.setNote(request.getParameter("Note"));
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "update booklist set Name=?,Author=?,Publish=?,Type=?, Note=?, PublishDate=? where Id = ?";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            System.out.println(sql1);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getPublish());
            preparedStatement.setString(4,book.getType());
            preparedStatement.setString(5,book.getNote());
            preparedStatement.setString(6,book.getPublishDate());
            preparedStatement.setString(7,book.getId());
            System.out.println(book.getPublishDate()+book.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String sql2 = "update loanbook set Id=?,Name=? where Id=?";
            PreparedStatement preparedStatement1=conn.prepareStatement(sql2);
            preparedStatement1.setString(1,book.getId());
            preparedStatement1.setString(2,book.getName());
            preparedStatement1.setString(3,book.getId());
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
