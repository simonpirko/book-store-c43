<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>RatingBook</title>
</head>
<body>
<jsp:include page="../fragments/_header.jsp"/>
<div class="container">
    <img src="/images/ratingstar.png" alt="" height="300" class="rounded mx-auto d-block">
    <div class="row justify-content-center">
        <div class="col-sm-4"
             style="text-align: center; padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px">
            <label class="form-check-label" for="rating">Please, rate the book.</label>
            <form action="/updRatingBook" method="post" id="rating" >
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="score" id="inlineRadio1" value="1">
                    <label class="form-check-label" for="inlineRadio1">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="score" id="inlineRadio2" value="2">
                    <label class="form-check-label" for="inlineRadio2">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="score" id="inlineRadio3" value="3">
                    <label class="form-check-label" for="inlineRadio3">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="score" id="inlineRadio4" value="4">
                    <label class="form-check-label" for="inlineRadio3">4</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="score" id="inlineRadio5" value="5">
                    <label class="form-check-label" for="inlineRadio3">5</label>
                </div>
                <br>
                <button type="submit" style="margin-top: 15px" class="btn btn-success">Submit</button>
            </form>
        </div>
    </div>
    <c:if test="${requestScope.message_upd_rating != null}">
        <div class="row justify-content-center">
            <div class="col-sm-4 m-3">
                <div class="alert alert-warning" role="alert" >
                    <p>${requestScope.message_upd_rating}</p>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>