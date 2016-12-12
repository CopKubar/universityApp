<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Предметы</title>

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/subject.js"></script>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
    .tableSubjects{
        position: relative;
        bottom: 55px;
        left: 120px;
    }
    .subjectAdd{
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

<div id="container">

    <div id="error" class="error"></div>

    <div class="subjectAdd">
        <form:form modelAttribute="subject" id="saveSubject">
            <h2>Форма добавления учебного предмета</h2>
            <div>
                <label for="titleInput">Название предмета</label>
                <form:input path="title" type="text" name="title" id="titleInput"/>
            </div></br>
            <div>
                <input id="submit" type="submit" value="Добавить"/>
            </div>
        </form:form>
    </div>

    <div id="success" class="success"></div>
    </br>
    </br>

    <div class="tableSubjects">
        <table border=2 bgcolor="#C1CDCD" id="subjectsTableResponse">
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
                            <a href="<c:url value='/subject/delete/${subject.id}' />"  class="confirmation">Удалить</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
