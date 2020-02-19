<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
	<meta charset="utf-8"/>
	<title>Metronic | Dashboard</title>
	<base href="http://localhost:8080/">
	<link href="style/explain.css" rel="stylesheet" type="text/css"/>
	<%@ include file="../../frame/head.jsp"%>
	<script src="script/interface.js" type="text/javascript"></script>
</head>

<body onload="initPage()" class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid page-header-fixed-mobile page-footer-fixed-mobile">
<%@ include file="../../frame/header.jsp"%>
<div class="clearfix">
</div>

<div class="page-container">
	<%@ include file="../../frame/sidebar.jsp"%>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="page-bar">
				<div>
					<ul id="page-breadcrumb" class="page-breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="../../index.jsp">首页</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">说明</a>
						</li>
					</ul>
				</div>
				<div><br><br><br>
					///////////////////////
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
									.getConnection("jdbc:mysql://138.197.223.75:3306/underground?user=user&password=password&useUnicode=true&characterEncoding=UTF-8");
							Statement statement = conn.createStatement();
							String sql = "insert into pipeline(ID,Status,Use_Status,Type,CreateTime) values('"
									+ ID + "','" + Status + "','" + Use_Status + "','" + Type + "','" + CreateTime + "')";
							statement.executeUpdate(sql);
							statement.close();
							conn.close();
					%>
					添加成功！请返回。
					<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
					<%
					} catch (SQLException sqlexception) {
						sqlexception.printStackTrace();
					%>
					添加失败！！！请返回。
					<input type="button" name="listBtn" value="返回列表" onclick="window.location='Search.jsp'">
					<%
						}
					%>
					//////////////////////
				</div>

			</div>
		</div>


	</div>

	<%@ include file="../../frame/footer.jsp"%>
	<%@ include file="../../frame/script.jsp"%>

</body>
</html>
