<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8"/>
    <title>Metronic | Dashboard</title>
    <base href="http://localhost:8080/">
    <link href="style/explain.css" rel="stylesheet" type="text/css"/>
    <%@ include file="../../frame/head.jsp"%>
    <%@include file="../../frame/check_login.jsp"%>
    <%@include file="../../frame/interface.jsp"%>
</head>

<body onload="Home()" class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid page-header-fixed-mobile page-footer-fixed-mobile">
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
                            <a href="javascript:location.reload()">添加读者</a>
                        </li>
                    </ul>
                </div>
                <div><br><br><br>

                    <%if(request.getAttribute("Reason")!=null) out.print("<h1>错误原因 : "+request.getAttribute("Reason")+"</h1>");%>
                   <label onclick="Home2()"><h1 id="txt">操作失败，4秒后将自动返回主页</h1></label>
                    //////////////////////
                </div>

            </div>
        </div>


    </div>

    <%@ include file="../../frame/footer.jsp"%>
    <%@ include file="../../frame/script.jsp"%>

</body>
</html>
<script>
    function Home(){
        initPage();
        setTimeout("document.getElementById('txt').innerHTML='<h1>操作失败，3秒后将自动返回主页</h1>'",1000)
        setTimeout("document.getElementById('txt').innerHTML='<h1>操作失败，2秒后将自动返回主页</h1>'",2000)
        setTimeout("document.getElementById('txt').innerHTML='<h1>操作失败，1秒后将自动返回主页</h1>'",3000)
        setTimeout("window.location=\"index.jsp\"",4000)
    }
    function Home2() {
        window.location="index.jsp";
    }
</script>