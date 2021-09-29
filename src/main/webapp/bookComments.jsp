<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 29.09.2021
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book comments</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row row-cols-auto">
        <div class="card" style="width: 16rem; background-color: #fafcfa; border-radius:8px; margin-top: 20px">
            <div class="card-body">
                <h5 class="card-title">Name: ${requestScope.book.name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">Author: ${requestScope.book.author}</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of
                    the card's content.</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item" style="background-color:#fafcfa ">Rating: ${requestScope.book.rating} </li>
                <li class="list-group-item" style="background-color:#fafcfa ">Price: ${requestScope.book.price} </li>
            </ul>
            <c:if test="${requestScope.book.reserved != true}">
                <div class="card-body">
                    <form action="/bookStore" method="post">
                        <button class="btn btn-success btn-sm" type="submit" name="bookReserved"
                                value="${requestScope.book.id}">
                            Buy
                        </button>
                    </form>
                </div>
            </c:if>
            <div class="card-footer text-muted">
                <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                    <div type="btn">
                        <a href="/bookComm?bookId=${book.id}" class="btn btn-outline-success btn-sm" type="button">Comment</a>
                    </div>
                    <div type="btn" style="padding-left: 40px">
                        <form action="/bookStore" method="post">
                            <button class="btn btn-outline-success btn-sm" type="submit" name="bookLike"
                                    value="${requestScope.book.id}">Like
                            </button>
                        </form>
                    </div>
                    <div type="btn">
                        <a href="#" class="btn  btn-sm disabled" type="button"
                           style="border-radius: 100px; background-color: #b6d9b6">
                            ${requestScope.book.likes}
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row row-cols-auto">
        <div class="col-sm-8" style="padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px">
            <c:forEach var="comment" items="${requestScope.listCommentsByBookId}">
                <ol class="list-group list-group-numbered">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${comment.user.name}</div>
                                ${comment.description}
                        </div>
                        <c:if test="${sessionScope.user.login.equals(comment.user.login)}">
                            <span class="badge bg-primary rounded-pill"><a href="//updComm?bookId=${book.id}" class="btn btn-outline-success btn-sm" type="button">Update</a></span>
                            <span class="badge bg-primary rounded-pill"><a href="/deleteComm?idComment=${comment.id}" class="btn btn-outline-success btn-sm" type="button">Delete</a></span>
                        </c:if>
                    </li>
                </ol>
            </c:forEach>
        </div>
    </div>
</div>
</div>
</body>
</html>
