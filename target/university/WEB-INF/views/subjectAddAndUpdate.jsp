<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Предмет</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
	.subjectAdd{
			position: relative;
            left: 10px;
			top: 40px;
	}
    .error {
        color: #ff0000;
    }
</style>

<div class="subjectAdd">
    <c:choose>
        <c:when test="${edit eq true}">
            <h2>Форма редактирования предмета</h2>
        </c:when>
        <c:otherwise>
            <h2>Форма добавления предмета</h2>
        </c:otherwise>
    </c:choose>
    <form:form  method="post" modelAttribute="subject">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="title">Предмет</label></td>
                <td><form:input path="title" id="title"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3" >
                    <c:choose>
                        <c:when test="${edit eq true}">
                            <input type="submit" value="Обновить"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Добавить"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
