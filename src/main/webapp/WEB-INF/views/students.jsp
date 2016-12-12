<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Студенты</title>

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/student.js"></script>
</head>
<body>

<style>
    .tableStudents{
        position: relative;
        bottom: 55px;
        left: 120px;
    }
    .studentAdd{
        position: relative;
        top: -65px;
        left: 120px;
    }
    .error {
        position: relative;
        top: 20px;
        left: 450px;
        color: #ff0000;
    }
    .success{
        position: relative;
        top: -50px;
        left: 120px;
        color: #14F804;
    }
</style>

<%@include file="menu.jsp"%>

<div id="container">

    <div id="error" class="error"></div>

    <div class="studentAdd">
        <form:form modelAttribute="student" id="saveStudent">
            <h2>Форма добавления студента</h2>
            <div>
                <label for="firstNameInput">Имя</label>
                <form:input path="firstName" type="text" name="firstName" id="firstNameInput"/>
            </div></br>
            <div>
                <label for="lastNameInput">Фамилия</label>
                <form:input path="lastName" type="text" name="lastName" id="lastNameInput"/>
            </div></br>
            <div>
                <label for="entranceYearInput">Дата поступления(dd/mm/yyyy):</label>
                <form:input path="entranceYear" type="text" name="entranceYear" id="entranceYearInput"/>
            </div>
            <div>
                <input id="submit" type="submit" value="Добавить"/>
            </div>
        </form:form>
    </div>

    <div id="success" class="success"></div>
    </br></br>

    <div class="tableStudents">
        <table border="2" bgcolor="#C1CDCD" id="studentTableResponse">
            <tr>
                <td align="center"><B>Имя</B></td>
                <td align="center"><B>Фамилия</B></td>
                <td align="center"><B>Дата поступления</B></td>
            </tr>
            <c:forEach items="${studentList}" var="student">
                <c:if test="${student.deleted eq false}">
                    <tr>
                        <td align="center">${student.firstName}</td>
                        <td align="center">${student.lastName}</td>
                        <td align="center">${student.entranceYear}</td>
                        <td align="center">
                            <a href="<c:url value='/student/profile/${student.id}' />">Профиль</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>

</div>

</body>
</html>