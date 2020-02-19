package Function;
import Bean.Book;
import Bean.DB;
import Bean.User;

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

@WebServlet("/FindBook")
public class FindBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *   response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName(DB.driver);
            Connection conn = DriverManager.getConnection(DB.url, DB.user,
                    DB.password);
            // 添加图书信息的SQL语句
            String sql="";
            String action=request.getParameter("action");

            if(action.equals("1")){
                String date11=request.getParameter("PublishDate1");

                if(date11.equals("")||date11==null) date11="1950-01-01";

                String date22=request.getParameter("PublishDate2");
                if(date22.equals("")||date22==null) date22="2950-01-01";

                java.sql.Date date1=java.sql.Date.valueOf(date11);
                java.sql.Date date2=java.sql.Date.valueOf(date22);
                String Name="'%"+request.getParameter("Name")+"%'";
                String Number="'%"+request.getParameter("Number")+"%'";
                String Author="'%"+request.getParameter("Author")+"%'";
                String Publish="'%"+request.getParameter("Publish")+"%'";
                String Note="'%"+request.getParameter("Note")+"%'";
                sql = "select * from booklist where Name like "+Name+" and Number like"+Number+" and Author like"+Author+" and Publish like"+Publish+" and Note like"+Note+" and PublishDate between '"+date1+"' and  '"+date2+"'";
            }
            else if (action.equals("2")) sql="select * from booklist";
        // 获取Statement
            System.out.println(sql);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<Book> list = new ArrayList<Book>();
            while (resultSet.next()) {

                Book book = new Book();
                book.setId(resultSet.getString("Id"));
                book.setName(resultSet.getString("Name"));
                book.setNumber(resultSet.getString("Number"));
                book.setPicture(resultSet.getString("Picture"));
                book.setAuthor(resultSet.getString("Author"));
                book.setIfBorrowed(resultSet.getInt("IfBorrowed"));
                book.setNote(resultSet.getString("Note"));
                book.setType(resultSet.getString("Type"));
                book.setPublish(resultSet.getString("Publish"));
                book.setPublishDate(resultSet.getString("PublishDate"));
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

        request.getRequestDispatcher("jsp/Book/Books.jsp")
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
