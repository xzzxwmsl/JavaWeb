<%@page contentType="text/html; charset=UTF-8"
	import="java.sql.*,java.io.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
			try {
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/test?user=ylx&password=ylx&useUnicode=true&characterEncoding=UTF-8");
				Statement statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery("select * from pipeline");
				//如果查询有结果，则循环显示查询出来的记录
                       out.println("<table border=\"1\">"); 
                       out.println("<tr>"); 
                       out.println("<th>ID</th>");   
                       out.println("<th>管道状态</th>");
                       out.println("<th>在用状态</th>"); 
                       out.println("<th>类型</th>"); 
                       out.println("<th>创建时间</th>"); 
                       out.println("<th>操作</th>");
                       out.println("</tr>");
				while (rs.next()) {
                                        out.println("<tr>");
					out.println("</td><td>");
					out.println(rs.getString("ID"));
					out.println("</td><td>"); 
					out.println(rs.getString("Status"));
					out.println("</td><td>"); 
					out.println(rs.getString("Use_Status"));
					out.println("</td><td>"); 
					out.println(rs.getString("Type"));
					out.println("</td><td>"); 
					out.println(rs.getString("CreateTime"));
					out.println("</td><td>"); 
					out.println("<a href=\"DeleteOK.jsp?id="
							+ rs.getString("ID") + "\"" + ">删除</a>");
					out.println("</td></tr>");
				}
                       out.println("</table>");
				out.println("<br>");
				statement.close();
				conn.close();
				out.println("Database Closed！！！");
			} catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
			}
		%>
		<input type="button" value="添加" onclick="add()"/>
		<input type="button" value="修改" onclick="modify()"/>
		<input type="button" value="查询" onclick="query()"/>
		<input type="button" value="删除" onclick="deleteit()"/>
	</body>
</html>
<script>
function add(){
	window.location="Add.jsp";
}
function modify(){
	window.location="Rewrite.jsp";
}
function query(){
	window.location="Search.jsp";
}
function deleteit(){
	window.location="Delete.jsp";
}
</script>