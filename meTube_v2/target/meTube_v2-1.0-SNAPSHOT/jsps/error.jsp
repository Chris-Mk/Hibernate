<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube v2</title>
    <%@include file="templates/resources.jsp"%>
</head>
<body>
<div class="container-fluid">
    <header>
        <%@include file="templates/header.jsp"%>
    </header>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h3">It seems you got some errors!</p>
            <% List<String> violationMessages = (List<String>) request.getAttribute("violationMessages");
                for (String violationMessage : violationMessages) { %>
                    <ul>
                        <li style="color: red"><%=violationMessage%></li>
                    </ul>
            <% } %>
        </div>
        <div class="control-group">
            <div class="controls text-center">
                <a class="btn btn-info" href="/register">Try Again!</a>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <%@include file="templates/footer.jsp"%>
    </footer>
</div>
</body>
</html>
