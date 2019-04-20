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
            <th><fmt:message key="number"/></th>
            <th><fmt:message key="title"/></th>
            <th><fmt:message key="distance"/></th>
            <th><fmt:message key="departure.station"/></th>
            <th><fmt:message key="arrival.station"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach items="${list}" var="route">
            <form action="controller" method="post">
                <input type="hidden" name="idRoute" value="${route.id}"/>
                <tr align="center">
                    <td>${route.number}</td>
                    <td>${route.title}</td>
                    <td>${route.distance}</td>
                    <td>${route.departure}</td>
                    <td>${route.arrival}</td>
                    <td>${route.status}</td>
                    <td>
                        <p>
                            <select name="command">
                                <option value="buses_info"><fmt:message key="show.buses"/></option>
                                <option value="buses_to_appoint"><fmt:message key="show.free.buses"/></option>
                                <option value="edit_route"><fmt:message key="edit.route"/></option>
                                <option value="delete_route"><fmt:message key="delete.route"/></option>
                            </select>
                        </p>
                        <button><fmt:message key="ok"/></button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <hr>
    <c:if test="${not empty currentPage}">
    <jsp:include page="/WEB-INF/jsp/includes/pagination.jsp">
        <jsp:param name="command" value="routes"/>
        <jsp:param name="currentPage" value="${currentPage}"/>
        <jsp:param name="lastPage" value="${lastPage}"/>
    </jsp:include>
    </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>