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
            <th><fmt:message key="mileage"/></th>
            <th><fmt:message key="inspection"/></th>
            <th><fmt:message key="consumption"/></th>
            <th><fmt:message key="release"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="seats"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach items="${list}" var="bus">
            <form action="controller" method="post">
                <input type="hidden" name="idBus" value="${bus.id}">
                <input type="hidden" name="idSchedule" value="${bus.schedule.id}">
                <tr align="center">
                    <td>${bus.plate}</td>
                    <td>${bus.model}</td>
                    <td>${bus.mileage}</td>
                    <td>${bus.inspection}</td>
                    <td>${bus.consumption}</td>
                    <td>${bus.release}</td>
                    <td>${bus.status}</td>
                    <td>${bus.seats}</td>
                    <td>
                        <p>
                            <select name="command">
                                <option value="driver_info"><fmt:message key="driver"/></option>
                                <option value="cancel_driver"><fmt:message key="cancel.driver"/></option>
                                <option value="free_drivers"><fmt:message key="free.drivers"/></option>
                                <option value="edit_bus"><fmt:message key="edit.bus"/></option>
                                <option value="delete_bus"><fmt:message key="delete.bus"/></option>
                            </select>
                        </p>
                        <button><fmt:message key="ok"/></button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <hr>
    <jsp:include page="/WEB-INF/jsp/includes/pagination.jsp">
        <jsp:param name="command" value="buses"/>
        <jsp:param name="currentPage" value="${currentPage}"/>
        <jsp:param name="lastPage" value="${lastPage}"/>
    </jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>