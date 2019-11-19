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
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputGroupFile01" name="file" path="pic"
                                   aria-describedby="inputGroupFileAddon01">
                            <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                            </div>
                    </div><br>
                    <div class="row input-group">
                        <div class="form-check form-check-inline">
                            <input type="radio" id="one" name="publicFlag" value="1" checked>
                            <label for="one"><h4>Public</h4></label>
                            <input type="radio" id="two" name="publicFlag" value="0">
                            <label for="two"><h4>Make it Private</h4></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label for="category">Select Category</label>
                            <select id="category" name="category" class="form-control">
                                <option selected>----</option>
                                <c:forEach items="${categories}" var="el">
                                    <option>${el.getName()}</option>
                                </c:forEach>
                            </select>
                            <c:if test="${noCategoryChosenError != null}">
                                <div class="alert alert-danger" role="alert">
                                    <h4>Error message:</h4>
                                        <p>${noCategoryChosenError.toString()}</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="row"/>
                    <div class="form-group col-md-2">
                        <input type="submit" class="btn btn-primary" value="Upload"/>
                    </div>
                    <c:if test="${error != null}">
                        <div class="row">
                            <div class="alert alert-danger" role="alert">
                                <h4>Error message:</h4>
                                <c:forEach items="${error}" var="el">
                                    <p>${el.toString()}</p>
                                </c:forEach>
                            </div>
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
