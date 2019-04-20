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
            <th><fmt:message key="login"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach items="${list}" var="driver">
            <form action="controller" method="post">
                <input type="hidden" name="idDriver" value="${driver.id}"/>
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
                                <option value="edit_driver"><fmt:message key="edit.driver"/></option>
                                <option value="delete_driver"><fmt:message key="delete.driver"/></option>
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
        <jsp:param name="command" value="drivers"/>
        <jsp:param name="currentPage" value="${currentPage}"/>
        <jsp:param name="lastPage" value="${lastPage}"/>
    </jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>