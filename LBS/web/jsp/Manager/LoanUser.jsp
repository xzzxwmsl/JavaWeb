<%@page contentType="text/html; charset=UTF-8"
        import="java.sql.*,java.io.*"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <meta charset="utf-8"/>
    <title>Metronic | Dashboard</title>
    <base href="http://localhost:8080/">
    <link href="style/explain.css" rel="stylesheet" type="text/css"/>
    <%@ include file="../../frame/head.jsp"%>
    <%@ include file="../../frame/Table.jsp"%>
    <%@page import="java.util.List"%>
    <%@page import="Bean.*"%>
    <%@include file="../../frame/check_login.jsp"%>
    <%@include file="../../frame/interface.jsp"%>

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
                            <a href="javascript:location.reload()">说明</a>
                        </li>
                    </ul>
                </div>
                <div><br><br><br>
                    <%
                        String UserType=(String)session.getAttribute("UserType");
                    %>

                    <table width="90%" class="table">
                        <caption><h2>书籍信息</h2></caption>
                        <thead>
                        <tr >
                            <th>欠书者ID</th>
                            <th>欠书者</th>
                            <th>书籍ID</th>
                            <th>书名</th>
                            <th>借书日期</th>
                            <th>应还日期</th>
                            <th>是否超期</th>
                            <%if(UserType.equals("ADMIN")) out.print("<th>操作</th>");%>
                        </tr>
                        </thead>

                        <%
                            // 获取图书信息集合
                            List<LoanBook> list = (List<LoanBook>) request.getAttribute("list");
                            // 判断集合是否有效
                            if (list == null || list.size() < 1) {
                                out.print("没有数据！");%>
                        <script type="text/javascript" language="javascript">
                            alert("未欠书，返回");
                            window.history.go(-1);
                        </script>
                        <%
                        } else {
                            // 遍历图书集合中的数据
                            for (LoanBook book : list) {
                                System.out.println(book.getFlag()+"XXXXXXX");
                        %>
                        <tr align="center" bgcolor="white">
                            <td><%=book.getUserId()%></td>
                            <td><%=book.getUserName()%></td>
                            <td><%=book.getId()%></td>
                            <td><%=book.getName()%></td>
                            <td><%=book.getLoanDate()%></td>
                            <td><%=book.getReturnDate()%></td>
                            <%if(book.getFlag()==1){%>
                            <td><font color="red">超期</font></td>
                            <%}else {%>
                            <td><font color="green">未超期</font></td>
                            <%}if(UserType.equals("ADMIN")){%>
                            <td><button type="button" class="btn green" onclick="window.location.href='FindUser?action=2&UserId=<%=book.getUserId()%>'">读者信息</button></td>
                            <%}%>
                        </tr>
                        <%
                                    }
                                }
                        %>

                    </table>


                    <%
                        //Body
                    %>


                </div>

            </div>
        </div>


    </div>

    <%@ include file="../../frame/footer.jsp"%>
    <%@ include file="../../frame/script.jsp"%>

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
    function LookPicture(e) {
        if(e.src == "http://localhost:8080/null"){
            alert("无图片！");
        }else{
            window.document.location.href=e.src;
        }
    }
</script>