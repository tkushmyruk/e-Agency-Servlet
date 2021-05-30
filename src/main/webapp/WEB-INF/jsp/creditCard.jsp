<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div><fmt:message key="yourCard" bundle="${bundle}"/> ${user.getCreditCard().getCardNumber()}</div>
<div><fmt:message key="cardBalance" bundle="${bundle}"/> ${user.getCreditCard().getBalance()}</div>
<div>
    <h4><fmt:message key="replenish" bundle="${bundle}"/></h4>
</div>
<form method="post" action="Agency?command=replenish">
    <input type="hidden" name="cardNumber" value="${user.getCreditCard().getCardNumber()}">
    <input type="number" step="0.01" name="replenish">
    <input type="password" name="cardPassword">
    <button type="submit"><fmt:message key="save" bundle="${bundle}"/></button>
</form>
</body>