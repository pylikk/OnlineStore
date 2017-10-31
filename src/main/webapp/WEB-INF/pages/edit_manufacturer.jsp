<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Manufacturer</title>
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
        <form:form action="/manufacturer/add" method="post" modelAttribute="manufacturer">
            <table align="center">
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
                        <form:label path="name">
                            <spring:message text="name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="country">
                            <spring:message text="country"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="country"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" class="button" value="Save">
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
