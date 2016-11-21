<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Action for student</title>
</head>
<body>
<form action="university" method="get">
    <input type="hidden" name="url" value="studentUpdate">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Редактировать">
</form>
<form action="university" method="get">
    <input type="hidden" name="url" value="studentDelete">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Отчислить">
</form>
<form action="university" method="get">
    <input type="hidden" name="url" value="attendAdd">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Назначить предмет">
</form>
</body>
</html>
