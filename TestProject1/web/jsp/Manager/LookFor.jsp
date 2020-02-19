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
        ////////////////
        <div class="page-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet box blue ">
                        <div class="page-bar" >
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
                        </div>
                        <div>
                            <button onclick="lookjq()" class="btn green">精确查找</button>
                            <button onclick="lookmh()" class="btn green">模糊查找</button>
                        </div>
                        <div class="portlet-body form">

                            <form name="registerForm" action="jsp/AddOk.jsp" class="form-horizontal">

                                <div class="form-group">
                                    <label for="ID"class="control-label col-md-3">管道ID:</label>
                                    <div class="col-md-3">
                                        <input type="text" name="ID" value="-1" id="ForId" class="form-control" placeholder="请填写ID名称">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="Status" class="control-label col-md-3">管道状态:</label>
                                    <div class="col-mod-3">
                                        <select name="Status" class="table-group-action-input form-control input-medium">
                                            <option value="正在使用">在用</option>
                                            <option value="废弃">废弃</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="Status" class="control-label col-md-3">在用状态:</label>
                                    <div class="col-mod-3">
                                        <select name="Use_Status" class="table-group-action-input form-control input-medium">
                                            <option value="通畅">通畅</option>
                                            <option value="堵塞">堵塞</option>
                                            <option value="维修中">维修中</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="Status" class="control-label col-md-3">管道类型:</label>
                                    <div class="col-mod-3">
                                        <select name="Type" class="table-group-action-input form-control input-medium">
                                            <option value="电线">电线</option>
                                            <option value="水管">水管</option>
                                            <option value="光纤">光纤</option>
                                            <option value="天然气管道">天然气管道</option>
                                        </select>
                                    </div>
                                </div>



                                <div class="form-group">
                                    <div class="col-md-6">
                                        <span>从</span>
                                        <div class="input-group date form_datetime">
                                            <input type="text" id="time_from" name="time_from" size="16" class="form-control" readonly="true">
                                            <span class="input-group-btn">
															<button class="btn default date-set" type="button">
																<i class="fa fa-calendar"></i>
															</button> </span>
                                        </div>
                                        <span>到</span>
                                        <div class="input-group date form_datetime">
                                            <input type="text" name="CreateTimeJZ" size="16" class="form-control" readonly="true">
                                            <span class="input-group-btn">
															<button class="btn default date-set" type="button">
																<i class="fa fa-calendar"></i>
															</button> </span>
                                        </div>

                                    </div>
                                </div>


                                <div class="form-actions">
                                    <input type="submit" name="subbtn" value="提交" class="btn red">
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <div >
                <button class="btn green" onclick="add()">添加</button>
                <button class="btn yellow" onclick="modify()">修改</button>
                <button class="btn blue" onclick="query()">查询</button>
                <button class="btn red" onclick="deleteit()">删除</button>
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
        window.location="Search.jsp";
    }
    function deleteit(){
        window.location="Delete.jsp";
    }
    function lookjq(){
        $("#ForId").prop('type','text');
    }
    function lookmh(){
        $("#ForId").prop('type','hidden');
    }
</script>