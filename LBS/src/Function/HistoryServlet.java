package Function;

import Bean.Book;
import Bean.DB;
import Bean.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HistoryServlet",urlPatterns = "/History")
public class HistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userid = (String)session.getAttribute("Id");
        String usertype =(String)session.getAttribute("UserType");
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql="";
            if(usertype.equals("ADMIN")) sql="select * from history";
            else if(usertype.equals("USER")) sql="select * from history where UserId = " + userid;
            // 获取Statement
            System.out.println(sql);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<History> list = new ArrayList<History>();
            while (resultSet.next()) {

                History book = new History();
                book.setLoanDate(resultSet.getDate("LoanDate"));
                book.setReturnDate(resultSet.getDate("ReturnDate"));
                book.setUserId(resultSet.getString("UserId"));
                book.setUserName(resultSet.getString("UserName"));
                book.setBookId(resultSet.getString("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                System.out.println(book.getBookId());
                list.add(book);

            }
            request.setAttribute("list", list);
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("jsp/History/History.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
