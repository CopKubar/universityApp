<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Subject update</title>
</head>
<body>
    <%@include file="menu.jsp"%>

    <c:if test="${sessionScope.title ne null}">
        <h4 align="center" style="color:#FF0000">Проверте правильность ввода</h4>
    </c:if>

    </br>
    </br>

        <div align="center">
            <form action="university" method="post">
            <input type="hidden" name="url" value="subjectUpdateForm">
                <c:choose>
                    <c:when test="${sessionScope.subjectId ne null}">
                        <input type="hidden" name="subjectId" value="${sessionScope.subjectId}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="subjectId" value="${subject.id}">
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${sessionScope.title ne null}">
                        <h4>Предмет</h4> <input type="text" name="title" value="${sessionScope.title}">
                    </c:when>
                    <c:otherwise>
                        <h4>Предмет</h4> <input type="text" name="title" value="${subject.title}">
                    </c:otherwise>
                </c:choose>
            <br/><br/>
            <input type="submit" value="Редактировать">
            </form>
        </div>

</body>
</html>
