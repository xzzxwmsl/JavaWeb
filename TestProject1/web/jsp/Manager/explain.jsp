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
                <div id="page-toolbar" class="page-toolbar">
                </div>
            </div>
            <h3 id="page-title" class="page-title">
                说明 <small> 大体结构 & 使用方法</small>
            </h3>
            <p>界面通过数据库自动生成，目前完成了菜单部分。</p>
            <p>数据库ip:138.197.223.75;用户名：user;密码password</p>
            <p>连接数据库underground,修改表project_tree来修改界面。</p>
            表结构：
            <img src="resource/img/table_structure.png"/>
            <div class="clearfix"></div>
            <p>表内容：
                <a href="resource/img/table_content.png" target="view_window">Image</a>
            </p>
            <div class="clearfix"></div>
            <p>
                CATEGORY_ID和PARENT_CATEGORY_ID命名没有要求，只要能够反映菜单间的父子关系并且不要有循环
                建议一级菜单A-Z;二级菜单AA-ZZ。。。（或着其他按字典序的方法）这样在navicat中按CATEGORY_ID排序很容易看清结构。
            </p>
            <p>菜单超过3级效果不好，以后可以把级数较高的菜单做成popup的形式</p>
            <p>将界面视为树状结构，TYPE表示每一个节点的类型，目前只有menu，以后可以加toolbar之类的。</p>
            <p>HTML_ID是给该菜单一个id，如果要在javascript更改菜单，就需要赋值。根菜单id为page-sidebar-menu。</p>
            <p>CLASS无用，留待以后扩展需要。</p>
            <p>icon通过css定义</p>
            <p>*_ACCESS控制访问权限，目前只有两类用户admin和user。置为0则该菜单以及所有的子菜单都不可见</p>
        </div>
    </div>


</div>

<%@ include file="../../frame/footer.jsp"%>
<%@ include file="../../frame/script.jsp"%>

</body>
</html>