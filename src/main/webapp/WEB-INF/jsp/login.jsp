<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/column_left.jsp"/>
<div class="add-center">
    <fieldset class="login-border">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login">
            <div class="field">
                <label><fmt:message key="login"/></label>
                <input type="text" name="login" placeholder="<fmt:message key="enter.login"/>" value="${user.login}"
                       required/>
            </div>
            <div class="field">
                <label><fmt:message key="password"/></label>
                <input type="password" name="password" placeholder="<fmt:message key="enter.password"/>" required/>
            </div>
            <br>
            <hr>
            <c:if test="${not empty message}">
                <fmt:message key="${message}"/>
                <br>
            </c:if>
            <button><fmt:message key="entry"/></button>
        </form>
    </fieldset>
</div>
<jsp:include page="/WEB-INF/jsp/includes/column_right.jsp"/>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>