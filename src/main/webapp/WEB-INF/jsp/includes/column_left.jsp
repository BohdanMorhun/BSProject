<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<section class="col-left">
    <ul>
        <li><a href="controller?command=routes"><fmt:message key="routes"/></a></li>
        <li><a href="controller?command=buses"><fmt:message key="buses"/></a></li>
        <li><a href="controller?command=drivers"><fmt:message key="drivers"/></a></li>
        <li><a href="controller?command=add_route"><fmt:message key="add.route"/></a></li>
        <li><a href="controller?command=add_bus"><fmt:message key="add.bus"/></a></li>
        <li><a href="controller?command=add_driver"><fmt:message key="add.driver"/></a></li>

    </ul>
</section>