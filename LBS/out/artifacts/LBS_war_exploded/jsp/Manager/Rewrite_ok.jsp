<%@page contentType="text/html; charset=UTF-8"
	import="java.sql.*,java.io.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			String id = request.getParameter("id");
			String ID = request.getParameter("ID");
			String Status = request.getParameter("Status");
			String Use_Status = request.getParameter("Use_Status");
			String Type = request.getParameter("Type");
			String CreateTime = request.getParameter("CreateTime");
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
				String sql = "update pipeline set ID='" + ID
						+ "',Status='" + Status + "',Use_Status='" + Use_Status + "',Type='" + Type + "',CreateTime='" + CreateTime + "' where ID=" + id;
				statement.executeUpdate(sql);
				out.println(sql);
				statement.close();
				conn.close();
		%>
更改成功！请返回。
<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
		<%
			} catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
		%>
出现错误，请查看Console提示信息！请返回。
<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
		<%
			}
		%>
	</body>
</html>
