<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty film.title}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty film.title}">
        <title>Edit</title>
    </c:if>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${empty film.title}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty film.title}">
    <c:url value="/edit" var="var"/>
</c:if>
<br/>
<form action="${var}" method="POST">
    <c:if test="${!empty film.title}">
        <input type="hidden" name="id" value="${film.id}">
    </c:if>
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
    <c:if test="${empty film.title}">
        <input type="submit" value="Add!">
    </c:if>
    <c:if test="${!empty film.title}">
        <input type="submit" value="Edit!">
    </c:if>
</form>
</body>
</html>