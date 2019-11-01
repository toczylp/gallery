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
        <div class="card-body">
            <div class="form-group">

                <form method="post" enctype="multipart/form-data">
                    <p>Select image:</p><br>
                    <input type="file" name="file" path="pic"/><br>
                    <input type="submit" class="btn btn-primary btn-lg" value="Upload"/>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger" role="alert">
                            <h4>Dozwolone formaty plików :</h4>
                                <c:forEach items="${error}" var="el">
                                        <p>${el.toString()}</p>
                                </c:forEach>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
