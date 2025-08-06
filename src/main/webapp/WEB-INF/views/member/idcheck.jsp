<%--
  Created by IntelliJ IDEA.
  User: JAVA
  Date: 2025-07-25
  Time: 오후 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Id Check</title>
    <script type="text/javascript">
        function idok(id) {
            opener.document.join.userid.value=id;
            opener.document.join.reid.value=id;
            self.close();
        }
    </script>
    <style type="text/css">
        body{display: flex; justify-content: center; flex-direction: column;}
    </style>
</head>
<body>
<!-- 재검색 창 -->
<form action="idcheck" style="margin: 20px 20px; display: flex; justify-content: center;">
    아이디 : <input type="text" name="userid" value="${userid}">
    <input type="submit" value="중복체크">
</form>
<!-- 사용 가능 여부 -->
<div style="display: flex; justify-content: center;">
<c:choose>
    <c:when test="${result == -1}">
        <script type="text/javascript">
            opener.document.join.userid.value='';
            opener.document.join.reid.value='';
        </script>
        ${userid}은(는) 이미 사용 중인 아이디입니다.
    </c:when>
    <c:otherwise>
        ${userid}은(는) 사용 가능한 아이디입니다.
        <input type="button" value="사용" onclick="idok('${userid}')">
    </c:otherwise>
</c:choose>
</div>

</body>
</html>
