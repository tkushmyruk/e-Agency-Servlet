<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div><fmt:message key="yourProfile" bundle="${bundle}"/>${userAuth}</div>
<c:if test="${not empty user.getCreditCard()}">
<div>
    <a href="Agency?command=creditCard"><fmt:message key="yourCard" bundle="${bundle}"/></a>
</div>
</c:if>
<c:if test="${empty user.getCreditCard()}">
    <div>
        <a href="Agency?command=redirectAddCreditCard"><fmt:message key="yourCard" bundle="${bundle}"/></a>
    </div>
</c:if>
<div>
    <a href="Agency?command=boughtTourList"><fmt:message key="yourTours" bundle="${bundle}"/></a>
</div>
<h2><fmt:message key="changePassword" bundle="${bundle}"/></h2>
<form action="Agency?command=changePassword" method="POST">
    <label class="error" for=""><c:out value="${exception}"/></label>
    <div>
        <div>
            <label><fmt:message key="password" bundle="${bundle}"/></label>
            <input type="password" id="password" name="password" value="${user.getPassword()}"  />
        </div>
        <div>
            <label><fmt:message key="email" bundle="${bundle}" /></label>
            <input type="email" id="email" name="email"  value="${user.getEmail()}" />
        </div>
    </div>
    <div>
        <div class="input-block">
            <label>
                <button class="button" type="submit">
                    <fmt:message key="save" bundle="${bundle}" />
                </button>
            </label>
        </div>
    </div>
</form>
</body>