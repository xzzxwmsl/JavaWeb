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
                            <a href="javascript:location.reload()">书籍信息</a>
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
                            <th>ID</th>
                            <th>书名</th>
                            <th>作者</th>
                            <th>出版社</th>
                            <th>书号</th>
                            <th>类型</th>
                            <th>出版日期</th>
                            <th>封面</th>
                            <th>说明</th>
                            <th>是否在馆</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <%
                            // 获取图书信息集合
                            List<Book> list = (List<Book>) request.getAttribute("list");
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
                            for (Book book : list) {
                                System.out.println(book.getPicture());
                        %>
                        <tr align="center" bgcolor="white">
                            <td><%=book.getId()%></td>
                            <td><%=book.getName()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getPublish()%></td>
                            <td><%=book.getNumber()%></td>
                            <td><%=book.getType()%></td>
                            <td><%=book.getPublishDate()%></td>
                            <td>
                                <img src="${pageContext.request.contextPath}/<%=book.getPicture()%>" height="40px" width="30px" onclick="LookPicture(this)">
                            </td>
                            <td><%=book.getNote()%></td>
                            <td>
                                <%if(book.getIfBorrowed()==1){%>
                                <font color="red">不在馆</font>
                                <%}else {%>
                                <font color="green">在馆</font>
                                <%}%>
                            </td>
                            <td>
                                <%if(UserType.equals("USER")&&book.getIfBorrowed()!=1){%>
                                <button type="button" class="btn green" onclick="window.location.href='LoanBook?action=1&UserId=<%=LoginUser.getUserId()%>&Id=<%=book.getId()%>&BookName=<%=book.getName()%>&UserName=<%=LoginUser.getName()%>&LoanBook=<%=LoginUser.getLoanBook()%>'">借阅</button>
                                <%}else if(UserType.equals("ADMIN")) {%>
                                <button type="button" class="btn blue" onclick="window.location.href='jsp/Book/Update.jsp?Id=<%=book.getId()%>'">修改</button>
                                <button type="button" class="btn red" onclick="window.location.href='DeleteBook?Id=<%=book.getId()%>'">删除</button>
                            </td>
                        </tr>
                        <%
                                    }
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

