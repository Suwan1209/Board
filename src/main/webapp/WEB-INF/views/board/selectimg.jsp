<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IMG</title>
    <script type="text/javascript">
        function selectedimage() {
            document.frm.submit();
        }
    </script>
</head>
<body>

<div id="wrap" align="center" style="width: 100%">
    <form name="frm" action="fileupload" method="post" enctype="multipart/form-data">
        <h1>파일 선택</h1>
        <input type="file" name="image" onchange="selectedimage();">
    </form>
</div>

</body>
</html>
