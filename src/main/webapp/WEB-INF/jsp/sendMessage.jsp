<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form action="">
    <div>
        <div>
            <label class="login-label"><fmt:message key="login" bundle="${bundle}" /></label>
            <input class="login-input" type="text" id="topic" name="topic"/>
        </div>
    </div>
    <div>
        <div>
            <label class="login-label"><fmt:message key="login" bundle="${bundle}" /></label>
            <input class="login-input" type="text" id="tag" name="tag"/>
        </div>
    </div>
    <div>
        <div>
            <label class="login-label"><fmt:message key="login" bundle="${bundle}" /></label>
            <input class="login-input" type="text" id="text" name="text"/>
        </div>
    </div>
    <div>
        <div>
            <label class="login-label"><fmt:message key="login" bundle="${bundle}" /></label>
            <input class="login-input" type="text" id="receiver" name="receiver"/>
        </div>
    </div>
</form>
</body>