<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gudwin
  Date: 23.09.2021
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_header.jsp"/>
<html>
<head>
    <title>Bookstore</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        card {
            background-color: #fff5d6;
        }
    </style>
</head>
<body>
<div class="container" >
    <div class="row row-cols-auto">  <%-- Количество книг на ширину контейнера: auto или требуемое число --%>
               <c:forEach items="${requestScope.allBooks}" var="book">
        <div class="col">
            <div class="card" style="width: 16rem; background-color: #fafcfa; border-radius:8px; margin-top: 20px">
                <div class="card-body">
                    <h5 class="card-title">Name: ${book.name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Author: ${book.author}</h6>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                </div>
                <ul class="list-group list-group-flush" >
                    <li class="list-group-item" style="background-color:#fafcfa ">Rating: ${book.rating} </li>
                    <li class="list-group-item" style="background-color:#fafcfa ">Price: ${book.price} </li>
                </ul>
                <div class="card-body" >
                    <form action="/bookStore" method="post">
                        <button class="btn btn-success btn-sm"  type="submit" name="bookReserved" value="${book.id}">Buy</button>
                    </form>
                </div>
                <div class="card-footer text-muted">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                        <div type="btn" >
                            <a href="#" class="btn btn-outline-success btn-sm" type="button" >Comment</a>
                        </div>
                        <div type="btn" style="padding-left: 40px">
                            <form action="/bookStore" method="post">
                                <button class="btn btn-outline-success btn-sm"  type="submit" name="bookLike" value="${book.id}">Like</button>
                            </form>
                        </div>
                        <div type="btn">
                            <a href="#" class="btn  btn-sm disabled" type="button"  style="border-radius: 100px; background-color: #b6d9b6">
                                ${book.likes}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                </c:forEach>
    </div>
</div>

</body>
</html>
