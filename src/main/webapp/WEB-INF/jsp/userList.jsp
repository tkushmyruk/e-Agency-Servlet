<%@page contentType="text/html" pageEncoding="UTF-8" %>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div>List of users</div>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr id="userList">
            <td>${user.username}</td>
            <td>${user.getRole().toString()}</td>
            <c:if test="${!user.isActive()}">
                <td>Blocked!</td>
            <td>
                <form method="post" action="Agency?command=userEdit">
                    <input type="hidden" name="method" value="activeMethod"/>
                    <input type="hidden" name="username" value="${user.getUsername()}"/>
                    <button type="submit">Active</button>
                </form>
            </td>
            </c:if>
            <c:if test="${user.isActive()}">
            <td>
                <form method="post" action="Agency?command=userEdit">
                    <input type="hidden" name="method" value="blockMethod"/>
                    <input type="hidden" name="username" value="${user.getUsername()}"/>
                    <button type="submit">block</button>
                </form>
            </td>
            </c:if>
            <td><form method="post" action="Agency?command=userEdit">
                <input type="hidden" name="method" value="roleMethod"/>
                <input type="hidden" name="username" value="${user.getUsername()}"/>
                <div>
                    <p><b><fmt:message key="role" bundle="${bundle}" /></b></p>
                    <div>
                        <input type="radio" id="admin" name="role" value="ADMIN">
                        <label for="admin"><fmt:message key="admin" bundle="${bundle}" /></label>
                    </div>
                    <div>
                        <input type="radio" id="manager" name="role" value="MANAGER">
                        <label for="manager"><fmt:message key="manager" bundle="${bundle}" /></label>
                    </div>
                    <div>
                        <input type="radio" id="user" name="role" value="USER">
                        <label for="user"><fmt:message key="user" bundle="${bundle}" /></label>
                    </div>
                </div>
            <button type="submit"><fmt:message key="changeRole" bundle="${bundle}" /></button>
            </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>