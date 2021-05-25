<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ser-Agency</title>
</head>
<body>
<form method="POST">
    <div><input name="command" value="login" type="hidden"/></div>
    <div>
        <div>
            <label><fmt:message key="login" bundle="${bundle}"/></label>
            <input type="text" id="login" name="login"  required />
        </div>
        <div>
            <label><fmt:message key="password" bundle="${bundle}" /></label>
            <input type="password" id="password" name="password" required />
        </div>
    </div>
    <div>
        <div class="input-block">
            <label>
                <button class="button" type="submit">
                    <fmt:message key="signIn" bundle="${bundle}" />
                </button>
            </label>
        </div>
    </div>
</form>
<li><a href="/Airline?command=redirectRegistration">Login</a></li>
</body>
</html>