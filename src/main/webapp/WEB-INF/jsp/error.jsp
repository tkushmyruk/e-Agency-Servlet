 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<label><c:out value="${exception}"/></label>

<p><fmt:message key="errorMessage" bundle="${bundle}"/>
    <c:out value = " ${messageError}" />
</p>
<a href="/Agency?command=index"><p>Redirect to main page</p></a>
</body>
</html>