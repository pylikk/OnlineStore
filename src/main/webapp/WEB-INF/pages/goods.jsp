<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>
</head>
<body>
<h1>Goods & Manufacturers</h1>
<div class="container">
    <div class="header">
        <div>
            <h2><a href="/goods">Goods</a></h2>
        </div>
        <div>
            <h2><a href="/orders">Orders</a></h2>
        </div>
    </div>

    <div class="main">
        <div class="left-menu">
            <h3 align="center">Manufacturers</h3><br>
            <c:forEach items="${allManufacturers}" var="manufacturer">
                <p>${manufacturer.id}. <a href="/goods/${manufacturer.name}">${manufacturer.name}</a>
                    (${manufacturer.country})</p>
            </c:forEach>
            <br>
            <form action="/manufacturer/add" method="post">
                <label><input type="text" name="name">name</label><br>
                <label><input type="text" name="country">country</label><br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" name="save">
            </form>
        </div>
        <div class="content">
            <h3 align="center">Goods</h3><br>

            <c:if test="${manuf != null}">
                <h3>${manuf.name}</h3>
                <c:forEach items="${listGoods}" var="goods">
                    <p>${goods.id}. ${goods.name} | ${goods.price} USD</p>
                </c:forEach>
                <p></p>
            </c:if>
        </div>
    </div>

</div>
</body>
</html>
