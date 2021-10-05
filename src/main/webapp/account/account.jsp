<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gudwin
  Date: 21.08.2021
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<jsp:include page="../fragments/_header.jsp"/>
<div class="container">
    <div class="row justify-content-center" >
        <div class="col-sm-3" style="padding: 20px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 10px" >
            <form action="/account" method="get">
                <div class="card" style="width: 18rem;">
                    <div class="card-header" style="text-align: center">
                        User account
                    </div>
                    <c:if test="${sessionScope.user.picture == null}">
                        <img src="../images/accountAvatar.png" class="card-img-top" >
                    </c:if>
                    <c:if test="${sessionScope.user.picture != null}">
                        <img src="${sessionScope.user.picture}" class="card-img-top">
                    </c:if>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><b>name:</b> ${sessionScope.user.name} <a style="float:right " href="/updateNameUser" class="card-link"><img style="height: 20px; width: 20px" src="/images/edit.jpg"></a></li>
                        <li class="list-group-item"><b>login:</b> ${sessionScope.user.login}</li>
                        <li class="list-group-item"><b>type:</b> ${sessionScope.user.typeOfUser.toString()}</li>
                        <li class="list-group-item"><b>password:</b> *********<a style="float:right " href="/updatePasswordUser" class="card-link"><img style="height: 20px; width: 20px" src="/images/edit.jpg"></a></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

