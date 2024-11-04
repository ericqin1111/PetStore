<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.RegisterMsg!=null}">
            <p><font color="blue">${requestScope.RegisterMsg}</font></p>
        </c:if>

        <p>Username:<input type="text" name="username"/> <br /><br />
            Password:<input type="password" name="password"/><br /><br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email:<input type="password" name="email"/><br /><br />
            &nbsp;Captcha:<input type="text" name="code"/><br /><br /><img src="code" onclick="this.src='code?'+Math.random()">
        </p>
        <input type="submit" value="Register">
    </form>

</div>
<%@ include file="../common/bottom.jsp"%>


