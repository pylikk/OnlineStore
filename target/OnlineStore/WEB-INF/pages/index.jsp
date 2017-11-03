<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Edit Order</title>
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="menufix"><a href="/">Main</a></div>
        <div class="menufix"><a href="/">Goods</a></div>
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
                                <td width="130px"><img width="130" src="${contextPath}/resources/images/${goods.id}.jpg"></td>
                                <td width="600">${goods.description}</td>
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