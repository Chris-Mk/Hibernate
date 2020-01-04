<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube</title>
    <style>
        <%@include file="styles.css" %>
    </style>
</head>
<body>
<h1>Welcome to MeTube!</h1>
<hr/>
<h3>Cool app in beta version</h3>
<br />
<a class="btn" href="${pageContext.request.contextPath}/tubes/create">Create Tube</a>
<a class="btn" href="${pageContext.request.contextPath}/tubes/all">All Tubes</a>
<footer>
    <br />
    <p>&copy; CopyRight Java Web Team 2019.All rights reserved.</p>
</footer>
</body>
</html>
