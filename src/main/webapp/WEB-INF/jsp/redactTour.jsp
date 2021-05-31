<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>Redact Tour</div>
<h2>${tour.getTourName()}</h2>
<form action="Agency?command=redactTour&tourName=${tour.getTourName()}&redact=true" method="POST">
    <div>
        <div>
            <label><fmt:message key="price" bundle="${bundle}" /></label>
            <input type="number" step="0.01" id="price" name="price" value="${tour.getPrice()}"required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="countOfPeople" bundle="${bundle}" /></label>
            <input type="number" id="countOfPeople" name="countOfPeople" value="${tour.getCountOfPeople()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="startDate" bundle="${bundle}" /></label>
            <input type="date" id="startDate" name="startDate" value="${tour.getStartDate()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="endDate" bundle="${bundle}" /></label>
            <input type="date" id="endDate" name="endDate" value="${tour.getEndDate()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="departingFrom" bundle="${bundle}" /></label>
            <input type="text" id="departingFrom" name="departingFrom" value="${tour.getDepartingFrom()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="country" bundle="${bundle}" /></label>
            <input type="text" id="country" name="country" value="${tour.getCountry()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="locality" bundle="${bundle}" /></label>
            <input type="text" id="locality" name="locality"  value="${tour.getLocality()}" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="hotelName" bundle="${bundle}" /></label>
            <input type="text" id="hotelName" name="hotelName" value="${tour.getHotelName()}" required />
        </div>
    </div>
    <div>
        <p><b><fmt:message key="tourType" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="rest" name="tourType" value="REST"
            <c:if test="${tour.getTourType().toString() == 'REST'}"> checked</c:if>>
            <label for="rest"><fmt:message key="rest" bundle="${bundle}" /></label>
        </div>
        <div>

            <input type="radio" id="excursion" name="tourType" value="EXCURSION"
            <c:if test="${tour.getTourType().toString() == 'EXCURSION'}">checked </c:if>>

            <label for="excursion"><fmt:message key="excursion" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="shopping" name="tourType" value="SHOPPING"
            <c:if test="${tour.getTourType().toString() == 'SHOPPING'}">checked</c:if>>
            <label for="shopping"><fmt:message key="shopping" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <p><b><fmt:message key="roomType" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="standard" name="roomType" value="STANDARD"
                   <c:if test="${tour.getRoomType().toString() == 'STANDARD'}">checked </c:if>>
            <label for="standard"><fmt:message key="standard" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="luxe" name="roomType" value="LUXE"
                   <c:if test="${tour.getRoomType().toString() == 'LUXE'}">checked </c:if>>
            <label for="luxe"><fmt:message key="luxe" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="president" name="roomType" value="PRESIDENT"
                   <c:if test="${tour.getRoomType().toString() == 'PRESIDENT'}">checked </c:if>>
            <label for="president"><fmt:message key="president" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <p><b><fmt:message key="hotelStars" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="one" name="hotelStars" value="ONE"
                   <c:if test="${tour.getHotelStars().toString() == 'ONE'}">checked </c:if>>
            <label for="one"><fmt:message key="one" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="two" name="hotelStars" value="TWO"
                   <c:if test="${tour.getHotelStars().toString() == 'TWO'}">checked </c:if>>
            <label for="two"><fmt:message key="two" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="three" name="hotelStars" value="THREE"
                   <c:if test="${tour.getHotelStars().toString() == 'THREE'}">checked </c:if>>
            <label for="three"><fmt:message key="three" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="four" name="hotelStars" value="FOUR"
                   <c:if test="${tour.getHotelStars().toString() == 'FOUR'}">checked </c:if>>
            <label for="four"><fmt:message key="four" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="five" name="hotelStars" value="FIVE"
                   <c:if test="${tour.getHotelStars().toString() == 'FIVE'}">checked </c:if>>
            <label for="five"><fmt:message key="five" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <div>
            <input type="checkbox" id="allInclusive" name="isAllInclusive" value="allInclusive"
                   <c:if test="${tour.isAllInclusive()}">checked </c:if>>
            <label for="allInclusive"><fmt:message key="isAllInclusive" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <div>
            <input type="checkbox" id="hot" name="isHot" value="hot"
                   <c:if test="${tour.isHot()}">checked </c:if>>
            <label for="hot"><fmt:message key="isHot" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <div>
            <label>
                <button class="button" type="submit">
                    <fmt:message key="save" bundle="${bundle}" />
                </button>
            </label>
        </div>
    </div>
</form>
</body>