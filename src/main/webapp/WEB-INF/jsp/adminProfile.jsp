<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div> Your profile ${userAuth}</div>
<a href="Agency?command=userList"><fmt:message key="userList" bundle="${bundle}"/></a>
</body>