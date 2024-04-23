<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">--%>
    <title>List RentalCats</title>
</head>
<body>
<h1>員工列表</h1>
<br>
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/3.jpg">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/4.jpeg">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/2.jpg">
<table style="width:50%; text-align:center;">
    <tr>
        <th>租借品類別編號</th>
        <th>租借品類別名稱</th>
        <th>租借品庫存數量</th>
        <th>租借品已租借數量</th>
        <th>押金</th>
    </tr>
    <c:forEach var="rentalCategoryVO" items="${list}">
        <tr>
            <td>${rentalCategoryVO.rCatNo}</td>
            <td>${rentalCategoryVO.rCatName}</td>
            <td>${rentalCategoryVO.rStockQty}</td>
            <td>${rentalCategoryVO.rRentedQty}</td>
            <td>${rentalCategoryVO.rDesPrice}</td>

        </tr>
    </c:forEach>
</table>
<br>
<br><br>

<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>
</body>
</html>