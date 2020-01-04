<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="app.domain.models.view.TubeViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>MeTube</title>
    <style><%@include file="styles.css"%></style>
</head>
<body>
<h1>All Tubes</h1>
<h3>Check our tubes below.</h3>
<% List<TubeViewModel> allTubes = (List<TubeViewModel>) request.getAttribute("allTubes"); %>
<% if (allTubes.size() == 0) { %>
<p>No Tubes - <a href="<c:url value="/tubes/create"/>">Create Some!</a></p>
<% } else { %>
<ul>
    <% for (TubeViewModel tube : allTubes) {
        String tubeName = tube.getTitle(); %>
     <li><a href="${pageContext.request.contextPath}/tubes/details?name=<%= tubeName %>"></a><%= tubeName %></li>
   <% }%>
</ul>
<% } %>
<a href="index.jsp">Back to Home</a>
<footer>
    <p>&copy; CopyRight Java Web Team 2019.All rights reserved.</p>
</footer>
</body>
</html>
