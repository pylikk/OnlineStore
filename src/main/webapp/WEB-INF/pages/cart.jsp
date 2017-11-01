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
        <form:form action="/makeorder" method="POST" modelAttribute="order">
        <table align="center">
            <tr>
                <td>
                    <form:label path="phone">
                        <spring:message text="Phone:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="firstname">
                        <spring:message text="Firstname:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="firstname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastname">
                        <spring:message text="Lastname:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="lastname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address">
                        <spring:message text="Address:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="address"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="order_info">
                        <spring:message text="Additional info:"/>
                    </form:label>
                </td>
                <td>
                    <form:textarea path="order_info"/>
                </td>
            </tr>
            <tr>
                <form:hidden path="goods.id" name="goods_id" value="${goods.id}"/>
                <td colspan="2"><input type="submit" class="button" value="Make an order!"></td>
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
