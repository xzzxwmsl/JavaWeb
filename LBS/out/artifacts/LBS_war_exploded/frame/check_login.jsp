<%
    //    如果当前不是登录状态，重定向至登录界面
    Boolean isLogin = (Boolean)session.getAttribute("isLogin");
    if (isLogin == null || !isLogin) {
        response.sendRedirect("/");
    }
%>