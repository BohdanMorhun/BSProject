<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/column_left.jsp"/>
<div class="add-center">
    <fieldset class="login-border">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="save_route">
            <input type="hidden" name="idRoute" value="${route.id}">
            <div class="field">
                <label><fmt:message key="number"/></label>
                <input type="text" name="number" value="${route.number}" placeholder="<fmt:message key="placeholder.route.number"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="title"/></label>
                <input type="text" name="title" value="${route.title}" placeholder="<fmt:message key="placeholder.route.title"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="distance"/></label>
                <input type="text" name="distance" value="${route.distance}" placeholder="<fmt:message key="placeholder.route.distance"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="departure.station"/></label>
                <input type="text" name="departure" value="${route.departure}" placeholder="<fmt:message key="placeholder.route.city"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="arrival.station"/></label>
                <input type="text" name="arrival" value="${route.arrival}" placeholder="<fmt:message key="placeholder.route.city"/>"/>
            </div>
            <br>
            <hr>
            <c:if test="${not empty message}">
                <fmt:message key="${message}"/>
                <br>
            </c:if>
            <button><fmt:message key="save"/></button>
        </form>
    </fieldset>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>