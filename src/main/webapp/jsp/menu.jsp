<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
