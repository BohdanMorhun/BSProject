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
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="birth"/></th>
            <th><fmt:message key="license.test"/></th>
            <th><fmt:message key="salary"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach items="${list}" var="driver">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="appoint_driver"/>
                <input type="hidden" name="idDriver" value="${driver.id}"/>
                <input type="hidden" name="idBus" value="${param.idBus}"/>
                <tr align="center">
                    <td>${driver.name}</td>
                    <td>${driver.surname}</td>
                    <td>${driver.birth}</td>
                    <td>${driver.licenseTest}</td>
                    <td>${driver.salary}</td>
                    <td>${driver.status}</td>
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