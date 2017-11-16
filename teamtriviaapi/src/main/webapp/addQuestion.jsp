<%--
  Created by IntelliJ IDEA.
  User: sarah
  Date: 11/13/2017
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Trivia API | Add Question"/>
<html>
<%@include file="head.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>

<form method="post" action="teamTrivia/questions/HTML/create">
    <h3>New Question</h3>
    <div><p>${question_type_post}</p></div>
    <div class="form-group">
        <label for="question-category">Category: </label>
        <select class="form-control" id="question-category" name="category">
            <option value="select">Select a Category</option>
            <c:forEach var="category" items="${question_categories_post}">
                <option value="${category.categoryID}">${category.categoryName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="question-type">Type: </label>
        <select class="form-control" id="question-type" name="type">
            <option value="select">Select a Type</option>
            <c:forEach var="type" items="${question_types_post}">
                <option value="${type.typeID}">${type.typeName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="question-difficulty">Difficulty: </label>
        <select class="form-control" id="question-difficulty" name="difficulty">
            <option value="select">Select Question Difficulty</option>
            <c:forEach var="difficulty" items="${question_difficulties_post}">
                <option value="${difficulty.difficultyID}">${difficulty.difficultyName}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="question">Question: </label>
        <input type="text" class="form-control" id="question" name="question"/>
    </div>

    <div class="form-group hidden" id="TF">
        <label for="answerTF">Answer: </label>
        <select class="form-control" id="answerTF" name="answerTF">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
    </div>

    <div class="form-group hidden" id="short">
        <label for="answerShort">Answer: </label>
        <textarea class="form-control" id="answerShort" name="answerShort"></textarea>
    </div>

    <div>
        <button type="submit" class="btn btn-success" name="submit" id="submit" value="submit">HTML Submit</button>
    </div>
</form>
<form method="post" action="teamTrivia/questions/JSON/create">
    <h3>New Question</h3>

    <div class="form-group">
        <label for="question-category">Category: </label>
        <select class="form-control" id="question-category2" name="category">
            <option value="select">Select a Category</option>
            <c:forEach var="category" items="${question_categories_post}">
                <option value="${category}">${category}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="question-type">Type: </label>
        <select class="form-control" id="question-type2" name="type">
            <option value="select">Select a Type</option>
            <c:forEach var="type" items="${question_types_post}">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="question-difficulty">Difficulty: </label>
        <select class="form-control" id="question-difficulty2" name="difficulty">
            <option value="select">Select Question Difficulty</option>
            <c:forEach var="difficulty" items="${question_difficulties_post}">
                <option value="${difficulty}">${difficulty}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="question">Question: </label>
        <input type="text" class="form-control" id="question2" name="question"/>
    </div>

    <div class="form-group hidden" id="TF2">
        <label for="answerTF">Answer: </label>
        <select class="form-control" id="answerTF2" name="answerTF">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
    </div>

    <div class="form-group hidden" id="short2">
        <label for="answerShort">Answer: </label>
        <textarea class="form-control" id="answerShort2" name="answerShort"></textarea>
    </div>

    <div>
        <button type="submit" class="btn btn-success" name="submit" id="submit2" value="submit">JSON Submit</button>
    </div>
</form>
<script>
    $(document).ready(function(){

        $("#question-type").change(function () {
            var value = $(this).val();
            //make this coresspond to answer type via users db
            if (value == "1") {
                document.getElementById("TF").classList.remove("hidden");
                document.getElementById("short").classList.add("hidden");

            } else if (value == "2") {
                document.getElementById("TF").classList.add("hidden");
                document.getElementById("short").classList.remove("hidden");
            }
        });

        $("#question-type2").change(function () {
            var value = $(this).val();

            if (value == "Type{TypeID=1, TypeName=\'T/F\'}") {
                document.getElementById("TF2").classList.remove("hidden");
                document.getElementById("short2").classList.add("hidden");

            } else if (value == "Type{TypeID=2, TypeName=\'Short Answer\'}") {
                document.getElementById("TF2").classList.add("hidden");
                document.getElementById("short2").classList.remove("hidden");
            }
        });
    });

</script>

</body>
</html>
