<%@ page import="by.bookstore.entity.TypeOfUser" %>
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
<jsp:include page="../fragments/_header.jsp"/>
<div class="container">
    <div class="row row-cols-auto">  <%-- Количество книг на ширину контейнера: auto или требуемое число --%>
        <c:forEach items="${requestScope.currentList}" var="book">
            <div class="col">
                <div class="card" style="width: 18rem; background-color: #fafcfa; border-radius:8px; margin-top: 20px">
                    <div class="card-body">
                        <h5 class="card-title">Name: ${book.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Author: ${book.author}</h6>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOiajxMowVRnj1Z33dbn8fIj63aibIIMuW9w&usqp=CAU">
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item" style="background-color:#fafcfa ">Price: ${book.price} </li>
                        <li class="list-group-item" style="background-color:#fafcfa ">Vendor details: ${book.user.name} </li>
                        <c:if test="${book.reserved != true && book.user.id != sessionScope.user.id}">
                            <li class="list-group-item">
                             <form action="/addReservedBook" method="post">
                                 <button class="btn btn-success btn-sm" type="submit" name="book_id" value="${book.id}">
                                    Buy
                                 </button>
                             </form>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.typeOfUser == TypeOfUser.ADMIN}">
                             <li  class="list-group-item" style="text-align:center">
                             <form action="/deleteBook" method="post">
                                  <button class="btn btn-danger btn-sm" type="submit" name="book_id" value="${book.id}">
                                     Delete
                                  </button>
                             </form>
                             </li>
                        </c:if>
                    </ul>
                    <c:if test="${book.reserved == true}">
                        <div class="card-body">
                            Reserved
                        </div>
                    </c:if>
                    <c:if test="${book.user.id == sessionScope.user.id}">
                        <div class="card-body">
                            Your book
                        </div>
                    </c:if>
                    <div class="card-footer text-muted">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <div type="btn">
                                <a href="/bookComm?bookId=${book.id}" class="btn btn-outline-success btn-sm"
                                   type="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-chat-text" viewBox="0 0 16 16">
                                        <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
                                        <path d="M4 5.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zM4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8zm0 2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z"/>
                                    </svg>
                                </a>
                            </div>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            ${book.comments.size()}
                            </div>
                            <div type="btn" style="padding-left: 40px">
                                <a href="/addLike?book_id=${book.id}" class="btn btn-outline-success btn-sm"
                                   type="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-heart-fill" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                    </svg>
                                </a>
                            </div>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                                    ${book.likes.size()}
                            </div>
                            <div type="btn">
                                <a href="/updRatingBook?bookId=${book.id}" class="btn btn-outline-success btn-sm"
                                   type="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                                    </svg>
                                </a>
                            </div>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                                    ${book.rating}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6 m-4">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:if test="${requestScope.currentPage != 1}">
                            <a class="page-link" href="/bookStore?currentPage=${requestScope.currentPage - 1}">Previous</a>
                        </c:if>
                    </li>
                    <c:forEach begin="1" end="${requestScope.numPages}" var="i">
                    <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li class="page-item disabled">
                            <a class="page-link">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                    <li class="page-item"><a href="/bookStore?currentPage=${i}"
                                             class="page-link">${i}</a>
                        </c:otherwise>
                        </c:choose>
                        </c:forEach>
                    </li>
                    <li class="page-item">
                        <c:if test="${requestScope.currentPage lt requestScope.numPages}">
                    <li class="page-item">
                        <a href="/bookStore?currentPage=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                    </c:if>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
