<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>Tour Page</div>
<br/>
<c:forEach var="tour" items="${userTours}">
    <div id="tour-list">
        <div><c:out value="${tour.getTourName()}"/> </div>
        <div>
            <a href="/Agency?command=tourInfo&tourName=${tour.getTourName()}">
                <fmt:message key="moreAbout" bundle="${bundle}"/>
            </a>
        </div>
    </div>
    <br/>
</c:forEach>

</body>