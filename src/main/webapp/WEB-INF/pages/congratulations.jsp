<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Goods</title>
    <link href="${contextPath}/resources/main.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="goods">
            <a href="/goods">Goods</a>
        </div>
        <div class="orders">
            <a href="/orders">Orders</a>
        </div>
    </div>

    <div class="content">

        <h3>Thank you for your order # ${order_id}!</h3>

    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>