<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container container-fluid">
            <div class="navbar-header">
                <span class="navbar-brand bottomNav">Online Gallery</span>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/load_active">Home</a></li>
                <li><a href="/add_client">Clients</a></li>
                <li><a href="/employees">Employees</a></li>
                <li><a href="/car_load">Vehicles</a></li>
                <li><a href="/picture/read/all/page/1">Read All</a></li>
                <li><a href="/picture/add">Add Picture</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()"><li><a href="/perform_logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li></sec:authorize>
            </ul>
        </div>
    </nav>
</header>