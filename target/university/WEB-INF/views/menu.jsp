<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<style>
        .menu {
            position: relative;
			top: 10px;
            left: 10px;
        }
</style>

<div class="menu">
    <a href="<c:url value='/students' />">Студенты</a>
	</br></br>
    <a href="<c:url value='/subjects' />">Предметы</a>
</div>
</body>
</html>
