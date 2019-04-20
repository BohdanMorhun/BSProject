<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/column_left.jsp"/>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<div class="functionality">
    <c:choose>
        <c:when test="${not empty message}">
            <h2><fmt:message key="${message}"/></h2>
        </c:when>
        <c:otherwise>
            <h2><fmt:message key="welcome.message"/></h2>
            <p><fmt:message key="functionality"/></p>
            <ul>
                <li><fmt:message key="li.first"/></li>
                <li><fmt:message key="li.second"/></li>
                <li><fmt:message key="li.third"/></li>
                <li><fmt:message key="li.fourth"/></li>
                <li><fmt:message key="li.fifth"/></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>