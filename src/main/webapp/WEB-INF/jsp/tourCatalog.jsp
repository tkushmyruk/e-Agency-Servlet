<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>Tour Page</div>
<br/>
<c:forEach var="tour" items="${notBoughtTours}">
    <div id="tour-list">
        <div><c:out value="${tour.getTourName()}"/> </div>
        <div>
            <a href="/Agency?command=tourInfo&tourName=${tour.getTourName()}">
                <fmt:message key="moreAbout" bundle="${bundle}"/>
            </a>
        </div>
        <div>
            <a href="/tour-redact/${tour.getTourName()}"><<fmt:message key="redact" bundle="${bundle}"/>/a>
        </div>
        <div>
            <form method="post" action="/Agency">
                <button type="submit"><fmt:message key="cancel" bundle="${bundle}"/></button>
            </form>
        </div>
    </div>
    <br/>
</c:forEach>
<a href="/Agency?command=redirectAddTour">Add Tour</a>

</body>