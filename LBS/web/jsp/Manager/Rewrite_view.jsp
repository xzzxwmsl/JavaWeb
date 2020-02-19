<%@page contentType="text/html; charset=UTF-8"
	import="java.sql.*,java.io.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			String id = request.getParameter("id");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
			try {
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/test?user=ylx&password=ylx&useUnicode=true&characterEncoding=UTF-8");
				Statement statement = conn.createStatement();
				out.println("Connect Database Ok！！！");
				ResultSet rs = statement
						.executeQuery("select * from pipeline where ID=" + id);
				while (rs.next()) {
		%>
		<form name="RewriteForm" action="Rewrite_ok.jsp" >
			<input type="hidden" name="id" value="<%=id%>">

			<label for="Status">管道状态:</label>
                       <select name="Status">
                       <option value="正在使用">在用</option>
                       <option value="废弃">废弃</option>
                       </select>
                       <br><br>

			<label for="Use_Status">在用状态:</label>
                       <select name="Use_Status">
                       <option value="通畅">通畅</option>
                       <option value="堵塞">堵塞</option>
                       <option value="维修中">维修中</option>
                       </select>
                       <br><br>

			<label for="ID">管道类型:</label>
                       <select name="Type">
                       <option value="电线">电线</option>
                       <option value="水管">水管</option>
                       <option value="光纤">光纤</option>
                       <option value="天然气管道">天然气管道</option>
                       </select>
                       <br><br>


                       <div>
			<label for="CreateTime">创建时间:</label>
　　　　　　　　　　　　<input type="datetime-local" name="CreateTime" value="2015/03/27 10:41">
                        </div><br><br>


			<label for="ID">管道ID:</label>
			<input type="text" name="ID" value="">
			<br><br>
			<input type="submit" name="subbtn" value="提交">

                       
		</form><br><br>
		<%
			}
				out.println("<br>");
				statement.close();
				conn.close();
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