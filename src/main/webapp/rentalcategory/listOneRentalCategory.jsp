<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rentalcategory.model.*"%>

<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), 存入req的rentalCategoryVO物件
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>租借品類別資料查詢結果 - listOneRentalCategory.jsp</title>
    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>
    <style>
        table {
            width: 600px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>

<body>
<table id="table-1">
    <tr>
        <td>
            <h3>租借品類別資料</h3>
            <h4><a href="<%=request.getContextPath()%>select_rentalCategory_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>租借品類別編號</th>
        <th>租借品類別名稱</th>
        <th>租借品庫存數量</th>
        <th>租借品已租借數量</th>
        <th>押金</th>
    </tr>
    <tr>
        <td>${rentalCategoryVO.rCatNo}</td>
        <td>${rentalCategoryVO.rCatName}</td>
        <td>${rentalCategoryVO.rStockQty}</td>
        <td>${rentalCategoryVO.rRentedQty}</td>
        <td>${rentalCategoryVO.rDesPrice}</td>
    </tr>
</table>
</body>
</html>