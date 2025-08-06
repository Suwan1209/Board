<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <script src="/script/board.js"></script>
</head>
<body>

<form name="join" class="login-form" action="update" method="post">
  <h2>Update</h2>
  <input type="hidden" name="provider" value="${dto.provider}">
  <div class="field">
    <label>User ID</label><input type="text" name="userid" value="${dto.userid}" style="flex: 3" readonly>
  </div>
  <c:choose>
    <c:when test="${empty dto.provider}">
      <div class="field"><label>Password</label><input type="password" name="pwd"></div>
      <div class="field"><label>Retype Pw</label><input type="password" name="pwd_check"></div>
    </c:when>
    <c:otherwise>
      <div class="field"><label>Password</label><input type="password" name="pwd" readonly></div>
      <div class="field"><label>Retype Pw</label><input type="password" name="pwd_check" readonly></div>
    </c:otherwise>
  </c:choose>

  <div class="field"><label>Name</label><input type="text" name="name" value="${dto.name}"></div>
  <div class="field"><label>Email</label><input type="text" name="email" value="${dto.email}"></div>
  <div class="field"><label>Phone</label><input type="text" name="phone" value="${dto.phone}"></div>
  <div class="login-button">
    <input type="submit" class="btn-login" value="UPDATE">
    <input type="button" class="btn-login" value="BACK" onclick="location.href='/'">
  </div>
  <div class="field">${msg}</div>
</form>

</body>
</html>
