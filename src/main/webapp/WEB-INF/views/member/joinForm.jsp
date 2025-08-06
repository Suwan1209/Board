<%--
  Created by IntelliJ IDEA.
  User: JAVA
  Date: 2025-07-25
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="/css/board.css">
<script src="/script/board.js"></script>
<head>
    <title>JOIN</title>
</head>
<body>

<form name="join" class="login-form" action="join" method="post">
    <h2>Join</h2>
    <div class="field">
        <label>User ID</label><input type="text" name="userid" value="${dto.userid}" style="flex: 3">
        <input type="button" value="중복체크" onclick="idcheck()" style="flex: 1">
        <input type="hidden" name="reid" value="${reid}">
    </div>
    <div class="field"><label>Password</label><input type="password" name="pwd"></div>
    <div class="field"><label>Retype Pw</label><input type="password" name="pwd_check"></div>
    <div class="field"><label>Name</label><input type="text" name="name" value="${dto.name}"></div>
    <div class="field"><label>Email</label><input type="text" name="email" value="${dto.email}"></div>
    <div class="field"><label>Phone</label><input type="text" name="phone" value="${dto.phone}"></div>
    <div class="login-button">
        <input type="submit" class="btn-login" value="JOIN">
        <input type="button" class="btn-login" value="BACK" onclick="location.href='/'">
    </div>
    <div class="field">${msg}</div>
</form>

</body>
</html>
