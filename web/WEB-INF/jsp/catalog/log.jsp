<%@ include file="../common/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="currenttype" value="${sessionScope.type}"/>
<head>
    <title>历史记录</title>
    <style>
        .menu {
            background-color: #f2f2f2;
            padding: 10px;
        }
        .menu a {
            margin: 10px;
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>

<div class="menu">
    <a href="logForm?type=viewlog">我的浏览</a>
    <a href="logForm?type=cartlog">我的购物车</a>
    <a href="logForm?type=commitlog">我的订单</a>
</div>

<h2>您的历史记录</h2>
<table border="1">
    <tr>
        <th>时间</th>
        <th>记录</th>
    </tr>
    <c:forEach var="log" items="${logList}">
        <tr>
            <td>${log.time}</td>
            <td>${log.document}</td>
        </tr>
    </c:forEach>
</table>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="logForm?page=${currentPage-1}&type=${currenttype}">上一页</a>
    </c:if>

    <c:forEach begin="1" end="${totalPages}" var="page">
        <c:if test="${page==currentPage}">
            <span>${page}</span>
        </c:if>

        <c:if test="${page!=currentPage}">
            <a href="logForm?page=${page}&type=${currenttype}">${page}</a>
        </c:if>
    </c:forEach>

    <c:if test="${currentPage <totalPage}">
        <a href="logForm?page=${currentPage+1}&type=${currenttype}">下一页</a>
    </c:if>
</div>
</div>
</body>
</html>

<%@ include file="../common/bottom.jsp"%>
