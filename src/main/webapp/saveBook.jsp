<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 26.09.2021
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new book</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" >
    <div class="row justify-content-center">
        <div class="col-sm-4" style="padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px" >
            <legend style="text-align: center; padding-bottom: 20px">Enter info of your new book</legend>
            <form action="/addBook" method="post">
                <div class="m-3" >
                    <input type="text" name="name" class="form-control" required placeholder="Name">
                </div>
                <div class="m-3" >
                    <input type="text" name="author" class="form-control" required placeholder="Author">
                </div>
                <div class="m-3" >
                    <input type="number" name="rating" class="form-control" required placeholder="Rating">
                </div>
                <div class="m-3" >
                    <input type="number" name="price" class="form-control" required placeholder="Price">
                </div>
                <div style="text-align:center">
                    <button type="submit" class="btn btn-success" style="">Submit</button>
                </div>
            </form>
            <c:if test="${requestScope.message_add_book != null}">
                <div class="alert alert-warning" role="alert" >
                    <p>${requestScope.message_add_book}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
