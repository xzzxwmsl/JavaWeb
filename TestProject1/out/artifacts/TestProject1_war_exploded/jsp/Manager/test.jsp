<%--
  Created by IntelliJ IDEA.
  User: xzz
  Date: 2018/5/17
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style media=print>
    .no_print{display:none;}
    .page_next{page-break-after: always;}
</style>
<head>
    <title>Title</title>
</head>
<body>
<button class="no_print">QQ</button>
<button class="no_print" onclick="window.print()">Print</button>
<table style="border:1px" id="tableID">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <tr>
        <td>1</td>
        <td>张三</td>
        <td>16</td>
        <td>男</td>
    </tr>
    <tr>
        <td>2</td>
        <td>李四</td>
        <td>18</td>
        <td>男</td>
    </tr>
    <tr>
        <td>3</td>
        <td>王五</td>
        <td>20</td>
        <td>女</td>
    </tr>
</table>
</body>
</html>
