<%@ page contentType="text/html; charset=UTF-8"
         import="java.sql.*,java.io.*,Bean.*"%>
<%
    String userid = (String)session.getAttribute("Id");
    User LoginUser = new User();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException classnotfoundexception) {
        classnotfoundexception.printStackTrace();
    }
    try {
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/lbs?user=xzz&password=xzzxwmsl&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true");
        Statement statement = conn.createStatement();
        ResultSet rs = statement
                .executeQuery("select * from user where UserId = '" + userid +"'");

        while(rs.next()){
            LoginUser.setUserId(rs.getString("UserId"));
            LoginUser.setGrade(rs.getString("Grade"));
            LoginUser.setSdept(rs.getString("Sdept"));
            LoginUser.setSex(rs.getString("Sex"));
            LoginUser.setName(rs.getString("Name"));
            LoginUser.setLoanBook(rs.getInt("LoanBook"));
            System.out.println("XXXXXXXXXXXXXXYYYYYYYYYYY"+LoginUser.getUserId());
        }
        statement.close();
        conn.close();
    }catch (SQLException sqlexception) {
        sqlexception.printStackTrace();
    }
%>