<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Add subject for student</title>
</head>
<body>
<%@include file="menu.jsp"%>

<table align="center" border=1 bgcolor="#FFDEAD" cellpadding="3">
    <h4 align="center" >Выберите предмет, который надо назначить студенту</h4>
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
</body>
</html>
