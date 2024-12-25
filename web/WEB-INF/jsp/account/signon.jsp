<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg!=null}">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>Username:<input type="text" name="username"/> <br /><br />
            Password:<input type="password" name="password"/><br /><br/>
            &nbsp;Captcha:<input type="text" name="vcode"/><br /><br /><img src="code" onclick="this.src='code?'+Math.random()">
        </p>
        <input type="submit" value="Login">
    </form>
    Need a user name and password?
    <a href="registerForm">Register Now!</a>
</div>


<%@ include file="../common/bottom.jsp"%>


