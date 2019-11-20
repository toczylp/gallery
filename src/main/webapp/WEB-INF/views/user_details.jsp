<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Password" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>User Details</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <h4>SIGNUP</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <form:form method="post" modelAttribute="user">
                    <form:input path="id" class="invisible"/><br>
                    Created:<br>
                    <form:input path="created" readonly="true"/><br>
                    <form:errors path="created" element="div" class="alert alert-danger" role="alert"/>
                    First Name:<br>
                    <form:input path="firstName"/><br>
                    <from:errors path="firstName" element="div" class="alert alert-danger" role="alert"/>
                    Last Name:<br>
                    <form:input path="lastName"/><br>
                    <form:errors path="lastName" element="div" class="alert alert-danger" role="alert"/>
                    Login:<br>
                    <form:input path="login" disabled="true"/><br>
                    <form:errors path="login" element="div" class="alert alert-danger" role="alert"/>
                    Date of Birth:<br>
                    <form:input path="dateOfBirth" type="date" readonly="true"/><br>
                    <form:errors path="dateOfBirth" element="div" class="alert alert-danger" role="alert"/>
                    Mail:<br>
                    <form:input path="mail" readonly="true"/><br>
                    <form:errors path="mail" element="div" class="alert alert-danger" role="alert"/>
                    Pictures q-ty:<br>
                    <form:input path="picsCounter" readonly="true"/><br>
                    <form:errors path="picsCounter" element="div" class="alert alert-danger" role="alert"/>
                    <input type="submit" class="btn btn-primary" value="UPDATE"/>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete">DELETE</button>
                </form:form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Warning</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <h3>Do You want to delete the account? Need your confirmation.</h3>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a href="/user/delete/${user.getId()}"><button type="button" class="btn btn-danger">Confirm</button></a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
