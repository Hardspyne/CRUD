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
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add new user</title>
</head>
<body>

<form method="POST" action='Controller' name="frmAddUser">
    User ID : <input type="text" readonly="readonly" name="userId"
                     value="<c:out value="${user.userId}" />"/> <br/>
    First Name : <input
        type="text" name="firstName"
        value="<c:out value="${user.firstName}" />"/> <br/>
    Last Name : <input
        type="text" name="lastName"
        value="<c:out value="${user.lastName}" />"/> <br/>
    DOB : <input
        type="text" name="birthdayDate"
        value="<fmt:formatDate pattern="dd.MM.yyyy" type="date" value="${user.birthdayDate}" />"/> <br/>
    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}" />"/> <br/> <input
        type="submit" value="Submit"/>
</form>
</body>
</html>
