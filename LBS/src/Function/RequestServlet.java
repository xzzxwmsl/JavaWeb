package Function;
import Bean.Book;
import Bean.DB;
import Bean.Request;

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

@WebServlet(name = "RequestServlet",urlPatterns = "/Request")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        try {
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            String sql = "select * FROM requestlist where Flag = " + action;
            System.out.println(sql);
            // 获取Statement
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<Request> list = new ArrayList<Request>();
            while (resultSet.next()) {

                Request book = new Request();
                book.setBookId(resultSet.getString("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                book.setUserId(resultSet.getString("UserId"));
                book.setUserName(resultSet.getString("UserName"));
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

       if(action.equals("1")){
           request.getRequestDispatcher("jsp/Request/LoanRequest.jsp")
                   .forward(request, response);
       }else if(action.equals("2")){
           request.getRequestDispatcher("jsp/Request/ReturnRequest.jsp")
                   .forward(request, response);
       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
