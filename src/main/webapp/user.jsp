<%--
  Created by IntelliJ IDEA.
  User: dwe
  Date: 02.04.2019
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new user</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<form method="POST" action='Controller' name="frmAddUser">
    <div class="main">
        <div class="field">
            <label for="UserId">UserId</label>
            <input type="text" id="UserId" readonly="readonly" name="userId"
                   value="<c:out value="${user.userId}" />"/>
        </div>
        <div class="field">
            <label for="n">firstName</label>
            <input type="text" id="n" name="firstName"
                   value="<c:out value="${user.firstName}" />"/>
        </div>
        <div class="field">
            <label for="ln">LastName</label>
            <input type="text" id="ln" name="lastName"
                   value="<c:out value="${user.lastName}" />"/>
        </div>
        <div class="field">
            <label for="bd">birthdayDate</label>
            <input type="text" id="bd" name="birthdayDate"
                   value="<fmt:formatDate pattern="dd.MM.yyyy" type="date" value="${user.birthdayDate}" />"/>
        </div>
        <div class="field">
            <label for="m">Email</label>
            <input type="text" id="m" name="email"
                   value="<c:out value="${user.email}" />"/>
        </div>
        <div class="field">
            <input type="submit" value="Submit"/>
        </div>
    </div>
</form>
</body>
</html>
