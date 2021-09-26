<%--
  Created by IntelliJ IDEA.
  User: Gudwin
  Date: 23.09.2021
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bookstore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Berkshire+Swash&display=swap" rel="stylesheet">

    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }
    </style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light mb-5"  style="background-color: #e3f2fd;">
    <div class="container-fluid ">
          <a class="navbar-brand" href="/bookStore" style="font-family: 'Berkshire Swash', cursive; margin-left: 40px">
             <img src="/images/BOOKS.png" alt=""  height="30" class="d-inline-block align-text-top">
               Bookstore
          </a>
          <div style="padding-right: 150px">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0" >
                      <li class="nav-item">
                          <a class="nav-link active" href="/auth">Login</a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link active"  href="/reg">Sign-up</a>
                      </li>
                      <li style="margin-left:  150px">
                          <div class="pull-right navbar-text">
                              <c:if test="${sessionScope.user == null}">
                                    <a class="pull-right navbar-text" href="#" style="padding-right: 5px">Guest</a>
                              </c:if>
                              <c:if test="${sessionScope.user != null}">
                                    <a class="pull-right navbar-text" href="#" style="padding-right: 5px">${sessionScope.user.name}</a>
                              </c:if>
                                    <img src="/images/avatar_circle.jpg" width="30px" height="30px" style="border-radius: 100px">
                              <c:if test="${sessionScope.user != null}">
                              <li class="nav-item dropdown" style="padding-top: 13px; padding-left: 0">
                                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                      <li><a class="dropdown-item" href="/profile">Profile</a></li>
                                      <li><a class="dropdown-item" href="/addBook">Add book</a></li>
                                      <li><hr class="dropdown-divider"></li>
                                      <li><a class="dropdown-item" href="/logout">Sign-out</a></li>
                                  </ul>
                              </li>
                              </c:if>
                              <a class="pull-right navbar-text"  style="padding-left: 30px" href="">
                                  <img src="/images/basket_2.png" width="30px" height="30px">
                              </a>
                          </div>
                      </li>
                </ul>
          </div>
    </div>
</nav>
<script src=" https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>