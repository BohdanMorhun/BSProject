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
            <input type="hidden" name="command" value="save_bus">
            <input type="hidden" name="idBus" value="${bus.id}">
            <input type="hidden" name="idSchedule" value="${bus.schedule.id}">
            <div class="field">
                <label><fmt:message key="plate"/></label>
                <input type="text" name="plate" value="${bus.plate}" placeholder="<fmt:message key="placeholder.bus.plate"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="model"/></label>
                <input type="text" name="model" value="${bus.model}" placeholder="<fmt:message key="placeholder.bus.model"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="mileage"/></label>
                <input type="text" name="mileage" value="${bus.mileage}" placeholder="<fmt:message key="placeholder.bus.mileage"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="inspection"/></label>
                <input type="date" name="inspection" value="${bus.inspection}"/>
            </div>
            <div class="field">
                <label><fmt:message key="consumption"/></label>
                <input type="text" name="consumption" value="${bus.consumption}" placeholder="<fmt:message key="placeholder.bus.consumption"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="release"/></label>
                <input type="date" name="release" value="${bus.release}"/>
            </div>
            <div class="field">
                <label><fmt:message key="seats"/></label>
                <input type="text" name="seats" value="${bus.seats}" placeholder="<fmt:message key="placeholder.bus.seats"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="departure.time"/></label>
                <input type="time" name="departure" value="${bus.schedule.departure}"/>
            </div>
            <div class="field">
                <label><fmt:message key="arrival.time"/></label>
                <input type="time" name="arrival" value="${bus.schedule.arrival}"/>
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