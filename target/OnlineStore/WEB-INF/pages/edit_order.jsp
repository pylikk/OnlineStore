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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="${contextPath}/resources/js/code.jquery.com/jquery-1.12.4.js"></script>
    <script src="${contextPath}/resources/js/code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet"/>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
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

    <div class="content">
        <form:form action="/admin/order/add" method="post" modelAttribute="order">
        <table class="admintable">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="phone">
                        <spring:message text="Phone"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="firstname">
                        <spring:message text="Firstname"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="firstname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastname">
                        <spring:message text="Lastname"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="lastname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address">
                        <spring:message text="address"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="address"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="order_status">
                        <spring:message text="order_status"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="order_status">
                        <option <c:if test="${order.order_status == 'new'}">selected</c:if> value="new">New</option>
                        <option <c:if test="${order.order_status == 'processed'}">selected</c:if> value="processed">Processed</option>
                        <option <c:if test="${order.order_status == 'completed'}">selected</c:if> value="completed">Completed</option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="goods">
                        <spring:message text="goods"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="goods.id">
                    <c:forEach items="${allGoods}" var="goods">
                            <option <c:if test="${order.goods.id == goods.id}">selected</c:if> value="${goods.id}">${goods.manufacturer.name} ${goods.name}</option>
                    </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="delivery_date">
                        <spring:message text="delivery date"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="delivery_date" id="datepicker"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="button" value="Save"/>
                </td>
            </tr>
        </table>
        </form:form>
    </div>
    <div class="footer">
        <p>&copy; All rights reserved 2017</p>
    </div>
</div>
</body>
</html>