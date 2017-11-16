<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Trivia API"/>

<%@include file="head.jsp" %>
<body>
<h1>Trivia API EndPoints & Usage</h1>

<h2>Questions</h2>
<h4>teamTrivia/questions/all</h4>
<ul>
    <li>Returns all items in HTML</li>
    <li>Returns id, question, answer</li>
    <li>Option parameters include amount, difficulty, type, category</li>
    <li>Example: teamTrivia/questions/all?difficulty=Medium&type=T/F&category=Java Hibernate&amount=10</li>
</ul>

<h4>teamTrivia/questions/< id></h4>
<ul>
    <li>Returns specific item in HTML</li>
    <li>Returns all item fields, including id, question, answer, difficulty, type, and category</li>
</ul>

<h4>teamTrivia/questions/JSON/all</h4>
<ul>
    <li>Returns all items in JSON</li>
    <li>Returns id, question, answer</li>
    <li>Option parameters include amount, difficulty, type, category</li>
    <li>Example: teamTrivia/questions/JSON/all?difficulty=Medium&type=T/F&category=Java Hibernate&amount=10</li>
</ul>

<h4>teamTrivia/questions/JSON/< id ></h4>
<ul>
    <li>Return specific item in JSON</li>
    <li>Returns all item fields including id, question, answer, difficulty, type and category</li>
</ul>

<h4>Notes</h4>
<ul>
    <li>Filtering on multiple values is not supported. For example, teamTrivia/questions/all?difficulty=Medium,Hard (selecting questions with difficulty of Medium or Hard) will return no results.</li>
</ul>

<h2>Categories</h2>

<h4>teamTrivia/category/all</h4>
<ul>
    <li>Returns all items in HTML</li>
</ul>

<h4>teamTrivia/category/< id ></h4>
<ul>
    <li>Returns specific item in HTML</li>
</ul>

<h4>teamTrivia/category/JSON/all</h4>
<ul>
    <li>Returns all items in JSON</li>
</ul>

<h4>teamTrivia/category/JSON/< id ></h4>
<ul>
    <li>Returns specific item in JSON</li>
</ul>


<h2>Types</h2>
<h4>teamTrivia/type/all</h4>
<ul>
    <li>Returns all items in HTML</li>
</ul>

<h4>teamTrivia/type/< id ></h4>
<ul>
    <li>Returns specific item in HTML</li>
</ul>

<h4>teamTrivia/type/JSON/all</h4>
<ul>
    <li>Returns all items in JSON</li>
</ul>

<h4>teamTrivia/type/JSON/< id ></h4>
<ul>
    <li>Returns specific item in JSON</li>
</ul>


<h2>Difficulties</h2>
<h4>teamTrivia/difficulty/all</h4>
<ul>
    <li>Returns all items in HTML</li>
</ul>

<h4>teamTrivia/difficulty/< id ></h4>
<ul>
    <li>Returns specific item in HTML</li>
</ul>

<h4>teamTrivia/difficulty/JSON/all</h4>
<ul>
    <li>Returns all items in JSON</li>
</ul>

<h4>teamTrivia/difficulty/JSON/< id ></h4>
<ul>
    <li>Returns specific item in JSON</li>
</ul>

<h2>Check it out!</h2>
<h4>See all questions <a href="allQuestions">here</a></h4>
<h4>Try out our Question Generator <a href="questionGenerator">here</a></h4>


</body>
</html>
