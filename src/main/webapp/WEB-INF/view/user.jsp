<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<h1>User Page</h1>
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
<br/>
<form class="form-horizontal"
      method="post" action="${pageContext.request.contextPath}/user/currency" modelAttribute="currencyDto">
    <div class="form-group">
        <label>Input currency name you want to sell / buy
            <input type="text" name="name" minlength="3" maxlength="3">
        </label>
    </div>
    <div class="form-group">
        <label>Input amount
            <input type="text" name="amount" min="1">
        </label>
    </div>
    <div>
        <label> Choose type of operation:
            <select name="operation">
                <option>Buy</option>
                <option>Sell</option>
            </select>
        </label>
        <label> Payed currency
            <select name="currencyType">
                <option>Other</option>
                <option>UAN</option>
            </select>
        </label>
    </div>
    <br/>
    <button class="btn-success btn-lg" type="submit">Calculate</button>
</form>
<br/>
<a class="btn-info btn-lg" href="${pageContext.request.contextPath}/index">Main page</a>
<br/>
</body>
</html>
