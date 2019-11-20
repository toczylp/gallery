<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Password" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container"><br>
    <h4>SIGNUP FORM</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <form:form method="post" modelAttribute="user">
                    Email:<br>
                    <form:input path="mail"/><br>
                    <form:errors path="mail" element="div" class="alert alert-danger" role="alert"/>
                    First Name:<br>
                    <form:input path="firstName"/><br>
                    <from:errors path="firstName" element="div" class="alert alert-danger" role="alert"/>
                    Last Name:<br>
                    <form:input path="lastName"/><br>
                    <form:errors path="lastName" element="div" class="alert alert-danger" role="alert"/>
                    Login:<br>
                    <form:input path="login"/><br>
                    <form:errors path="login" element="div" class="alert alert-danger" role="alert"/>
                    Password:<br>
                    <form:password path="password"/><br>
                    <form:errors path="password" element="div" class="alert alert-danger" role="alert"/>
                    Date of Birth:<br>
                    <form:input path="dateOfBirth" type="date"/>
                    <input type="submit" class="btn btn-primary" value="SIGNUP"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
