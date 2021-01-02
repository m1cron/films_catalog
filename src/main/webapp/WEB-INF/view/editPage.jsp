<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<br/>
<c:url value="/edit" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${film.id}">
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <br/><br/>
    <label for="year">Year</label>
    <input type="text" name="year" id="year">
    <br/><br/>
    <label for="genre">Genre</label>
    <input type="text" name="genre" id="genre">
    <br/><br/>
    <label for="watched">Watched</label>
    <input type="text" name="watched" id="watched">
    <br/><br/>
    <input type="submit" value="Commit changes!">
</form>
</body>
</html>