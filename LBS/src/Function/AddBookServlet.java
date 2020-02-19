package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.*;
@WebServlet(name = "AddBookServlet", urlPatterns = "/AddBook")
public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Book book=new Book();
            java.sql.Date date=java.sql.Date.valueOf(request.getParameter("PublishDate"));
            book.setPublish(request.getParameter("Publish"));
            book.setType(request.getParameter("Type"));
            book.setNote(request.getParameter("Note"));
            book.setNumber(request.getParameter("Number"));
            book.setAuthor(request.getParameter("Author"));
            book.setName(request.getParameter("Name"));
            book.setId(request.getParameter("Id"));
            book.setPublishDate(request.getParameter("PublishDate"));
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句

            String sql1 = "insert into booklist(Id,Name,Number,Picture,Note,Author,IfBorrowed,Type,Publish,PublishDate) values(?,?,?,?,?,?,?,?,?,?)";
            // 获取Statement
            PreparedStatement preparedStatement=conn.prepareStatement(sql1);

            preparedStatement.setString(1,book.getId());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setString(3,book.getNumber());
            preparedStatement.setString(4,null);
            preparedStatement.setString(5,book.getNote());
            preparedStatement.setString(6,book.getAuthor());
            preparedStatement.setInt(7,0);
            preparedStatement.setString(8,book.getType());
            preparedStatement.setString(9,book.getPublish());
            preparedStatement.setDate(10,date);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (Exception  e) {
            e.printStackTrace();
            request.setAttribute("Reason","ID重复");
            request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
        }

        request.getRequestDispatcher("jsp/Operation/OperationSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
