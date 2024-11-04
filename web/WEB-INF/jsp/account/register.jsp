<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.RegisterMsg!=null}">
            <p><font color="blue">${requestScope.RegisterMsg}</font></p>
        </c:if>

        <p>Username:<input type="text" name="username"/> <br />
            Password:<input type="password" name="password"/><br />
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email:<input type="password" name="email"/></p>
        <input type="submit" value="Register">
    </form>

</div>
<%@ include file="../common/bottom.jsp"%>


