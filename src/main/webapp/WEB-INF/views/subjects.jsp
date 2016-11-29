<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Предметы</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
	.tableSubjects{
			position: relative;
			bottom: 70px;
			left: 120px;
		}
	.subjectAdd{
            position: relative;
			top: -45px;
			left: 210px;
        }
</style>

<div class="subjectAdd">
    <a href="<c:url value='/subjectAdd' />">Добавить предмет</a>
</div>
</br>
</br>

<div class="tableSubjects">
    <table border=2 bgcolor="#C1CDCD">
        <tr>
            <td align="center"><B>Предмет</B></td>
        </tr>
        <c:forEach items="${subjectList}" var="subject">
            <c:if test="${subject.deleted eq false}">
                <tr>
                    <td align="center">${subject.title}</td>
                    <td align="center">
                        <a href="<c:url value='/subject/update/${subject.id}' />">Редактировать</a>
                    </td>
                    <td align="center">
                        <a href="<c:url value='/subject/delete/${subject.id}' />">Удалить</a>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>
