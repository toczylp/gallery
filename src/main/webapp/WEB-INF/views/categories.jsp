<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>Manage Categories</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container"></div>
<div class="container">
    <div class="row">
        <div class="form-group col-md-8">
        <c:if test="${categoryList.size() > 0}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Images Qty</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="el" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index + 1}</th>
                        <td>${el.getId()}</td>
                        <td>${el.getName()}</td>
                        <td>${el.getPicsQty()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        </div>
        <div class="form-group col-md-4">

            <p>Create New Category Form</p>
            <form:form action="/category/add" method="get" modelAttribute="category">
                Category Name:<br>
                <form:input path="name"/>
                <form:errors path="name" element="div" class="alert alert-danger" role="alert"/>
                <input type="submit" class="btn btn-primary" value="SIGNUP"/>
            </form:form>

        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
