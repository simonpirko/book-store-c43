<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RatingBook</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container"><img src="/images/star.png" alt="" height="300" class="rounded mx-auto d-block">
    <div class="row justify-content-center">
        <div class="col-sm-4" style="text-align: center; padding: 30px 20px 20px; border-radius:8px; background: #e3f2fd; margin-top: 40px">
            <form action="/updRatingBook" method="post">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="score">
                    <label class="form-check-label" for="inlineRadio1">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="score">
                    <label class="form-check-label" for="inlineRadio2">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="score">
                    <label class="form-check-label" for="inlineRadio3">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio4" value="score">
                    <label class="form-check-label" for="inlineRadio3">4</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio5" value="score">
                    <label class="form-check-label" for="inlineRadio3">5</label>
                </div>
                <br>
                <button type="submit" style="margin-top: 15px" class="btn btn-success">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>