<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Trivia API | Get Questions"/>

<%@include file="head.jsp" %>
<body>
<div style="">
    <h3>Fill out form to generate questions.</h3>
</div>
<div>
    <form method="GET" action="teamTrivia/questions/all">
        <div class="form-group">
            <label for="question-category">Category: </label>
            <select class="form-control" id="question-category" name="category">
                <option value="select">Select a Category</option>
                <c:forEach var="category" items="${question_categories}">
                    <option value="${category.categoryName}">${category.categoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="question-amount">Amount of Questions: </label>
            <input type="number" class="form-control" placeholder="0" min="0" id="question-amount" name="amount">
        </div>
        <div class="form-group">
            <label for="question-type">Type of Questions: </label>
            <select class="form-control" id="question-type" name="type">
                <option value="select">Select a Type</option>
                <c:forEach var="type" items="${question_types}">
                    <option value="${type.typeName}">${type.typeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="question-difficulty">Difficulty: </label>
            <select class="form-control" id="question-difficulty" name="difficulty">
                <option value="select">Select Question Difficulty</option>
                <c:forEach var="difficulty" items="${question_difficulties}">
                    <option value="${difficulty.difficultyName}">${difficulty.difficultyName}</option>
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
