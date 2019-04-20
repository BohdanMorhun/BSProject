<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
</div>
</main>
<footer id="footer-site">
    <ul>
        <li>
            <a href=""><fmt:message key="vk"/></a>
        </li>
        <li>
            <a href=""><fmt:message key="facebook"/></a>
        </li>
        <li>
            <a href=""><fmt:message key="gmail"/></a>
        </li>
    </ul>
    <div>
        <span><fmt:message key="created.by"/></span>
    </div>
</footer>
</body>
</html>