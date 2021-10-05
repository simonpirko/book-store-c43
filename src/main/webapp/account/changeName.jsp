<%--
  Created by IntelliJ IDEA.
  User: UserOk
  Date: 26.09.2021
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change name</title>
</head>
<body>
<jsp:include page="../fragments/_header.jsp"/>
<div class="container" >
<div class="row justify-content-center" >
    <div class="col-sm-3" style="padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px" >
        <legend style="text-align: center; padding-bottom: 20px">Please enter the data</legend>
        <form action="/updateNameUser" method="post">
            <div class="mb-3">
                <input type="text" name="newName" class="form-control"  aria-describedby="emailHelp" required placeholder="New name">
            </div>
            <div style="text-align:center">
                <button type="submit" class="btn btn-success" style="">Change</button>
            </div>
        </form>
        <c:if test="${requestScope.message_upd_n != null}">
            <div class="alert alert-warning" role="alert">
                <p>${requestScope.message_upd_n}</p>
            </div>
        </c:if>
    </div>
</div>
</div>
</body>
</html>
