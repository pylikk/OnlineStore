<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Edit Goods</title>
    <link href="${contextPath}/resources/main.css" rel="stylesheet"/>
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

    <div class="content">
        <form:form enctype="multipart/form-data" action="/admin/goods/add/?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="goods">
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
                        <form:label path="manufacturer">
                            <spring:message text="manufacturer"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select path="manufacturer.id">
                            <c:forEach items="${allManufacturers}" var="manufacturer">
                                <option <c:if test="${manufacturer.id == goods.manufacturer.id}">selected</c:if> value="${manufacturer.id}">${manufacturer.name}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="price">
                            <spring:message text="price"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="price"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="description">
                            <spring:message text="description"/>
                        </form:label>
                    </td>
                    <td>
                        <form:textarea path="description"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="quantity">
                            <spring:message text="quantity"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="quantity"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="image">
                            <spring:message text="image"/>
                        </form:label>
                    </td>
                    <td>
                        <input type="file" name="file"><br>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="goods_id" value="${goods.id}"/>
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
