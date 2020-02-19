<%@page contentType="text/html; charset=UTF-8"
		import="java.sql.*,java.io.*"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
	<meta charset="utf-8"/>
	<title>Metronic | Dashboard</title>
	<base href="http://localhost:8080/">
	<link href="style/explain.css" rel="stylesheet" type="text/css"/>
	<%@ include file="../../frame/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
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
						//链接数据库，加载jdbc的驱动com.mysql.jdbc.Driver
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException classnotfoundexception) {
							//如果有异常就抛出
							classnotfoundexception.printStackTrace();
						}
						try {
							//链接数据库，IP地址是localhost，端口是3306，账号和密码是ylx，这些都可以更改
							Connection conn = DriverManager
									.getConnection("jdbc:mysql://138.197.223.75:3306/underground?user=user&password=password&useUnicode=true&characterEncoding=UTF-8");
							Statement statement = conn.createStatement();
							//执行查询语句，返回结果集
							ResultSet rs = statement
									.executeQuery("select * from pipeline");
							//如果查询有结果，则循环显示查询出来的记录
							out.println("<table width=\"100%\" id=\"Table\">");
							out.println("<tr>");
							out.println("<th>ID</th>");
							out.println("<th>管道状态</th>");
							out.println("<th>在用状态</th>");
							out.println("<th>类型</th>");
							out.println("<th>创建时间</th>");
							out.println("</tr>");
							while (rs.next()) {
								out.println("<tr>");
								out.println("<td>");
								out.println(rs.getString("ID"));
								out.println("</td><td>");
								out.println(rs.getString("Status"));
								out.println("</td><td>");
								out.println(rs.getString("Use_Status"));
								out.println("</td><td>");
								out.println(rs.getString("Type"));
								out.println("</td><td>");
								out.println(rs.getString("CreateTime"));
								out.println("</td></tr>");
							}
							out.println("</table>");
							//加个断行
							out.println("<br>");
							statement.close();
							conn.close();
						} catch (SQLException sqlexception) {
							sqlexception.printStackTrace();
						}
					%>
					<button class="btn white" onclick="lookfor()">查询</button>
					<button class="btn green" onclick="add()">添加</button>
					<button class="btn yellow" onclick="modify()">修改</button>
					<button class="btn blue" onclick="query()">总览</button>
					<button class="btn red" onclick="deleteit()">删除</button>
					<input type="button" id="print" class="btn green" value="打印" onclick="doPrint('TableDiv')"/>
					<input type="button" id="export" class="btn green" value="导出" onclick="Export('Table')"/>
					//////////////////////
				</div>

			</div>
		</div>


	</div>

	<%@ include file="../../frame/footer.jsp"%>
	<%@ include file="../../frame/script.jsp"%>
		<script type="text/javascript" src="assets/admin/pages/scripts/components-dropdowns.js"></script>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="assets/module/scripts/public/datetime_tools.js"></script>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/select2/select2.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>

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
	window.location="jsp/Search.jsp";
}
function deleteit(){
	window.location="Delete.jsp";
}
</script>