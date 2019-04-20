<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<aside class="col-sidebar">
    <div>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="language">
            <p><fmt:message key="language.choice"/></p>
            <select name="lang">
                <c:choose>
                    <c:when test="${lang eq 'en_US'}">
                        <option value="en" selected><fmt:message key="en"/></option>
                        <option value="ua"><fmt:message key="ua"/></option>
                        <option value="ru"><fmt:message key="ru"/></option>
                    </c:when>
                    <c:when test="${lang eq 'uk_UA'}">
                        <option value="en"><fmt:message key="en"/></option>
                        <option value="ua" selected><fmt:message key="ua"/></option>
                        <option value="ru"><fmt:message key="ru"/></option>
                    </c:when>
                    <c:otherwise>
                        <option value="en"><fmt:message key="en"/></option>
                        <option value="ua"><fmt:message key="ua"/></option>
                        <option value="ru" selected><fmt:message key="ru"/></option>
                    </c:otherwise>
                </c:choose>
            </select>
            <button><fmt:message key="change"/></button>
        </form>
    </div>
    <div>
        <p><fmt:message key="search.tip"/></p>
        <form action="controller" class="search-menu">
            <input type="hidden" name="command" value="search">
            <input type="text" name="departure" placeholder="<fmt:message key="departure.station"/>"/>
            <input type="text" name="arrival" placeholder="<fmt:message key="arrival.station"/>"/>
            <button><fmt:message key="search"/></button>
        </form>
    </div>
</aside>