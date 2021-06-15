<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agency</title>
    <style>
        header {background-color: rgba(20, 20, 00, 0.2)}
        a {color: midnightblue; text-decoration: underline; font-style: italic}
        body{background-color: rgba(00, 00, 50, 0.05)}
        #header-button {background-color: slategrey;
            color: white;
            padding: 12px 25px;
            text-align: center;
            display: inline-block;
            font-size: 14px;
            margin-top: 0.4em;
        }
        #tour-info{
            color: rgb(30, 00, 20);
            font-family: "Times New Roman";
        }
        .login-label {
            color: navy;
            font-weight: bold;
            display: block;
            width: 100px;
            line-height: 25px;
            float: left;
        }
        .login-input{
            margin-top: 0.4em;
        }
        .button{background-color: royalblue;
            color: bisque;
            padding: 10px 20px;
            text-align: center;
            display: inline-block;
            font-size: 14px;
            margin-top: 0.4em;
        }
        .error{
            color: #D8000C;
            background-color: #FFBABA;
        }
    </style>
</head>
<body>
<header>
    <%@include file="/WEB-INF/jsp/i18n.jsp" %>
    <form action="Agency" method="POST">
        <div><input name="command" value="setLang" type="hidden"/></div>
        <div><input name="page" value="${page}" type="hidden"/></div>
        <input style="font-family: 'Times New Roman'; font-style: italic" type="radio" id="lang" name="lang" value="en" checked="checked"/>EN
        <input style="font-family: 'Times New Roman'; font-style: italic" type="radio" id="lang" name="lang" value="ua"/>UA
        <button id="header-button" type="submit">
            <fmt:message key="changeLang" bundle="${bundle}"/>
        </button>
    </form>
    <c:if test="${empty role}">
        <li><a href="/Agency?command=redirectLogin"><fmt:message key="signIn" bundle="${bundle}" /></a></li>
        <li><a href="/Agency?command=redirectRegistration"><fmt:message key="registration" bundle="${bundle}" /></a></li>
    </c:if>
    <c:if test="${not empty role}">
        <c:if test = "${role == 'ADMIN' || role == 'MANAGER'}">
            <li><a href="/Agency?command=adminProfile"><fmt:message key="profile" bundle="${bundle}" /></a></li>
        </c:if>
        <c:if test = "${role == 'USER'}">
            <li><a href="/Agency?command=userProfile"><fmt:message key="profile" bundle="${bundle}" /></a></li>
        </c:if>
        <li><a href="/Agency?command=logout"><fmt:message key="logout" bundle="${bundle}" /></a></li>
        <li><a href="/Agency?command=tourList"><fmt:message key="tours" bundle="${bundle}" /></a></li>
    </c:if>
    <br/>
    <br/>
</header>
</body>