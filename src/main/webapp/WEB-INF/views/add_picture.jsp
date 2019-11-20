<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="common/meta.jsp"></jsp:include>
    <title>Add Image</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container"><br>
    <h4>Add an image</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <form action="/picture/add" method="post" enctype="multipart/form-data">
                    <p>Select image:</p><br>
                    <div class="input-group form-group col-md-5">
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
                        </div>
                        <div class="form-group col-md-2">
                            <label for="btn-upload"><span class="invisible">D</span> </label><br>
                            <input type="submit" id="btn-upload" class="btn btn-primary" value="Upload"/>
                        </div>
                    </div>
                    <c:if test="${noCategoryChosenError != null}">
                        <div class="row">
                            <div class="form-group col-md-6">
                                <div class="alert alert-danger" role="alert">
                                    <h4>Error message:</h4>
                                    <p>${noCategoryChosenError.toString()}</p>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${error != null}">
                        <div class="row">
                            <div class="form-group col-md-6">
                            <div class="alert alert-danger" role="alert">
                                <h4>Error message:</h4>
                                <c:forEach items="${error}" var="el">
                                    <p>${el.toString()}</p>
                                </c:forEach>
                            </div>
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
