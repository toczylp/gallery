<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>

    <nav class="navbar navbar-inverse navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <div class="navbar-header">
                <span class="navbar-brand bottomNav">Online Gallery</span>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/read/top_rated/page/1">Top Rated</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/read/most_viewed/page/1">Most Viewed</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/read/latest">Latest</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categories
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <c:forEach items="${categories}" var="el">
                                <a class="dropdown-item" href="/picture/read/category/${el.getName()}/page/1">${el.getName()}</a><br>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/my_gallery/page/1">Show My Gallery</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/all/public/page/1">Read All Public</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/read/all/page/1">Read All</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picture/add">Add Picture</a>
                    </li>
                </ul>
            </div>
            <div>
                <ul class="navbar-nav navbar-right">
                    <sec:authorize access="!isAuthenticated()">
                        <li><a href="/signup"><i class="fas fa-user-plus"></i> Sign Up  </a></li>
                        <li><a href="/login"> <i class="fas fa-sign-in-alt"></i> Login </a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li><a href="/user/details"><i class="far fa-user-circle"></i> User  </a></li>
                        <li><a href="/perform_logout"><i class="fas fa-sign-out-alt"></i> Logout </a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>