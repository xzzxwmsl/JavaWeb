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

@WebServlet(name = "LoanBookServlet",urlPatterns = "/LoanBook")
public class LoanBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");

        if(action.equals("1")){

            User user = new User();
            user.setUserId(request.getParameter("UserId"));
            user.setName(request.getParameter("UserName"));
            user.setLoanBook(Integer.parseInt(request.getParameter("LoanBook")));

            System.out.println(user.getUserId()+user.getName()+user.getLoanBook());
            Book book = new Book();
            book.setId(request.getParameter("Id"));
            book.setName(request.getParameter("BookName"));
            System.out.println(book.getName()+book.getId());

            int flag=0;

            try {
                String sql="select * from loanbook where UserId = '"+ user.getUserId() +"'";
                Class.forName(DB.driver);
                Connection conn = DriverManager.getConnection(DB.url, DB.user,
                        DB.password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                java.util.Date date = new java.util.Date();
                while (resultSet.next()) {
                    LoanBook loanBook=new LoanBook();
                    Date ReturnDate=resultSet.getDate("ReturnDate");
                    loanBook.setReturnDate(ReturnDate);
                    if(ReturnDate.before(date)) flag++;
                }
                resultSet.close();
                statement.close();
                conn.close();

            }catch (Exception e){
                e.printStackTrace();
            }


            if( flag!=0 ){
                request.setAttribute("Reason","有借出的书籍尚未归还，借书失败！");
                request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
            }else {


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
                    preparedStatement.setString(5,"1");
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    conn.close();
                    request.setAttribute("Reason","已提交申请");
                } catch (Exception  e) {
                    e.printStackTrace();
                    request.setAttribute("Reason","已有人在申请本书，请稍后重试");
                    request.getRequestDispatcher("jsp/Operation/OperationFail.jsp").forward(request,response);
                }
            }

        }else if(action.equals("2")){
            Request requestbook = new Request();
            requestbook.setBookId(request.getParameter("Id"));
            String Action = request.getParameter("Action");
            if(Action.equals("1")){
                LoanBook loanBook = new LoanBook();

                try {
                    // 加载数据库驱动，注册到驱动管理器
                    Class.forName(DB.driver);
                    Connection conn = DriverManager.getConnection(DB.url, DB.user,
                            DB.password);
                    // 添加图书信息的SQL语句
                    String sql = "select * FROM requestlist where BookId = '" + requestbook.getBookId() +"' and UserId = '" + request.getParameter("UserId") + "' and Flag = '1'";
                    // 获取Statement
                    System.out.println(sql);
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        loanBook.setId(resultSet.getString("BookId"));
                        loanBook.setName(resultSet.getString("BookName"));
                        loanBook.setUserId(resultSet.getString("UserId"));
                        loanBook.setUserName(resultSet.getString("UserName"));
                        System.out.println(loanBook.getId()+loanBook.getUserId());

                        java.util.Date date=new java.util.Date();
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = s.format(date);
                        java.sql.Date date1=java.sql.Date.valueOf(strDate);
                        loanBook.setLoanDate(date1);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.MONTH,2);
                        loanBook.setReturnDate(java.sql.Date.valueOf(s.format(calendar.getTime())));
                    }
                    resultSet.close();
                    statement.close();

                    System.out.println("1");
                    sql = "delete from requestlist where BookId= ? and Flag = 1";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,loanBook.getId());
                    System.out.println(sql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    System.out.println("11");

                    //插入到借书列表
                    sql = "insert into loanbook(Id,Name,UserId,UserName,LoanDate,ReturnDate) values(?,?,?,?,?,?)";
                    // 获取Statement
                    preparedStatement=conn.prepareStatement(sql);

                    preparedStatement.setString(1,loanBook.getId());
                    preparedStatement.setString(2,loanBook.getName());
                    preparedStatement.setString(3,loanBook.getUserId());
                    preparedStatement.setString(4,loanBook.getUserName());
                    preparedStatement.setDate(5,loanBook.getLoanDate());
                    preparedStatement.setDate(6,loanBook.getReturnDate());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    //修改book list中的是否借出信息
                    sql = "UPDATE booklist SET IfBorrowed=1 WHERE Id=?";
                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,loanBook.getId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    //修改user中的借书数量
                    sql = "UPDATE user SET LoanBook=LoanBook+1 WHERE UserId=?";
                    preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,loanBook.getUserId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    conn.close();
                    request.setAttribute("Reason","已同意借书");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(Action.equals("0")){
                try {
                    Class.forName(DB.driver);
                    Connection conn = DriverManager.getConnection(DB.url, DB.user,
                            DB.password);
                    String sql = "delete from requestlist where BookId= ? ";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,requestbook.getBookId());
                    System.out.println(sql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    request.setAttribute("Reason","已拒绝借书");
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
