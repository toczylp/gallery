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
                <li><a href="/picture/read/most_viewed/page/1">Most Viewed</a></li>
                <li><a href="/picture/read/latest">Latest</a></li>
                <li><a href="/picture/my_gallery/page/1">Show My Gallery</a></li>
                <li><a href="/picture/all/public/page/1">Read All Public</a></li>
                <li><a href="/picture/read/all/page/1">Read All</a></li>
                <li><a href="/picture/add">Add Picture</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/user/details"><span class="glyphicon glyphicon-user"></span>User</a></li>
                    <li><a href="/perform_logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>