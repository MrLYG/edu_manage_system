<%--
  Created by IntelliJ IDEA.
  User: 李沅罡
  Date: 2021/3/31
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form method="post" enctype="multipart/form-data" action="<%=pageContext.getServletContext().getContextPath()%>/upload">
        <input type="file" name="upload">
        <br>
        <input type="text" name="name">
        <input type="text" name = "password">
        <input type="submit" value="文件上传">
    </form>
<%--        <form method="post" action="/lagou_edu_home/upload" enctype="multipart/form-data">--%>
<%--            <input type="file" name="upload">--%>
<%--            <br>--%>
<%--            <input type="text" name="name">--%>
<%--            <input type="text" name = "password">--%>
<%--            <input type="submit" value="文件上传">--%>


<%--        </form>--%>
</body>
</html>
