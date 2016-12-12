<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        top: 30px;
        left: 10px;
    }
</style>

    <div class="profile">
        <a href="<c:url value='/student/profile/${student.id}' />">Профиль</a>
    </div>

    <div class="attendAdd">
        <h4>Назначить предмет студенту ${student.firstName} ${student.lastName}</h4>
        <form:form action="attendSave" method="post" modelAttribute="attend">
            <form:input type="hidden" path="id"/>
            <p><label for="subject">Предмет: </label>
            <select id="subject" name="subject">
                <option value="0">Выберите предмет</option>
                <c:forEach items="${subjectList}" var="subject">
                    <option value="${subject.id}">${subject.title}</option>
                </c:forEach>
            </select>
            </p>
            <input type="hidden" name="studentId" value="${studentId}">
            <input type="submit" value="Назначить предмет">
        </form:form>
    </div>

</body>
</html>
