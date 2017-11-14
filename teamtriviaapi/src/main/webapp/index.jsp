<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="head.jsp" %>
<body>
<div style="">
    <h3>Fill out form to generate questions.</h3>
</div>
<div>
    <form>
        <div class="form-group">
            <label for="question-category">Category: </label>
            <select class="form-control" id="question-category" name="question-category">
                <option value="select">Select a Category</option>
                <c:forEach var="category" items="${question-categories}">
                    <option value="${category.CategoryID}">${category.CategoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="question-amount">Amount of Questions: </label>
            <input type="number" class="form-control" placeholder="0" min="0" id="question-amount" name="question-amount">
        </div>
        <div class="form-group">
            <label for="question-type">Type of Questions: </label>
            <select class="form-control" id="question-type" name="question-type">
                <option value="select">Select a Type</option>
                <c:forEach var="type" items="${question-types}">
                    <option value="${type.TypeID}">${type.TypeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="question-difficulty">Difficulty: </label>
            <select class="form-control" id="question-difficulty" name="question-difficulty">
                <option value="select">Select Question Difficulty</option>
                <c:forEach var="difficulty" items="${question-difficulties}">
                    <option value="${difficulty.DifficultyID}">${difficulty.DifficultyName}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-success" name="submit" id="submit" value="submit">Submit</button>
        </div>
    </form>

    <a class="btn btn-success" href="addQuestion">Add a Question?</a>

</div>
</body>
</html>
