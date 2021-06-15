<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>Tour Page</div>
<br/>
<div>
    <h4>Sorting</h4>
    <form method="post" action="Agency?command=sorting">
        <div>By Tour Type</div>
        <input type="hidden" name="sortType" value="byTourType">
        <label> Rest <input type="radio" id="rest" name="tourType" value="REST"/></label>
        <label> Excursion <input type="radio" id="excursion" name="tourType" value="EXCURSION"/></label>
        <label> Shopping <input type="radio" id="shopping" name="tourType" value="SHOPPING"/></label>
        <button class="button-sort" type="submit">Sort</button>
    </form>
    <form method="post" action="Agency?command=sorting">
        <div>By Hotel Stars</div>
        <input type="hidden" name="sortType" value="byHotelStars">
        <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
        <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
        <button class="button-sort" type="submit">Sort</button>
    </form>
    <form method="post" action="Agency?command=sorting">
        <div>By Price</div>
        <input type="hidden" name="sortType" value="byPrice">
        <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
        <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
        <button class="button-sort" type="submit">Sort</button>
    </form>
    <form method="post" action="Agency?command=sorting">
        <div>By Count of People</div>
        <input type="hidden" name="sortType" value="byCountOfPeople">
        <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
        <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
        <button class="button-sort" type="submit">Sort</button>
    </form>
</div>
<c:forEach var="tour" items="${notBoughtTours}">
    <div id="tour-list">
        <div><c:out value="${tour.getTourName()}"/> </div>
        <div>
            <a href="/Agency?command=tourInfo&tourName=${tour.getTourName()}">
                <fmt:message key="moreAbout" bundle="${bundle}"/>
            </a>
        </div>
        <div>
            <a href="/Agency?command=redactTour&tourName=${tour.getTourName()}&redact=false">
                <fmt:message key="redact" bundle="${bundle}"/>
            </a>
        </div>
        <div>
            <form method="post" action="/Agency?command=cancelTour&tourName=${tour.getTourName()}">
                <button  class="button" type="submit"><fmt:message key="cancel" bundle="${bundle}"/></button>
            </form>
        </div>
    </div>
    <br/>
</c:forEach>
<a href="/Agency?command=redirectAddTour">Add Tour</a>

</body>