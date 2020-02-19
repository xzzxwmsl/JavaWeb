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
                    <form name="registerForm" action="jsp/AddOk.jsp" class="form-horizontal">


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
                    <input type="button" value="添加" onclick="add()"/>
                    <input type="button" value="修改" onclick="modify()"/>
                    <input type="button" value="查询" onclick="query()"/>
                    <input type="button" value="删除" onclick="deleteit()"/>
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
function add(){
	window.location="jsp/Add.jsp";
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