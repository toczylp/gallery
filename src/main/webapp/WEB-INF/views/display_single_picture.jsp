<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <img src="data:image/jpeg;base64,${singlePicture.getEncodedPic()}" alt="DUPA" width="100%" height="auto"/>
                </div>
                <div class="col-sm-4">
                    <div class="row">
                    <h4>Comment (max 60 characters):</h4>
                    <form:form method="post" action="../${singlePicture.getId()}/add_comment" modelAttribute="comment">
                        <form:textarea path="content" cols="auto" rows="3"/><br>
                        <c:if test="${error != null}">
                            <div class="alert alert-danger" role="alert">${error}</div>
                        </c:if>
                        <input type="submit" class="btn btn-primary" value="COMMENT"/>
                    </form:form>
                    </div>
                    <div class="container" style="border-color: chartreuse; border-width: 1px">
                            <p>Basic Info: </p>
                            <c:forEach items="${details}" var="el" varStatus="status">
                                <p>${el.key}<span> : </span>${el.value}</p>
                            </c:forEach>
                    </div>
                    <div class="container">
                        <p><span style="font-weight: bold">Views</span> : ${singlePicture.getDirectDisplayQty()}</p>
                    </div>
                    <div class="container">
                        <form method="post" action="../${singlePicture.getId()}/rate">
                            <div>
                            <input type="radio" name="rating" value="5">5<input type="radio" name="rating" value="4">4<input type="radio" name="rating" value="3">3<input type="radio" name="rating" value="2">2<input type="radio" name="rating" value="1">1<br>
                            <c:if test="${alreadyRankedFlag}">
                                <div class="alert alert-danger" role="alert">You can just rank once every picture</div>
                            </c:if>
                            </div>
                            <input type="submit" class="btn btn-primary" value="RATE"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:if test="${commentsList.size() > 0}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Created</th>
                            <th scope="col">User</th>
                            <th scope="col">Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${commentsList}" var="el" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index + 1}</th>
                            <td>${el.dateFormatToString()}</td>
                            <td>${el.getUser().getLogin()}</td>
                            <td>${el.getContent()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
