<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html >

<html>

<head>

    <title>MyPetStore</title>
    <link rel="StyleSheet" href="css/mypetstore.css" type="text/css"
          media="screen" />
    <script src="http://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainFrom"><img src="images/logo-topbar.gif"/></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif"/></a>

            <img align="middle" src="images/separator.gif"/>
            <c:if test="${sessionScope.loginAccount==null}">
                <a href="signonForm">Sign In</a>
                <img align="middle" src="images/separator.gif"/>
                <a href="registerForm">Register</a>
                    <img align="middle" src="images/separator.gif"/>
            </c:if>

            <c:if test="${sessionScope.loginAccount!=null}">
                <a href="#">Sign Out</a>
                <img align="middle" src="images/separator.gif"/>
<<<<<<< HEAD
                <a href="accountUpdate">My Account</a>
=======
                <a href="logForm?type=viewlog&page=1">My Account</a>
>>>>>>> 748a8a19f27a2f0c16b159d0c98dbe2726901c09
                <img align="middle" src="images/separator.gif"/>
            </c:if>


            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="" method="post">
                <input type="text" name="keyword" size="14" id="keyword">
                <input type="submit" value="Search">
            </form>
            <div id="productAutoComplete">
                <ul id="productAutoList">
<%--                    <li class="productAutoItem">Amazon Parrot</li>--%>
<%--                    <li class="productAutoItem">Labrador Retriever</li>--%>
<%--                    <li class="productAutoItem">Golden Retriever</li>--%>
<%--                    <li class="productAutoItem">Rattlesnake</li>--%>
<%--                    <li class="productAutoItem">Tiger Shark</li>--%>
<%--                    <li class="productAutoItem">Dalmation</li>--%>
                </ul>
            </div>
        </div>

    </div>

    <div id="QuickLinks">

        <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif"/></a>
        <img src="images/separator.gif"/>

        <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif"/></a>
        <img src="images/separator.gif"/>

        <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif"/></a>
        <img src="images/separator.gif"/>

        <a href="categoryForm?categoryId=CATS"><img src="images/sm_cats.gif"/></a>
        <img src="images/separator.gif"/>

        <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif"/></a>


    </div>

</div>

<div id="Content">
<%--</div>--%>