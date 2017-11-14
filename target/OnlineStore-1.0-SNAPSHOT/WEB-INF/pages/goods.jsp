<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Edit Goods</title>
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="menufix"><a href="/">Main</a></div>
        <div class="menufix"><a href="/admin/goods">Goods</a></div>
        <div class="menufix"><a href="/admin/orders">Orders</a></div>
        <form class="search" action="/search?${_csrf.parameterName}=${_csrf.token}" method="POST">
            Search: <input type="text" size="12" name="search" id="search"><input type="submit" class="button" value="Go"></form>
        <div class="hello">Hello,
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                guest!
            </c:if>
        </div>
    </div>

    <div class="main">
        <div class="left-menu">
            <h3 align="center">Manufacturers</h3><br>
            <a href="/admin/edit_manufacturer" class="button">New Manufacturer</a>
            <h3></h3>
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
                            <td><a href="/admin/goods/${manufacturer.name}">${manufacturer.name}</a></td>
                            <td>${manufacturer.country}</td>
                            <td><a href="/admin/edit_manufacturer/${manufacturer.id}">edit</a></td>
                            <td><a href="/admin/remove_manufacturer/${manufacturer.id}">delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <br>

        </div>
        <div class="content">


            <c:if test="${manuf != null}">
                <div class="goodsdata">
                    <h3 align="center">Goods</h3>
                    <a href="/admin/edit_goods" class="button">New Goods</a>
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
                                <td><a href="/admin/edit_goods/${goods.id}">edit</a></td>
                                <td><a href="/admin/remove_goods/${goods.id}">delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>

                </div>
            </c:if>

        </div>
    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>
