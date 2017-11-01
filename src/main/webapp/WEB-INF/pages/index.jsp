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
            <ul>
                <c:forEach items="${allManufacturers}" var="manufacturer">
                    <li><a href="/goodsbymanufacturer/${manufacturer.name}">${manufacturer.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="content">
            <c:if test="${goods != null}">
                <c:forEach items="${goods}" var="goods">
                    <div class="goodsdata">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <h3>${manufacturer.name} > ${goods.name}</h3>
                                </td>
                            </tr>
                            <tr>
                                <td><img width="130" src="${contextPath}/resources/images/${goods.id}.jpg"></td>
                                <td>${goods.description}</td>
                            </tr>
                            <tr>
                                <td>Price: ${goods.price} USD</td>
                                <td><a href="/cart?goods_id=${goods.id}" class="button">Buy now!</a></td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>