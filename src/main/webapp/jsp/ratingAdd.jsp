<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Rating add</title>
</head>
<body>
<%@include file="menu.jsp"%>

<table align="center" border=1 bgcolor="#FFDEAD" cellpadding="3">
    <h4 align="center" >Выберите оценку</h4>
    <c:forEach items="${marks}" var="mark">
        <tr>
            <td>
                <form action="university" method="post">
                    <input type="hidden" name="url" value="ratingAddForm">
                    <input type="hidden" name="attendId" value="${attendId}">
                    <input type="hidden" name="mark" value="${mark}">
                    <input type="submit" value="${mark}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
