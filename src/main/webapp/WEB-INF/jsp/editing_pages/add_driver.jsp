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
            <input type="hidden" name="command" value="save_driver">
            <input type="hidden" name="idDriver" value="${driver.id}">
            <input type="hidden" name="idUser" value="${driver.user.id}">
            <div class="field">
                <label><fmt:message key="name"/></label>
                <input type="text" name="name" value="${driver.name}" placeholder="<fmt:message key="placeholder.name"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="surname"/></label>
                <input type="text" name="surname" value="${driver.surname}" placeholder="<fmt:message key="placeholder.surname"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="birth"/></label>
                <input type="date" name="birth" value="${driver.birth}"/>
            </div>
            <div class="field">
                <label><fmt:message key="license.test"/></label>
                <input type="date" name="test" value="${driver.licenseTest}"/>
            </div>
            <div class="field">
                <label><fmt:message key="salary"/></label>
                <input type="text" name="salary" value="${driver.salary}" placeholder="<fmt:message key="placeholder.salary"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="login"/></label>
                <input type="text" name="login" value="${driver.user.login}" placeholder="<fmt:message key="placeholder.login"/>"/>
            </div>
            <div class="field">
                <label><fmt:message key="password"/></label>
                <input type="password" name="password" value="${driver.user.password}" placeholder="<fmt:message key="placeholder.password"/>"/>
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