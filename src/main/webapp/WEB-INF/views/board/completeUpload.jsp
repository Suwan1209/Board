<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete</title>
</head>
<body>
<script type="text/javascript">
    opener.document.getElementById('previewimg').setAttribute('src', '${savefilename}');
    opener.document.getElementById('previewimg').style.display='inline'; // 이미지 미리보기

    opener.document.insertBoard.image.value='${image}';
    opener.document.insertBoard.savefilename.value='${savefilename}';
    self.close();
</script>
</body>
</html>
