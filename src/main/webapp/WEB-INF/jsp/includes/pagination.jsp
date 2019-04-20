<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<div class="buttons-paginated">
    <ul>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="${param.command}">
                <input type="hidden" name="page" value="${1}">
                <input type="hidden" name="lastPage" value="${lastPage}">
                <button><fmt:message key="first.page"/></button>
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="${param.command}">
                <input type="hidden" name="page" value="${currentPage - 1}">
                <input type="hidden" name="lastPage" value="${lastPage}">
                <c:choose>
                    <c:when test="${currentPage > 1}">
                        <button><fmt:message key="previous.page"/></button>
                    </c:when>
                    <c:otherwise>
                        <button disabled><fmt:message key="previous.page"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="${param.command}">
                <input type="hidden" name="page" value="${currentPage}">
                <input type="hidden" name="lastPage" value="${lastPage}">
                <button>${currentPage}</button>
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="${param.command}">
                <input type="hidden" name="page" value="${currentPage + 1}">
                <input type="hidden" name="lastPage" value="${lastPage}">
                <c:choose>
                    <c:when test="${currentPage < lastPage}">
                        <button><fmt:message key="next.page"/></button>
                    </c:when>
                    <c:otherwise>
                        <button disabled><fmt:message key="next.page"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="${param.command}">
                <input type="hidden" name="page" value="${lastPage}">
                <input type="hidden" name="lastPage" value="${lastPage}">
                <button><fmt:message key="last.page"/></button>
            </form>
        </li>
    </ul>
</div>
