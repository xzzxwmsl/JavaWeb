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
    <%@page import="Bean.Book"%>
    <%@include file="../../frame/check_login.jsp"%>
    <%@include file="../../frame/interface.jsp"%>
    <%@include file="../../frame/GetUser.jsp"%>

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
                            <a href="javascript:location.reload()">借阅申请</a>
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
                            <th>书籍ID</th>
                            <th>书名</th>
                            <th>借书者Id</th>
                            <th>借书者姓名</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <%
                            // 获取图书信息集合
                            List<Request> list = (List<Request>) request.getAttribute("list");
                            // 判断集合是否有效
                            if (list == null || list.size() < 1) {
                                out.print("没有数据！");%>
                        <script type="text/javascript" language="javascript">
                            alert("未查到数据，跳转至主界面");
                            window.document.location.href="index.jsp";
                        </script>
                        <%
                        } else {
                            // 遍历图书集合中的数据
                            for (Request book : list) {
                        %>
                        <tr align="center" bgcolor="white">
                            <td><%=book.getBookId()%></td>
                            <td><%=book.getBookName()%></td>
                            <td><%=book.getUserId()%></td>
                            <td><%=book.getUserName()%></td>
                            <td>
                                <button type="button" class="btn green" onclick="window.location.href='LoanBook?action=2&Action=1&Id=<%=book.getBookId()%>&UserId=<%=book.getUserId()%>'">同意</button>
                                <button type="button" class="btn red" onclick="window.location.href='LoanBook?action=2&Action=0&Id=<%=book.getBookId()%>&UserId=<%=book.getUserId()%>'">拒绝</button>
                            </td>
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

