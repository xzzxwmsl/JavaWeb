<%@page contentType="text/html; charset=UTF-8"
	import="java.sql.*,java.io.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			String id = request.getParameter("id");
			request.setCharacterEncoding("UTF-8");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
			try {
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/test?user=ylx&password=ylx&useUnicode=true&characterEncoding=UTF-8");
				Statement statement = conn.createStatement();
				String sql = "delete from pipeline where ID=" + id;
				statement.executeUpdate(sql);
				statement.close();
				conn.close();
		%>
删除成功！请返回。
<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
		<%
			} catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
		%>
出现错误，请看Console提示！请返回。
<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
		<%
			}
		%>
	</body>
</html>
