<%@ include file="../common/top.jsp"%>
<html>
<head>
    <title>User Register!</title>
<%--    <link rel="stylesheet" type="text/css" href="./css/register.css">--%>
</head>
<body>
<div id="Catalog">

    <form action="register" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.RegisterMsg!=null}">
            <p><font color="red">${requestScope.RegisterMsg}</font></p>
        </c:if>
        <p>Please enter your username and password.</p>
            <label for="username">Username:</label>
            <input type="text" name="username" id="username"/><br/>
            <div id="feedback"></div><br/>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password"/><br/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label for="email">Email:</label>
            <input type="text" name="email" id="email"/><br/><br/>
<%--        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email:<input type="password" name="email"/>--%>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;
            Captcha:<input type="text" name="vcode"/><br /><br /><img src="code" onclick="this.src='code?'+Math.random()"><br/><br/>
            </p>
<%--        <p>Username:<input type="text" name="username"/> <br /><br />--%>
<%--            Password:<input type="password" name="password"/><br /><br />--%>
<%--            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email:<input type="password" name="email"/><br /><br />--%>
<%--            &nbsp;Captcha:<input type="text" name="vcode"/><br /><br /><img src="code" onclick="this.src='code?'+Math.random()">--%>
<%--        </p>--%>


          <input type="submit" value="Register">
    </form>

</div>
<script src="./js/check-username.js"></script>
</body>

<%@ include file="../common/bottom.jsp"%>
</html>

<%--<script>--%>
<%--    var xhr;--%>
<%--    function checkUsername(){--%>
<%--        var username=document.getElementById('username').value;--%>
<%--        xhr=new XMLHttpRequest();--%>
<%--        xhr.onreadystatechange=process;--%>
<%--        xhr.open("GET","usernameIsExist?username="+usrname,true);--%>
<%--        xhr.send(null);--%>
<%--    }--%>
<%--    function process(){--%>
<%--        if(xhr.readyState==4){--%>
<%--            if(xhr.status==200){--%>
<%--                var responseInfo=xhr.responseText;--%>
<%--                var msg=document.getElementById('isExistInfo');--%>
<%--                if(responseInfo=='Exist') {--%>
<%--                    msg.classList.add('okmsg');--%>
<%--                    msg.innerText = '用户名可用';--%>
<%--                }else if(responseInfo=='Not Exist'){--%>
<%--                    msg.classList.add('errormsg');--%>
<%--                    msg.innerText='用户名不可用';--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
