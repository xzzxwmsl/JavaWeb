<%@page contentType="text/html; charset=UTF-8"
        import="java.sql.*,java.io.*"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta charset="utf-8"/>
    <title>Metronic | Dashboard</title>
    <base href="http://localhost:8080/">
    <%@ include file="../../frame/head.jsp"%>
    <script src="script/interface.js" type="text/javascript"></script>
    <style type="text/css">
        /* CSS Document */
        table th {
            font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #4f6b72;
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            border-top: 1px solid #C1DAD7;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-align: left;
            padding: 6px 6px 6px 12px;
            background: #CAE8EA no-repeat;
        }
        td {
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            background: #fff;
            font-size:11px;
            padding: 6px 6px 6px 12px;
            color: #4f6b72;
        }
        td.alt {
            background: #F5FAFA;
            color: #797268;
        }
        div.portlet-body form{
            padding: 15px;
        }
    </style>
</head>

<body onload="initPage()" class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid page-header-fixed-mobile page-footer-fixed-mobile">

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
                                <i class="fa fa-gift"></i>查询设备
                            </div>
                            <div class="tools">
                                <a href="" class="reload"> </a>
                                <a href="" class="remove"> </a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form class="form-horizontal">
                            <%
                                try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                } catch (ClassNotFoundException classnotfoundexception) {
                                    classnotfoundexception.printStackTrace();
                                }
                                try {
                                    Connection conn = DriverManager
                                            .getConnection("jdbc:mysql://localhost:3306/test?user=ylx&password=ylx&useUnicode=true&characterEncoding=UTF-8");
                                    Statement statement = conn.createStatement();
                                    //执行查询语句，返回结果集
                                    ResultSet rs = statement
                                            .executeQuery("select * from device");
                                    //如果查询有结果，则循环显示查询出来的记录
                                    out.println("<table width=\"100%\">");
                                    out.println("<tr>");
                                    out.println("<th>设备ID</th>");
                                    out.println("<th>设备类型</th>");
                                    out.println("<th>所属管线ID</th>");
                                    out.println("<th>设备添加时间</th>");
                                    out.println("</tr>");
                                    int count=1;
                                    while (rs.next()) {
                                        if((count%2)==1)
                                        {out.println("<tr>");
                                            out.println("<td>");
                                            out.println(rs.getString("Device_ID"));
                                            out.println("</td><td>");
                                            out.println(rs.getString("Device_Type"));
                                            out.println("</td><td>");
                                            out.println(rs.getString("Pipeline_ID"));
                                            out.println("</td><td>");
                                            out.println(rs.getString("Create_Time"));
                                            out.println("</td></tr>");
                                            count++;
                                        }
                                        else{
                                            out.println("<tr>");
                                            out.println("<td class=\"alt\">");
                                            out.println(rs.getString("Device_ID"));
                                            out.println("</td><td class=\"alt\">");
                                            out.println(rs.getString("Device_Type"));
                                            out.println("</td><td class=\"alt\">");
                                            out.println(rs.getString("Pipeline_ID"));
                                            out.println("</td><td class=\"alt\">");
                                            out.println(rs.getString("Create_Time"));
                                            out.println("</td></tr>");
                                            count++;
                                        }
                                    }
                                    out.println("</table>");
                                    //加个断行
                                    out.println("<br>");
                                    statement.close();
                                    conn.close();
                                } catch (SQLException sqlexception) {
                                    sqlexception.printStackTrace();
                                }
                            %>
                            </form>
                            <div class="form-actions right1">
                                <input type="button" id="search_button" class="btn green" value="添加设备" onclick="add()"/>
                                <input type="button" id="submit_button" class="btn green" value="修改设备" onclick="modify()"/>
                                <input type="button" id="return_button" class="btn green" value="查询设备" onclick="query()"/>
                                <input type="button" id="help_button" class="btn green" value="删除设备" onclick="deleteit()"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
            <%@ include file="../../frame/footer.jsp"%>
            <%@ include file="../../frame/script.jsp"%>
            <script type="text/javascript" src="../../assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
            <script type="text/javascript" src="../../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
            <script type="text/javascript" src="../../assets/module/scripts/project/todo/todo_add.js"></script>

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
</script>