<%--
  Created by IntelliJ IDEA.
  User: JAVA
  Date: 2025-07-25
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <script src="/script/board.js"></script>
</head>
<body>

<form class="login-form" action="login" method="post" name="login">
    <h2>Login</h2>
    <div class="field">
        <label>USER ID</label><input type="text" name="userid" value="${dto.userid}">
    </div>
    <div class="field">
        <label>PASSWORD</label><input type="password" name="pwd">
    </div>
    <div class="login-button">
        <input type="submit" class="btn-login" value="LOGIN">
        <input type="button" class="btn-login" value="JOIN" onclick="location.href='joinForm'">
    </div>
    <div style="font-weight: bold">${msg}</div>
    <div class="sns-login">
        <input type="button" class="btn naver" value="Naver">
        <input type="button" class="btn x" value="X">
        <input type="button" class="btn google" value="Google">
        <input type="button" class="btn kakao" value="Kakao" onclick="location.href='kakaostart'">
    </div>
</form>

</body>
</html>
