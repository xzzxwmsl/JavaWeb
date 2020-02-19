package Function;
import Bean.Book;
import Bean.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TotalBook")
public class TotalBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql = "select *,COUNT(Number) AS TotalNum,Sum(IfBorrowed) AS BorrowedNum FROM booklist GROUP BY Number";
            // 获取Statement
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<Book> list = new ArrayList<Book>();
            while (resultSet.next()) {

                Book book = new Book();
                book.setName(resultSet.getString("Name"));
                book.setNumber(resultSet.getString("Number"));
                book.setPicture(resultSet.getString("Picture"));
                book.setAuthor(resultSet.getString("Author"));
                book.setPublish(resultSet.getString("Publish"));
                book.setPublishDate(resultSet.getString("PublishDate"));
                book.setNote(resultSet.getString("Note"));
                book.setType(resultSet.getString("Type"));
                book.setTotalNum(resultSet.getInt("TotalNum"));
                book.setBorrowedNum(resultSet.getInt("BorrowedNum"));
                System.out.println(book);
                list.add(book);

            }
            request.setAttribute("list", list);
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("jsp/Book/Search.jsp")
                .forward(request, response);

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
