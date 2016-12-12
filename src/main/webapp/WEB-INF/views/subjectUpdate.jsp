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
            <h2>Форма редактирования предмета</h2>
    <form:form  method="post" modelAttribute="subject" action="id">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="title">Предмет</label></td>
                <td><form:input path="title" id="title"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3" >
                            <input type="submit" value="Обновить"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
