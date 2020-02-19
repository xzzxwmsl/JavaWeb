<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8"/>
    <title>三维智慧城市地下管网</title>
    <%--<base href="http://localhost:8080/underground/">--%>
    <base href="/XM11/">
    <%@ include file="../../frame/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
    <script src="script/interface.js" type="text/javascript"></script>
</head>

<%@include file="../../frame/check_login.jsp"%>

<body class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid page-header-fixed-mobile page-footer-fixed-mobile">
<%@ include file="../../frame/header.jsp"%>
<div class="clearfix">
</div>

<div class="page-container">
    <%@ include file="../../frame/sidebar.jsp"%>
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="portlet box blue ">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-gift"></i>添加设备
                                </div>
                                <div class="tools">
                                    <a href="" class="reload"> </a>
                                    <a href="" class="remove"> </a>
                                </div>
                            </div>
                            <div class="portlet-body form">
                                <form class="form-horizontal" action="jsp/device/AddOk.jsp">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="Device_ID">设备编号<font color="red">*</font></label>
                                            <div class="col-md-3">
                                                <input type="text" id="Device_ID" name="Device_ID" class="form-control" placeholder="请填写设备编号" value=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="Device_Type">
                                                设备类别<font color="red">*</font>
                                            </label>
                                            <div class="col-md-3">
                                                <select class="table-group-action-input form-control input-medium" id="Device_Type" name="Device_Type">
                                                    <option value="电阻式传感设备">电阻式传感设备</option>
                                                    <option value="变频功率传感设备">变频功率传感设备</option>
                                                    <option value="称重传感设备">称重传感设备</option>
                                                    <option value="电阻应变式传感设备">电阻应变式传感设备</option>
                                                    <option value="压阻式传感设备">压阻式传感设备</option>
                                                    <option value="激光传感设备">激光传感设备</option>
                                                    <option value="温度传感设备">温度传感设备</option>
                                                    <option value="光敏传感设备">光敏传感设备</option>
                                                    <option value="液位传感设备">液位传感设备</option>
                                                    <option value="酸碱盐传感设备">酸碱盐传感设备</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="Pipeline_ID">所属管线编号<font color="red">*</font></label>
                                            <div class="col-md-3">
                                                <input type="text" id="Pipeline_ID" name="Pipeline_ID" class="form-control" placeholder="请输入所属管线编号" value=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3">设备添加时间</label>
                                            <div class="col-md-3">
                                                <div class="input-group date form_datetime">
                                                    <input type="text" name="Create_Time" id="Create_Time" size="16"  class="form-control" placeholder="请输入设备添加时间">
                                                    <span class="input-group-btn">
                                                <button class="btn default date-set" type="button">
                                                    <i class="fa fa-calendar"></i>
                                                </button>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="submit" id="subbtn" name="subbtn" class="btn blue" value="提交" align="right">
                                    </div>
                                </form>
                                <div class="form-actions right1">
                                    <input type="button" id="search_button" class="btn green" value="添加设备" onclick="add()"/>
                                    <input type="button" id="submit_button" class="btn green" value="修改设备" onclick="modify()"/>
                                    <input type="button" id="return_button" class="btn green" value="设备列表" onclick="query()"/>
                                    <input type="button" id="help_button" class="btn green" value="删除设备" onclick="deleteit()"/>
                                    <input type="button" id="delete_button" class="btn green" value="查询设备" onclick="lookfor()"/>
                                </div>
                            </div>
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

            <script type="text/javascript" src="script/device/CRUD.js"></script>
            <script type="text/javascript" src="assets/global/scripts/jquery.table2excel.js"></script>
            <script type="text/javascript" src="script/print-export.js"></script>

            <script>
                jQuery(document).ready(function() {
                    //从http session里面获取菜单json对象并初始化sidebar
                    rootMenu = <%=session.getAttribute("rootMenu")%>;
                    initSideBar();

                    Metronic.init(); // init metronic core componets
                    Layout.init(); // init layout
                    QuickSidebar.init(); // init quick sidebar
                    Index.init();
                    ComponentsDropdowns.init();
                    ComponentsPickers.init();
                });
            </script>

</body>
</html>