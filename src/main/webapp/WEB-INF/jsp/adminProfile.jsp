<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div><fmt:message key="yourProfile" bundle="${bundle}"/> ${userAuth}</div>
<a href="Agency?command=userList&pageNumber=1"><fmt:message key="userList" bundle="${bundle}"/></a>
</body>