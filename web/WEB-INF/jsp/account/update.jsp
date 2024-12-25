<%@ include file="../common/top.jsp"%>
<html>
<head>
    <title>Update password!</title>
</head>
<body>
<div id="Catalog">

    <form action="update" method="post">
        <p>Change password.</p>
        <c:if test="${requestScope.UpdateMsg!=null}">
            <p><font color="red">${requestScope.UpdateMsg}</font></p>
        </c:if>

        <label for="current_password">Current Password:</label>
        <input type="password" name="current_password" id="current_password"/><br/>
        <div id="feedback1"></div><br/>

        <label for="password1">New Password:</label>
        <input type="password" name="password1" id="password1"/><br/>
        <div id="feedback2"></div><br/>

        <label for="password2">Confirm Password:</label>
        <input type="password" name="password2" id="password2"/><br/><br/>
        <div id="feedback3"></div><br/>

        <p>
            Captcha:<input type="text" name="vcode"/><br /><br /><img src="code" onclick="this.src='code?'+Math.random()"><br/><br/>
        </p>

        <input type="submit" value="Update">
    </form>

</div>
<script src="./js/check-password.js"></script>
</body>

<%@ include file="../common/bottom.jsp"%>
</html>