<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>TOUR INFO</div>
<div>
    <p><h3 align="center">${tour.getTourName()}</h3></p>
    <h4><fmt:message key="tourFor" bundle="${bundle}" />
        <c:if test="${tour.getTourType() == 'REST'}">
            <fmt:message key="rest" bundle="${bundle}" />
        </c:if>
        <c:if test="${tour.getTourType() == 'SHOPPING'}">
            <fmt:message key="shopping" bundle="${bundle}" />
        </c:if>
        <c:if test="${tour.getTourType() == 'EXCURSION'}">
            <fmt:message key="excursion" bundle="${bundle}" />
        </c:if>
        </h4>
    <c:if test="${tour.isHot()}">
    <div><fmt:message key="hotTour" bundle="${bundle}" /></div>
    </c:if>
<div><fmt:message key="only" bundle="${bundle}" /> ${tour.getPrice()} <fmt:message key="canBuy" bundle="${bundle}" /></div>
<div><fmt:message key="tourFor" bundle="${bundle}" /> ${tour.getCountOfPeople()} <fmt:message key="people" bundle="${bundle}" /></div>
<div><fmt:message key="arriveIn" bundle="${bundle}" /> ${tour.getCountry()}, ${tour.getLocality()}</div>
<div><fmt:message key="niceTime" bundle="${bundle}" /> ${tour.getHotelName()} <fmt:message key="hotelWith" bundle="${bundle}" />
    ${tour.getHotelStars().toString()}  <fmt:message key="stars" bundle="${bundle}" />
${tour.getRoomType().toString()} <fmt:message key="class" bundle="${bundle}" /></div>
<div><fmt:message key="tripStart" bundle="${bundle}" /> ${tour.getStartDate().toString()}
    <fmt:message key="tripEnd" bundle="${bundle}" />${tour.getEndDate().toString()}</div>
<div><fmt:message key="plane" bundle="${bundle}" /> ${tour.getDepartingFrom()}</div>
<c:if test="${tour.isAllInclusive()}">
<div><fmt:message key="allInclusive" bundle="${bundle}" /></div>
</c:if>
</div>
<div>
    <form action="Agency?command=buyCommand" method="post">
        <input type="hidden" name="tourName" value="${tour.getTourName()}">
        <button type="submit"><fmt:message key="buy" bundle="${bundle}" /></button>
    </form>
</div>
</body>