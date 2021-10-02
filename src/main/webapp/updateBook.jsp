<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 29.09.2021
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container" >
    <div class="row justify-content-center">
        <div class="col-sm-4" style="padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px" >
            <legend style="text-align: center; padding-bottom: 20px">Enter info to update book</legend>
            <form action="/updBook" method="post">
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
        </div>
    </div>
</div>
</body>
</html>
