<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container"></div>
    <div class="container">
        <div class="row">
            <c:if test="${users.size() > 0}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id</th>
                            <th scope="col">Created</th>
                            <th scope="col">Updated</th>
                            <th scope="col">FirstName</th>
                            <th scope="col">LastName</th>
                            <th scope="col">Login</th>
                            <th scope="col">Mail</th>
                            <th scope="col">Images Qty</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="el" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index + 1}</th>
                            <td>${el.getId()}</td>
                            <td>${el.getCreated()}</td>
                            <td>${el.getUpdated()}</td>
                            <td>${el.getFirstName()}</td>
                            <td>${el.getLastName()}</td>
                            <td>${el.getLogin()}</td>
                            <td>${el.getMail()}</td>
                            <td>${el.getPicsCounter()}</td>
                            <th scope="col"><a href="/user/deleteByAdmin/${el.getId()}"><button type="button" class="btn btn-danger">DELETE</button></a></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
