<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subjects</title>
</head>
<body>
<%@include file="menu.jsp"%>

</br>
</br>

<!--Add student link-->
<div align="center">
    <form action="university" method="get">
        <input type="hidden" name="url" value="subjectAdd">
        <input type="submit" value="Добавить предмет">
    </form>
</div>
</br>
</br>

<h4 align="center">Предметы</h4>
<div align="center">
    <table border=1 bgcolor="#FFDEAD">
        <tr>
            <td><B>Предмет</B></td>
        </tr>
        <c:forEach items="${subjectList}" var="subject">
            <c:if test="${subject.deleted eq false}">
                <tr>
                    <td>${subject.title}</td>
                    <td>
                        <form action="university" method="get">
                            <input type="hidden" name="url" value="subjectUpdate">
                            <input type="hidden" name="subjectId" value="${subject.id}">
                            <input type="submit" value="Редактировать">
                        </form>
                    </td>
                    <td>
                        <form action="university" method="get">
                            <input type="hidden" name="url" value="subjectDelete">
                            <input type="hidden" name="subjectId" value="${subject.id}">
                            <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>

</body>
</html>
