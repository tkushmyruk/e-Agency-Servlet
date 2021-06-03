<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form action="Agency?command=addTour" method="POST">
    <label><c:out value="${exception}"/></label>
    <div><input name="command" value="registration" type="hidden"/></div>
    <div>
        <div>
            <label><fmt:message key="tourName" bundle="${bundle}" /></label>
            <input type="text" id="tourName" name="tourName" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="price" bundle="${bundle}" /></label>
            <input type="number" step="0.01" id="price" name="price" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="countOfPeople" bundle="${bundle}" /></label>
            <input type="number" id="countOfPeople" name="countOfPeople" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="startDate" bundle="${bundle}" /></label>
            <input type="date" id="startDate" name="startDate" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="endDate" bundle="${bundle}" /></label>
            <input type="date" id="endDate" name="endDate" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="departingFrom" bundle="${bundle}" /></label>
            <input type="text" id="departingFrom" name="departingFrom" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="country" bundle="${bundle}" /></label>
            <input type="text" id="country" name="country" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="locality" bundle="${bundle}" /></label>
            <input type="text" id="locality" name="locality" required />
        </div>
    </div>
    <div>
        <div>
            <label><fmt:message key="hotelName" bundle="${bundle}" /></label>
            <input type="text" id="hotelName" name="hotelName" required />
        </div>
    </div>
    <div>
        <p><b><fmt:message key="tourType" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="rest" name="tourType" value="REST"
                   checked>
            <label for="rest"><fmt:message key="rest" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="excursion" name="tourType" value="EXCURSION">
            <label for="excursion"><fmt:message key="excursion" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="shopping" name="tourType" value="SHOPPING">
            <label for="shopping"><fmt:message key="shopping" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <p><b><fmt:message key="roomType" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="standart" name="roomType" value="STANDART"
                   checked>
            <label for="standart"><fmt:message key="standard" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="luxe" name="roomType" value="LUXE">
            <label for="luxe"><fmt:message key="luxe" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="president" name="roomType" value="PRESIDENT">
            <label for="president"><fmt:message key="president" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <p><b><fmt:message key="hotelStars" bundle="${bundle}" /></b></p>
        <div>
            <input type="radio" id="one" name="hotelStars" value="ONE"
                   checked>
            <label for="one"><fmt:message key="one" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="two" name="hotelStars" value="TWO">
            <label for="two"><fmt:message key="two" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="three" name="hotelStars" value="THREE">
            <label for="three"><fmt:message key="three" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="four" name="hotelStars" value="FOUR">
            <label for="four"><fmt:message key="four" bundle="${bundle}" /></label>
        </div>
        <div>
            <input type="radio" id="five" name="hotelStars" value="FIVE">
            <label for="five"><fmt:message key="five" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <div>
            <input type="checkbox" id="allInclusive" name="isAllInclusive" value="allInclusive"
                   checked>
            <label for="allInclusive"><fmt:message key="isAllInclusive" bundle="${bundle}" /></label>
        </div>
    </div>
    <div>
        <div>
            <input type="checkbox" id="hot" name="isHot" value="hot"
                   checked>
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
