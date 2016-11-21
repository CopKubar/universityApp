<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Add subject for student</title>
</head>
<body>
<%@include file="menu.jsp"%>

<style>
        .attendAdd {
            position: relative;
            bottom: 70px;
            left: 170px;
        }
		.profile{
            position: relative;
			top: 10px;
            left: 10px;
        }
    </style>

<div class="profile">
<form action="university" method="get">
     <input type="hidden" name="url" value="profile">
     <input type="hidden" name="studentId" value="${studentId}">
     <input type="submit" value="Профиль">
</form>
</div>
	
<div class="attendAdd">
<table border="2" bgcolor="#C1CDCD">
    <th>Выберите предмет, который надо назначить студенту</th>
    <c:forEach items="${subjectList}" var="subject">
        <c:if test="${subject.deleted eq false}">
        <tr>
            <td>
                <form action="university" method="post">
                    <input type="hidden" name="url" value="attendAddForm">
                    <input type="hidden" name="studentId" value="${studentId}">
                    <input type="hidden" name="subjectId" value="${subject.id}">
                    <input type="submit" value="${subject.title}">
                </form>
            </td>
        </tr>
        </c:if>
    </c:forEach>
</table>
<div>
</body>
</html>
