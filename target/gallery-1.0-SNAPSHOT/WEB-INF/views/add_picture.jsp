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
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <h4>Add an image</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <form action="/picture/add" method="post" enctype="multipart/form-data">
                    <p>Select image:</p><br>
                    <div>
                    <input type="file" name="file" path="pic"/>
                    </div>
                    <div>
                        <input type="radio" id="one" name="publicFlag" value="1" checked>
                        <label for="one"><h4>Public</h4></label><br>
                        <input type="radio" id="two" name="publicFlag" value="0">
                        <label for="two"><h4>Make it Private</h4></label>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Upload"/>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger" role="alert">
                            <h4>Error message:</h4>
                                <c:forEach items="${error}" var="el">
                                        <p>${el.toString()}</p>
                                </c:forEach>
                        </div>
                    </c:if>
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
