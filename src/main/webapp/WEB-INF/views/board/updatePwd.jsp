<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pwd</title>
</head>
<body>

<div id="wrap" align="center" style="width: 100%">
    <form name="frm" action="updatePwd" method="post">
        <input type="hidden" name="num" value="${num}">
        <h1>비밀번호 수정</h1>
        <div class="field">
            <label>기존 비밀번호</label><input type="password" name="oldPwd">
        </div>
        <div class="field">
            <label>새 비밀번호</label><input type="password" name="newPwd">
        </div>
        <div class="field">
            <label>비밀번호 확인</label><input type="password" name="confirmPwd">
        </div>
        <div class="field">
            <input type="submit" value="수정">
        </div>
        <div class="field">${msg}</div>

    </form>
</div>

</body>
</html>
