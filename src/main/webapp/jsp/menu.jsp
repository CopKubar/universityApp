<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<div align="center">
    <form action="university" method="get">
        <input type="hidden" name="url" value="students">
        <input type="submit" value="Студенты">
    </form>
    <form action="university" method="get">
        <input type="hidden" name="url" value="subjects">
        <input type="submit" value="Предметы">
    </form>
</div>
</body>
</html>
