<%@ page contentType="text/html; charset=UTF-8"
		 import="java.sql.*,java.io.*"%>
<%@include file="GetUser.jsp"%>
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content">

		<!-- BEGIN PAGE HEADER-->
		<div class="page-bar">
			<ul id="page-breadcrumb" class="page-breadcrumb">
				<%--<li>--%>
					<%--<i class="fa fa-home"></i>--%>
					<%--<a href="index.html">Home</a>--%>
					<%--<i class="fa fa-angle-right"></i>--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<a href="#">Dashboard</a>--%>
				<%--</li>--%>
			</ul>
			<div id="page-toolbar" class="page-toolbar">
				<%--<div id="dashboard-report-range" class="pull-right tooltips btn btn-sm btn-default" data-container="body" data-placement="bottom" data-original-title="Change dashboard date range">--%>
					<%--<i class="icon-calendar"></i>&nbsp; <span class="thin uppercase visible-lg-inline-block"></span>&nbsp; <i class="fa fa-angle-down"></i>--%>
				<%--</div>--%>
			</div>
		</div>
		<h3 id="page-title" class="page-title">
			<%--Dashboard <small>reports & statistics</small>--%>
		</h3>
		<h1>Hello!  欢迎<%=LoginUser.getName()%>来到图书管理系统</h1>
			<%
				int BookNumber=0;
				int BorrowedBook=0;
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
							.executeQuery("select * from booklist");
				while(rs.next()){
				    BookNumber++;
				    if (rs.getBoolean("IfBorrowed")) BorrowedBook++;
				}
				out.println("<h1>总共书籍" + BookNumber+"</h1>");
				out.println("<h1>已经被借书籍" + BorrowedBook+"</h1>");
				out.println("<h1>在馆书籍" + (BookNumber-BorrowedBook)+"</h1>");

				rs=statement.executeQuery("select * from user ");
				int UserNumber=0;
				int BorrowedUser=0;

				while (rs.next()){
				    UserNumber++;
				    if(rs.getInt("LoanBook")!=0) BorrowedUser++;
				}
				out.println("<h1>读者人数" + UserNumber+"</h1>");
				out.println("<h1>欠书人数" + BorrowedUser+"</h1>");
				statement.close();
				conn.close();
				}catch (SQLException sqlexception) {
					sqlexception.printStackTrace();
				}
			%>
	</div>
</div>
<!-- END CONTENT -->