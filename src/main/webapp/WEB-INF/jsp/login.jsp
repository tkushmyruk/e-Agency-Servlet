<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<jsp:include page="header.jsp"/>
<form method="POST">
    <label class="registration" for=""><c:out value="${successRegistration}"/></label>
    <label class="error" for=""><c:out value="${exception}"/></label>
    <div><input name="command" value="login" type="hidden"/></div>
    <div>
        <div>
            <label><fmt:message key="login" bundle="${bundle}"/></label>
            <input type="text" id="login" name="login"  required />
        </div>
        <div>
            <label><fmt:message key="password" bundle="${bundle}" /></label>
            <input type="password" id="password" name="password" required />
        </div>
    </div>
    <div>
        <div class="input-block">
            <label>
                <button class="button" type="submit">
                    <fmt:message key="signIn" bundle="${bundle}" />
                </button>
            </label>
        </div>
    </div>
</form>
<li><a href="/Agency?command=redirectRegistration">Registration</a></li>
</body>
</html>