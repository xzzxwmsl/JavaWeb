<%@page contentType="text/html; charset=UTF-8" language="java"
        import="java.text.*,org.json.JSONObject,java.util.ArrayList,java.io.PrintWriter"
        import="java.util.HashMap,java.util.List,java.sql.*,java.util.Map,java.io.IOException"%>
<%

    response.setContentType("text/xml;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Cache-Control", "no-cache");
    String username=request.getParameter("username");
    String pwd=request.getParameter("pwd");
    String status="fail";
    //开始查询数据库
    List jsonList = new ArrayList();
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException classnotfoundexception) {
        classnotfoundexception.printStackTrace();
        System.out.println("__");
    }
    try {
        final String url = "jdbc:mysql://localhost:3306/android?user=Android&password=Android=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true&ONLY_FULL_GROUP_BY=false";
        System.out.println("_A_");
        Connection conn = DriverManager.getConnection(url);
        System.out.println("_B_");
        Statement statement = conn.createStatement();
        System.out.println("_C_");
        System.out.println("连接数据库Ok！！！");
        //构造sql语句，根据传递过来的查询条件参数，目前是deviceId和gpsTime
        String sql="select * from user where username='"+username+"' and password='"+pwd+"'";
        System.out.println("构造出来的sql语句是："+sql);
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            status="sucess";
        }else {
            status="fail";
        }
        statement.close();
        conn.close();
        System.out.println("数据库关闭了！！！");
    } catch (SQLException sqlexception) {
        sqlexception.printStackTrace();
    }
    //////////数据库查询完毕，得到了json数组jsonList//////////
    //下面开始构建返回的json
    JSONObject json=new JSONObject();
    json.put("status",status);
    System.out.println("最后构造得到的json是："+json.toString());
    response.setContentType("text/html; charset=UTF-8");
    try {
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("返回结果给调用页面了。");
%>
