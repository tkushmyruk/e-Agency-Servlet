<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="custom" %>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>TOUR INFO</div>
<div id="tour-info">
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
    <c:if test="${tour.getTourStatus().toString() == 'REGISTERED'}">
<div><fmt:message key="only" bundle="${bundle}" /> ${tour.getPrice()} <fmt:message key="canBuy" bundle="${bundle}" /></div>
    </c:if>
<div><fmt:message key="tourFor" bundle="${bundle}" /> ${tour.getCountOfPeople()} <fmt:message key="people" bundle="${bundle}" /></div>
<div><fmt:message key="arriveIn" bundle="${bundle}" /> ${tour.getCountry()}, ${tour.getLocality()}</div>
<div><fmt:message key="niceTime" bundle="${bundle}" /> ${tour.getHotelName()} <fmt:message key="hotelWith" bundle="${bundle}" />
    ${tour.getHotelStars().toString()}  <fmt:message key="stars" bundle="${bundle}" />
${tour.getRoomType().toString()} <fmt:message key="class" bundle="${bundle}" /></div>
<div><fmt:message key="tripStart" bundle="${bundle}" /> <ct:formatDate date="${tour.getStartDate().toString()}" pattern="EEE, MMM d, ''yy"/>
    <fmt:message key="tripEnd" bundle="${bundle}" /> <ct:formatDate date="${tour.getStartDate()}" pattern="EEE, MMM d, ''yy"/></div>
<div><fmt:message key="plane" bundle="${bundle}" /> ${tour.getDepartingFrom()}</div>
<c:if test="${tour.isAllInclusive()}">
<div><fmt:message key="allInclusive" bundle="${bundle}" /></div>
</c:if>
</div>
<c:if test="${tour.getTourStatus().toString() == 'BOUGHT'}">
    <div>
        <form action="Agency?command=returnTour" method="post">
            <input type="hidden" name="tourName" value="${tour.getTourName()}">
            <button class="button" type="submit"><fmt:message key="return" bundle="${bundle}" /></button>
        </form>
    </div>
</c:if>
<c:if test="${tour.getTourStatus().toString() == 'REGISTERED'}">
<div>
    <form action="Agency?command=buyCommand" method="post">
        <input type="hidden" name="tourName" value="${tour.getTourName()}">
        <button type="submit"><fmt:message key="buy" bundle="${bundle}" /></button>
    </form>
</div>
    <label class="error"><c:out value="${exception}"/></label>
</c:if>
</body>