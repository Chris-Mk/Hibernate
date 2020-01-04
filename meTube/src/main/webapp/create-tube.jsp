<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>MeTube</title>
    <style><%@include file="styles.css"%></style>
</head>
<body>
<h1>Create Tube!</h1>
<hr />
<form action="<c:url value="/tubes/create"/>" method="post">
    <label>Title
        <br/>
        <input type="text" name="title">
    </label>
    <br/>
    <br/>
    <label>Description
        <br/>
        <textarea name="description" cols="30" rows="5"></textarea>
    </label>
    <br />
    <br />
    <label>YouTube Link
        <br/>
        <input type="text" name="youtubeLink">
    </label>
    <br/>
    <br/>
    <label>Uploader
        <br/>
        <input type="text" name="uploader">
    </label>
    <br/>
    <button class="btn">Create Tube</button>
</form>
<a href="${pageContext.request.contextPath}/">Back to Home</a>
<footer>
    <p>&copy; CopyRight Java Web Team 2019.All rights reserved.</p>
</footer>
</body>
</html>
