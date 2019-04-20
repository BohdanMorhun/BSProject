<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/column_left.jsp"/>

<div class="col-center">
    <p><fmt:message key="driver.info"/></p>
    <table class="table-class" border="3">
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="birth"/></th>
            <th><fmt:message key="license.test"/></th>
            <th><fmt:message key="salary"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="login"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <form action="controller" method="post">
            <input type="hidden" name="idDriver" value="${driver.id}"/>
            <input type="hidden" name="status" value="${driver.status}"/>
            <tr align="center">
                <td>${driver.name}</td>
                <td>${driver.surname}</td>
                <td>${driver.birth}</td>
                <td>${driver.licenseTest}</td>
                <td>${driver.salary}</td>
                <td>${driver.status}</td>
                <td>${driver.user.login}</td>
                <td>
                    <p>
                        <select name="command">
                            <option value="edit_driver_account"><fmt:message key="edit.profile"/></option>
                            <option value="accept_schedule"><fmt:message key="accept.schedule"/></option>
                        </select>
                    </p>
                    <button><fmt:message key="ok"/></button>
                </td>
            </tr>
        </form>
    </table>
    <hr>
    <c:if test="${driver.bus.id != 0}">
        <p><fmt:message key="bus.info"/> </p>
        <table class="table-class" border="3">
            <tr>
                <th><fmt:message key="plate"/></th>
                <th><fmt:message key="model"/></th>
                <th><fmt:message key="mileage"/></th>
                <th><fmt:message key="inspection"/></th>
                <th><fmt:message key="consumption"/></th>
                <th><fmt:message key="release"/></th>
                <th><fmt:message key="status"/></th>
                <th><fmt:message key="seats"/></th>
            </tr>
            <tr align="center">
                <td>${driver.bus.plate}</td>
                <td>${driver.bus.model}</td>
                <td>${driver.bus.mileage}</td>
                <td>${driver.bus.inspection}</td>
                <td>${driver.bus.consumption}</td>
                <td>${driver.bus.release}</td>
                <td>${driver.bus.status}</td>
                <td>${driver.bus.seats}</td>
            </tr>
        </table>
        <hr>
    </c:if>
    <c:if test="${driver.bus.status eq 'work'}">
        <p><fmt:message key="route.info"/> </p>
        <table class="table-class" border="3">
            <tr>
                <th><fmt:message key="number"/></th>
                <th><fmt:message key="title"/></th>
                <th><fmt:message key="distance"/></th>
                <th><fmt:message key="departure.station"/></th>
                <th><fmt:message key="arrival.station"/></th>
                <th><fmt:message key="status"/></th>
            </tr>
            <tr align="center">
                <td>${driver.bus.route.number}</td>
                <td>${driver.bus.route.title}</td>
                <td>${driver.bus.route.distance}</td>
                <td>${driver.bus.route.departure}</td>
                <td>${driver.bus.route.arrival}</td>
                <td>${driver.bus.route.status}</td>
            </tr>
        </table>
        <hr>
    </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>