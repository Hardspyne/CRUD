<%--
  Created by IntelliJ IDEA.
  User: dwe
  Date: 02.04.2019
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Users</title>
</head>
<body>
<table border=1 style="border-collapse: collapse;" cellpadding="5px" >
    <thead>
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Birthday Date</th>
        <th>Email</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.userId}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${user.birthdayDate}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><a href="Controller?action=edit&userId=<c:out value="${user.userId}"/>">Update</a></td>
            <td><a href="Controller?action=delete&userId=<c:out value="${user.userId}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="Controller?action=insert">Add User</a></p>
</body>
</html>

