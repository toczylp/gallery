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
<div class="container">
        <div class="container">
            <c:forEach items="${details}" var="el" varStatus="status">
                    <div class="row">
                        <p>${el.key}<span> : </span>${el.value}</p>
                    </div>
            </c:forEach>
        </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
