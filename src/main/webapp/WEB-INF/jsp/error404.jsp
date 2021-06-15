<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<label class="error"><c:out value="${exception}"/></label>

<p class="error"><fmt:message key="errorMessage404" bundle="${bundle}"/></p>
<a href="/Agency?command=index">Redirect to main page</a>
</body>
</html>