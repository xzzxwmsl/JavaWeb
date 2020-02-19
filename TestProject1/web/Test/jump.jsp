
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>为 HTML 添加新元素</title>
    <script>
        document.createElement("myHero")
    </script>
    <style>
        myHero {
            display: block;
            background-color: #ddd;
            padding: 50px;
            font-size: 30px;
        }
    </style>
</head>

<body>

<h1>我的第一个标题</h1>

<p>我的第一个段落。</p>

<myHero>我的第一个新元素</myHero>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="190">
    <polygon points="100,10 40,180 190,60 10,60 160,180"
             style="fill:lime;stroke:purple;stroke-width:5;fill-rule:evenodd;">
</svg>

</body>
</html>