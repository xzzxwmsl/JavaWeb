package Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bean.*;
import org.apache.catalina.Session;

@WebServlet(name = "FindLoanServlet",urlPatterns = "/FindLoan")
public class FindLoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
        String UserId = request.getParameter("UserId");
        HttpSession session = request.getSession(true);
        String userid = (String)session.getAttribute("Id");
        System.out.println(userid);
        String sql="";
        if(action.equals("1") || action.equals("3")) sql="select * from loanbook ";
        else if(action.equals("2")) sql="select * from loanbook where UserId = "+UserId;
        else if(action.equals("4")) sql="select * from loanbook where UserId = " + userid ;
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            //if(UserId==null||UserId.equals("")) UserId="'%%'";
            //else UserId="'%"+UserId+"%'";
            // 获取Statement
            System.out.println(sql);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            List<LoanBook> list = new ArrayList<LoanBook>();

            java.util.Date date=new java.util.Date();

            while (resultSet.next()) {
                LoanBook loanBook=new LoanBook();
                loanBook.setId(resultSet.getString("Id"));
                loanBook.setName(resultSet.getString("Name"));
                loanBook.setUserId(resultSet.getString("UserId"));
                loanBook.setUserName(resultSet.getString("UserName"));
                Date LoanDate=resultSet.getDate("LoanDate");
                Date ReturnDate=resultSet.getDate("ReturnDate");
                loanBook.setLoanDate(LoanDate);
                loanBook.setReturnDate(ReturnDate);
                if(ReturnDate.before(date)){
                    loanBook.setFlag(1);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int day1=calendar.get(Calendar.DAY_OF_YEAR);
                    calendar.setTime(ReturnDate);
                    int day2=calendar.get(Calendar.DAY_OF_YEAR);
                    loanBook.setLoanDays(day1-day2);
                }
                else {
                    loanBook.setFlag(0);
                    loanBook.setLoanDays(0);
                }
               // System.out.println("AAAAA"+loanBook.getFlag());
                if(action.equals("3")){
                    if(ReturnDate.before(date)) list.add(loanBook);
                }else list.add(loanBook);
            }
            //System.out.println("XXX");
            request.setAttribute("list", list);
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!action.equals("4")){
            request.getRequestDispatcher("jsp/Manager/LoanUser.jsp")
                    .forward(request, response);
        }else {
            request.getRequestDispatcher("jsp/Request/ReturnList.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
