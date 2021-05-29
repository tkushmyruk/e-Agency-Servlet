<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agency</title>
</head>
<body>
<header>
    <%@include file="/WEB-INF/jsp/i18n.jsp" %>
    <form action="Agency" method="POST">
        <div><input name="command" value="setLang" type="hidden"/></div>
        <div><input name="page" value="${page}" type="hidden"/></div>
        <input type="radio" id="lang" name="lang" value="en" checked="checked"/>EN
        <input type="radio" id="lang" name="lang" value="ua"/>UA
        <button type="submit">
            <fmt:message key="changeLang" bundle="${bundle}"/>
        </button>
    </form>
    <c:if test="${empty role}">
        <li><a href="/Agency?command=redirectLogin"><fmt:message key="signIn" bundle="${bundle}" /></a></li>
        <li><a href="/Agency?command=redirectRegistration"><fmt:message key="registration" bundle="${bundle}" /></a></li>
    </c:if>
    <c:if test="${not empty role}">
        <c:if test = "${not empty admin}">
            <li><a href="/Agency?command=profileAdmin"><fmt:message key="profile" bundle="${bundle}" /></a></li>
        </c:if>
        <c:if test = "${empty admin}">
            <li><a href="/Agency?command=profile"><fmt:message key="profile" bundle="${bundle}" /></a></li>
        </c:if>
        <li><a href="/Agency?command=logout"><fmt:message key="logout" bundle="${bundle}" /></a></li>
        <li><a href="/Agency?command=tourList"><fmt:message key="tours" bundle="${bundle}" /></a></li>
    </c:if>
    <br/>
    <br/>
</header>
</body>