<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/column_left.jsp"/>
<div class="col-center">
    <table class="table-class" border="3">
        <tr>
            <th><fmt:message key="plate"/></th>
            <th><fmt:message key="model"/></th>
            <th><fmt:message key="departure.time"/></th>
            <th><fmt:message key="arrival.time"/></th>
            <th><fmt:message key="seats"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach items="${list}" var="bus">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="appoint_bus">
                <input type="hidden" name="idBus" value="${bus.id}">
                <input type="hidden" name="idRoute" value="${param.idRoute}">
                <tr align="center">
                    <td>${bus.plate}</td>
                    <td>${bus.model}</td>
                    <td>${bus.schedule.departure}</td>
                    <td>${bus.schedule.arrival}</td>
                    <td>${bus.seats}</td>
                    <td>
                        <button><fmt:message key="appoint"/></button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>