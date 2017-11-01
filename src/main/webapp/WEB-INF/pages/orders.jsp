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
        <table align="center">
            <tr>
                <th>Id</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Name</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
                <c:forEach items="${allOrders}" var="order">
                   <tr>
                       <td>${order.id}</td>
                       <td>${order.phone}</td>
                       <td>${order.address}</td>
                       <td>${order.firstname} ${order.lastname}</td>
                       <td><a href="/edit_order/${order.id}">edit</a></td>
                       <td><a href="/remove_order/${order.id}">delete</a></td>
                   </tr>
                </c:forEach>

        </table>


    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>
