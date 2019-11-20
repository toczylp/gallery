<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Password" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>Online Gallery</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container"><br>
    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <form:form method="post" action="" modelAttribute="user">
                    <form:input path="login"/><br>
                    <form:errors path="login" element="div" class="alert alert-danger" role="alert"/>
                    Password:<br>
                    <form:password path="password"/><br>
                    <form:errors path="password" element="div" class="alert alert-danger" role="alert"/>
                    <input type="submit" class="btn btn-primary" value="LOGIN"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
