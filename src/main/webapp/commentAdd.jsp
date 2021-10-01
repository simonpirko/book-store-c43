<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 26.09.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add comment</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-4 m5 bg-success p-2 text-dark bg-opacity-10 rounded">
            <form action="/addComment" method="post">
                <div class="form-floating">
                <textarea class="form-control" placeholder="description" name="description"
                          id="floatingTextarea"></textarea>
                    <label for="floatingTextarea">Comments</label>
                    <div style="text-align:center">
                        <button type="submit" class="btn btn-success" style="">Add comment</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row justify-content-center">
    <c:if test="${requestScope.message_add_com != null}">
        <div class="col-sm-3 text-center m-3">
            <div class="alert alert-warning" role="alert">
                <p>${requestScope.message_add_com}</p>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
