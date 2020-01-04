<%@ page import="java.util.Collection" %>
<%@ page import="app.domain.models.view.TubeViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube v2</title>
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
            <h4 class="h4 text-info">Welcome, <%=request.getAttribute("username")%>
            </h4>
        </div>
        <hr class="my-4">
        <div class="container">
            <% Collection<TubeViewModel> tubeViewModels = ((Collection<TubeViewModel>) request.getAttribute("alltubes"));
                for (TubeViewModel tubeViewModel : tubeViewModels) { %>
                    <p><%=tubeViewModel.getTitle()%></p>
                    <p><%=tubeViewModel.getAuthor()%></p>
                    <p><%=tubeViewModel.getDescription()%></p>
                    <p><%=tubeViewModel.getYoutubeId()%></p>
                    <p><%=tubeViewModel.getViews()%></p>
            <% } %>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <%@include file="templates/footer.jsp" %>
    </footer>
</div>
</body>
</html>
