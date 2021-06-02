<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form action="Agency?command=addCreditCard" method="POST">
    <label class="error" for=""><c:out value="${exception}"/></label>
    <div><input name="command" value="registration" type="hidden"/></div>
    <div>
        <div>
            <label><fmt:message key="cardNumber" bundle="${bundle}" /></label>
            <input type="text" id="cardNumber" name="cardNumber" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="cardPassword" bundle="${bundle}" /></label>
            <input type="password" id="cardPassword" name="cardPassword" required />
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