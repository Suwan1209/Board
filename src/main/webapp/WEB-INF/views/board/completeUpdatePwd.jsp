<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comp</title>
</head>
<body>
<script type="text/javascript">
    alert('게시물의 비밀번호가 변경되었습니다.');
    opener.document.location.href='boardViewWithoutCnt?num=${num}'
    self.close();
</script>
</body>
</html>
