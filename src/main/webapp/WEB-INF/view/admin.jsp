<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<h1>Admin Page</h1>
<table class="table table-bordered">
    <tr>
        <th>Currency</th>
        <th>Exchange rate sell</th>
        <th>Exchange rate buy</th>
    </tr>
    <c:forEach var="currency" items="${currencies}">
        <tr>
            <td>
                <c:out value="${currency.name}"/>
            </td>
            <td>
                <c:out value="${currency.valueSell}"/>
            </td>
            <td>
                <c:out value="${currency.valueBuy}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<h3 style="color: green">${message}</h3>
<br/>
<h3 style="color: red">${invalidMessage}</h3>
<h4>You need to feel all fields, if you dont want to change sell/buy value just rewrite it</h4>
<form class="form-horizontal"
      method="post" action="${pageContext.request.contextPath}/admin/set" modelAttribute="currency">
    <div class="form-group">
        <label>Input currency name
            <input type="text" name="name" minlength="3" maxlength="3">
        </label>
    </div>
    <div class="form-group">
        <label>Input currency rate for sell
            <input type="text" name="valueSell" min="1">
        </label>
    </div>
    <div class="form-group">
        <label>Input currency rate for buy
            <input type="text" name="valueBuy" min="1">
        </label>
    </div>
    <br/>
    <button class="btn-success btn-lg" type="submit">Set currency rate</button>
</form>
<div class="btn-group-vertical">
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/admin/update">Update currency rate</a>
    <br/>
    <a class="btn-danger btn-lg" href="${pageContext.request.contextPath}/logout">Logout</a>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/index">Main page</a>
</div>
</body>
</html>
