<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/21/17
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Trivia API | All Questions"/>
<html>
<%@include file="head.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>

${allQuestions}
<table id="all-questions">
    <thead>
    <tr>
        <th>Question</th>
        <th>Answer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="question" items="${allQuestions}">
        <tr>
            <td>${question.question}</td>
            <td>${question.answer}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script>
    $(function(){
        $("#all-questions").dataTable();
    })
</script>
</body>
</html>