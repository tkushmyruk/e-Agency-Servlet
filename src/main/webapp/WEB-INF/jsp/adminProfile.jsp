<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div> Y<fmt:message key="yourProfile" bundle="${bundle}"/> ${userAuth}</div>
<a href="Agency?command=userList"><fmt:message key="userList" bundle="${bundle}"/></a>
</body>