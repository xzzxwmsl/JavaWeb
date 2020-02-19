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
                            <a href="javascript:location.reload()">搜索读者</a>
                        </li>
                    </ul>
                </div>
                <div><br><br><br>


                    <form class="form-horizontal" action="FindUser?action=1" method="post">
                        <div class="form-body">

                            <div class="form-group">
                                <label class="col-md-3 control-label">证号<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="UserId" name="UserId" class="form-control" placeholder="请填写证号" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Name" name="Name" class="form-control" placeholder="请填写姓名" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">性别<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Author" name="Author" class="form-control" placeholder="请填写作者" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">系<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Sdept" name="Sdept" class="form-control" placeholder="请填写年级" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">年级<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Grade" name="Grade" class="form-control" placeholder="请填写年级" value=""/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-3" >
                                    <input class="btn green" type="submit" name="subbtn" value="提交"  style="margin-right: 40px">
                                    <input class="btn red" type="reset" value="重置" style="margin-right: 10px">
                                </div>
                            </div>

                        </div>
                    </form>




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