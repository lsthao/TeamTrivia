<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Questions Generator</title>

    <style type="text/css">
        body {
            width: 50%;
            margin: auto;
        }

    </style>

</head>
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
                <c:forEach var="category" items="${question-categoryies}">
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
</div>
</body>
</html>
