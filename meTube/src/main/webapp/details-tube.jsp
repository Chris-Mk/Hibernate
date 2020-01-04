<%@ page import="app.services.TubeService" %>
<%@ page import="app.services.TubeServiceImpl" %>
<%@ page import="app.domain.models.view.TubeViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube</title>
    <style><%@include file="styles.css"%></style>
</head>
<body>
<% TubeViewModel tubeName = (TubeViewModel) request.getAttribute("viewModel"); %>
<h1>
    <%= tubeName.getTitle() %>
</h1>
<hr />
<h3>
    <%= tubeName.getDescription() %>
</h3>
<a href=<%= tubeName.getYoutubeLink() %>>Link to Video</a>
<%= tubeName.getUploader() %>
<hr />
<a href="index.jsp">Back to Home</a>
<footer>
    <p>&copy; CopyRight Java Web Team 2019.All rights reserved.</p>
</footer>
</body>
</html>
