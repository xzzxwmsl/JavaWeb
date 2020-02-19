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
                            <a href="javascript:location.reload()">搜索书籍</a>
                        </li>
                    </ul>
                </div>
                <div><br><br><br>


                    <form class="form-horizontal" action="FindBook?action=1" method="post">
                        <div class="form-body">

                            <div class="form-group">
                                <label class="col-md-3 control-label">书名<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Name" name="Name" class="form-control" placeholder="请填写书名" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">编号<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Number" name="Number" class="form-control" placeholder="请填写编号" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">作者<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Author" name="Author" class="form-control" placeholder="请填写作者" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">出版社<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Publish" name="Publish" class="form-control" placeholder="请填写出版社" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">内容摘要<font color="red">*</font></label>
                                <div class="col-md-3">
                                    <input type="text" id="Note" name="Note" class="form-control" placeholder="请填写内容摘要" value=""/>
                                </div>
                            </div>



                            <div class="form-group">
                                <label class="control-label col-md-3">出版日期</label>
                                <div class="col-md-3">
                                    从：
                                    <div class="input-group date form_datetime">
                                        <input type="Date" name="PublishDate1" id="PublishDate1" size="16"  class="form-control" placeholder="请输入时间">
                                        到：
                                        <input type="Date" name="PublishDate2" id="PublishDate2" size="16"  class="form-control" placeholder="请输入时间">
                                    </div>
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