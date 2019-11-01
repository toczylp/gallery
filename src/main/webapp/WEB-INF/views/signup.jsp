<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Password" uri="http://www.springframework.org/tags/form" %>
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
    <h4>SIGNUP</h4>
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
