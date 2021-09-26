<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gudwin
  Date: 23.09.2021
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" >
    <div class="row justify-content-center" >
        <div class="col-sm-3" style="padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px" >
            <legend style="text-align: center; padding-bottom: 20px">Please sign up</legend>
            <form action="/reg" method="post">
                <div class="mb-3" >
                    <input type="text" name="name" class="form-control"  aria-describedby="emailHelp" required placeholder="Name">
                </div>
                <div class="mb-3">
                    <input type="text" name="login" class="form-control"  aria-describedby="emailHelp" required placeholder="Login">
                </div>
                <div class="mb-3">
                    <input type="password" name="password" class="form-control" aria-describedby="passwordHelp" required placeholder="Password">
                    <div id="passwordHelp" class="form-text" style="text-align: center">Do not share your password with anyone else</div>
                </div>
                <div class="mb-3" >
                    <input type="text" name="picture" class="form-control"  aria-describedby="emailHelp" required placeholder="Photo for profile (URL)">
                </div>
                <div style="text-align:center">
                    <button type="submit" class="btn btn-success" style="">Sign-up</button>
                </div>
            </form>
            <c:if test="${requestScope.message_reg != null}">
                <div class="alert alert-warning" role="alert">
                    <p>${requestScope.message_reg}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
