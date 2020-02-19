<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.1.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<html lang="en" class="no-js">

<head>
<meta charset="utf-8"/>
<title>Metronic | Dashboard</title>
<%@ include file="frame/head.jsp"%>
<script src="script/interface.js" type="text/javascript"></script>
</head>
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body onload="initPage()" class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid page-header-fixed-mobile page-footer-fixed-mobile">
<%@ include file="frame/header.jsp"%>
<div class="clearfix">
</div>

<div class="page-container">
	<%@ include file="frame/sidebar.jsp"%>
	<%@ include file="frame/content.jsp"%>

</div>

<%@ include file="frame/footer.jsp"%>
<%@ include file="frame/script.jsp"%>
<script>
// jQuery(document).ready(function() {
//    Metronic.init(); // init metronic core componets
//    Layout.init(); // init layout
//    QuickSidebar.init(); // init quick sidebar
// Demo.init(); // init demo features
//    Index.init();
//    Index.initDashboardDaterange();
//    Index.initJQVMAP(); // init index page's custom scripts
//    Index.initCalendar(); // init index page's custom scripts
//    Index.initCharts(); // init index page's custom scripts
//    Index.initChat();
//    Index.initMiniCharts();
//    Tasks.initDashboardWidget();
// });
</script>

</body>
</html>