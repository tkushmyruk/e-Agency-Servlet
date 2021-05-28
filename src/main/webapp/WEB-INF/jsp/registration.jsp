<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form action="Agency" method="POST">
    <div><input name="command" value="registration" type="hidden"/></div>
    <div>
        <div>
            <label><fmt:message key="login" bundle="${bundle}" /></label>
            <input type="text" id="username" name="username" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="password" bundle="${bundle}" /></label>
            <input type="password" id="password" name="password" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="email" bundle="${bundle}" /></label>
            <input type="text" id="email" name="email" required />
        </div>
    </div>
    <div>
        <div>
            <label>
                <button class="button" type="submit">
                    <fmt:message key="registration" bundle="${bundle}" />
                </button>
            </label>
        </div>
    </div>

</form>
</body>