<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 11/4/2019
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube</title>
    <%@include file="templates/resources.jsp" %>
</head>
<body>
<div class="container-fluid">
    <header>
        <%@include file="templates/header.jsp" %>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%=request.getParameter("username")%></h4>
            <h4 class="text-info text-center">(<%=request.getParameter("email")%>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover table-dark">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    ${myTubes}
                    </tbody>
                </table>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <%@include file="templates/footer.jsp" %>
    </footer>
</div>
</body>
</html>
