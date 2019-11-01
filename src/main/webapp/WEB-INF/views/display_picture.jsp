<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <h4>Add an image</h4>

    <div class="card">
        <img src="data:image/jpeg;base64,${pic}" alt="DUPA"/>
    </div>
    <c:if test="${pictures != null}">
    <div class="container">
        <c:forEach items="${pictures}" var="el" varStatus="status">
            <h1>${status.index}</h1>
            <img src="data:image/jpeg;base64,${el}" alt="DUPA" width="480" height="640"/>
        </c:forEach>

        <nav aria-label="...">
            <ul class="pagination">
                <c:if test="${currentPage == 1}">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                </c:if>
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="/picture/read/all/page/${currentPage - 1}">Previous</a>
                    </li>
                </c:if>
                    <c:forEach begin="1" end="${pages}" varStatus="loop">
                    <li class="page-item <c:if test="${currentPage == loop.index}">active"</c:if>"><a class="page-link" href="/picture/read/all/page/${loop.index}" act>${loop.index}</a></li>
                </c:forEach>
                <c:if test="${currentPage == pages}">
                    <li class="page-item disabled">
                        <span class="page-link">Next</span>
                    </li>
                </c:if>
                <c:if test="${currentPage < pages}">
                    <li class="page-item">
                        <a class="page-link" href="/picture/read/all/page/${currentPage + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
    </c:if>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
