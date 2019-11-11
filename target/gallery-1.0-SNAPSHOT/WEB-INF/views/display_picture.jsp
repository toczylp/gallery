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
    <c:if test="${pictures != null}">
        <div class="container">
            <c:forEach items="${pictures}" var="el" varStatus="status">
                <c:if test="${status.index % 3 == 0}">
                    <div class="row">
                </c:if>
                <div class="col-sm-4">
                    <h1>${el.getFileName()}</h1>
                    <img src="data:image/jpeg;base64,${el.getEncodedPic()}" alt="DUPA" width="100%" height="auto"/>
                </div>
                <c:if test="${status.index % 3 == 2 ||  status.last}">
                    </div>
                    <div class="row">
                        <c:if test="${status.index < 3}">
                        <c:forEach var="i" begin="0" end="${status.index}" varStatus="status2" step="1">
                            <div class="col-sm-4 panel panel-info">
                                <div class="panel-heading"><p>File name: ${pictures[i].getFileName()}</p></div>
                                    <div class="panel-body">
                                        <p>Uploaded by: ${pictures[i].getUser().getLogin()}</p>
                                        <p>Upload date: ${pictures[i].getCreated()}    <span class="glyphicon glyphicon-info-sign lg"><a href="/picture/${pictures[i].getId()}/details">More</a><</span></p>
                                    </div>
                            </div>
                        </c:forEach>
                    </div>
                        </c:if>
                <div class="row">
                    <c:if test="${status.index >= 3}">
                    <c:forEach var="i" begin="3" end="${status.index}" varStatus="status2" step="1">
                        <div class="col-sm-4 panel panel-info">
                            <div class="panel-heading"><p>File name: ${pictures[i].getFileName()}</p></div>
                            <div class="panel-body">
                                <p>Uploaded by: ${pictures[i].getUser().getLogin()}</p>
                                <p>Upload date: ${pictures[i].getCreated()}    <span class="glyphicon glyphicon-info-sign lg"><a href="/picture/${pictures[i].getId()}/details">More </a></span></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                </c:if>

                    </div>
                </c:if>
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
                        <li class="page-item <c:if test="${currentPage == loop.index}">active</c:if>"><a class="page-link" href="/picture/read/all/page/${loop.index}" act>${loop.index}</a></li>
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
