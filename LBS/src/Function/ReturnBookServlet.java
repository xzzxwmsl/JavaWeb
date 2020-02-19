package Function;

import Bean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "ReturnBookServlet", urlPatterns = "/ReturnBook")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");

        if(action.equals("1")){

            User user = new User();
            user.setUserId(request.getParameter("UserId"));
            user.setName(request.getParameter("UserName"));

            System.out.println(user.getUserId()+user.getName());
            Book book = new Book();
            book.setId(request.getParameter("BookId"));
            book.setName(request.getParameter("BookName"));
            System.out.println(book.getName()+book.getId());

            try {
                    Class.forName(DB.driver);
                    Connection conn = DriverManager.getConnection(DB.url, DB.user,
                            DB.password);

                    String sql = "insert into requestlist(BookId,BookName,UserId,UserName,Flag) values(?,?,?,?,?)";
                    // 获取Statement
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,book.getId());
                    preparedStatement.setString(2,book.getName());
                    preparedStatement.setString(3,user.getUserId());
                    preparedStatement.setString(4,user.getName());
                    preparedStatement.setString(5,"2");
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    conn.close();
                    request.setAttribute("Reason","已提交申请");
                } catch (Exception  e) {
                    e.printStackTrace();
                    request.setAttribute("Reason","已申请归还本书，请等待审核");
                    request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
                }


        }else if(action.equals("2")){
            Request requestbook = new Request();
            requestbook.setBookId(request.getParameter("BookId"));
            requestbook.setUserId(request.getParameter("UserId"));
            String Action = request.getParameter("Action");
            if(Action.equals("1")){

                try {
                    // 加载数据库驱动，注册到驱动管理器
                    Class.forName(DB.driver);
                    Connection conn = DriverManager.getConnection(DB.url, DB.user,
                            DB.password);
                    // 添加图书信息的SQL语句
                   String sql = "";


                    System.out.println("1");
                    sql = "delete from requestlist where BookId= ? and Flag = 2";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getBookId());
                    System.out.println(sql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    System.out.println("11");

                    //插入到history列表
                    History history = new History();

                    sql="select * from loanbook where Id = '" + requestbook.getBookId() +"'";

                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        history.setBookId(resultSet.getString("Id"));
                        history.setBookName(resultSet.getString("Name"));
                        history.setUserId(resultSet.getString("UserId"));
                        history.setUserName(resultSet.getString("UserName"));
                        history.setLoanDate(resultSet.getDate("LoanDate"));

                        java.util.Date date=new java.util.Date();
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = s.format(date);
                        java.sql.Date date1=java.sql.Date.valueOf(strDate);
                        history.setReturnDate(date1);
                    }
                    statement.close();
                    resultSet.close();

                    sql = "insert into history(BookId,BookName,UserId,UserName,LoanDate,ReturnDate) values(?,?,?,?,?,?)";
                    preparedStatement=conn.prepareStatement(sql);

                    preparedStatement.setString(1,history.getBookId());
                    preparedStatement.setString(2,history.getBookName());
                    preparedStatement.setString(3,history.getUserId());
                    preparedStatement.setString(4,history.getUserName());
                    preparedStatement.setDate(5,history.getLoanDate());
                    preparedStatement.setDate(6,history.getReturnDate());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    //修改book list中的是否借出信息
                    sql = "UPDATE booklist SET IfBorrowed=0 WHERE Id=?";
                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getBookId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    //修改user中的借书数量
                    sql = "UPDATE user SET LoanBook=LoanBook-1 WHERE UserId=?";
                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getUserId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    sql = "delete from loanbook where Id= ? ";
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getBookId());
                    System.out.println(sql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    conn.close();
                    request.setAttribute("Reason","已同意还书");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(Action.equals("0")){
                try {
                    Class.forName(DB.driver);
                    Connection conn = DriverManager.getConnection(DB.url, DB.user,
                            DB.password);
                    String sql = "delete from requestlist where BookId= ? and Flag = '2' ";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getBookId());
                    System.out.println(sql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    request.setAttribute("Reason","已拒绝还书");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        request.getRequestDispatcher("jsp/Operation/OperationSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
