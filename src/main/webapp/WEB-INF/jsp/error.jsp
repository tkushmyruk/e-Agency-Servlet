 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<label class="error" for=""><c:out value="${exception}"/></label>

<p style="text-indent: 1.5em; color: red; text-align: center"><fmt:message key="error.message" bundle="${bundle}"/><c:out value = " ${messageError}" /></p>
<a href="/Agency?command=index"><p>Redirect to main page</p></a>
</body>
</html>