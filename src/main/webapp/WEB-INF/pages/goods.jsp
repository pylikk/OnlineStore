<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>
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

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div class="logout">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </div>
        </c:if>

    </div>

    <div class="main">
        <div class="left-menu">
            <h3 align="center">Manufacturers</h3><br>
            <c:if test="${!empty allManufacturers}">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${allManufacturers}" var="manufacturer">
                        <tr>
                            <td>${manufacturer.id}</td>
                            <td><a href="/goods/${manufacturer.name}">${manufacturer.name}</a></td>
                            <td>${manufacturer.country}</td>
                            <td><a href="/edit_manufacturer/${manufacturer.id}">edit</a></td>
                            <td><a href="/remove_manufacturer/${manufacturer.id}">delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <br>
            <a href="/edit_manufacturer" class="button">New Manufacturer</a>
        </div>
        <div class="content">
            <h3 align="center">Goods</h3>

            <c:if test="${manuf != null}">
                <div class="goodsdata">
                    <h3 align="center">${manuf.name}</h3>
                    <table align="center">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${listGoods}" var="goods">
                            <tr>
                                <td>${goods.id}</td>
                                <td>${goods.name}</td>
                                <td>${goods.price}</td>
                                <td>${goods.quantity}</td>
                                <td><a href="/edit_goods/${goods.id}">edit</a></td>
                                <td><a href="/remove_goods/${goods.id}">delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>

                </div>
            </c:if>
            <a href="/edit_goods" class="button">New Goods</a>
        </div>
    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>
