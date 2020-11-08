<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
    <h1>Currency exchanger</h1>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/user">Change Currency</a>
    <br/>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/admin">Admin page</a>
    <br/>
</body>
</html>
