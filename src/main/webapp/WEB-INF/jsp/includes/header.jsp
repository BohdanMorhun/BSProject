<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/BSProject_war_exploded/style/main.css" />
    <header class="site-header clearfix">
        <div class="body"></div>
        <h1 class="my-class"><fmt:message key="station"/></h1>
    <nav id="main-menu">
        <ul>
            <li>
                <a href="controller?command=about"><fmt:message key="about"/></a>
            </li>
            <li>
                <a href="controller?command=account"><fmt:message key="account"/></a>
            </li>
            <li>
                <a href="controller?command=contacts"><fmt:message key="contacts"/></a>
            </li>
            <li>
            <c:choose>
                <c:when test="${not empty user && not empty user.role}">
                        <a href="controller?command=logout_page"><fmt:message key="logout"/></a>
                </c:when>
                <c:otherwise>
                        <a href="controller?command=login_page"><fmt:message key="entry"/></a>
                </c:otherwise>
            </c:choose>
            </li>
        </ul>
    </nav>
</header>
<main>
    <div class="wrapper-col clearfix">

