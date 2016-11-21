<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Rating add</title>
</head>
<body>
<%@include file="menu.jsp"%>

<style>
        .ratingAdd{
            position: relative;
            bottom: 70px;
            left: 180px;
        }
</style>
<div class="ratingAdd">
    <h4>Выберите оценку</h4>
	<form action="university" method="post">
		<input type="hidden" name="url" value="ratingAddForm">
		<input type="hidden" name="attendId" value="${attendId}">
		<select name="mark">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
		</select>
		<input type="submit" value="Поставить">
	</form>
</div>
</body>
</html>
