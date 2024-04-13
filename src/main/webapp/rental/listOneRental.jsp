<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rental.model.*"%>

<%
    RentalVO rentalVO = (RentalVO) request.getAttribute("rentalVO"); //RentalServlet.java(Concroller), 存入req的rentalVO物件
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>租借品資料查詢結果 - listOneRental.jsp</title>

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

        <h3>租借品資料</h3>
        <h4><a href="<%=request.getContextPath()%>select_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>租借品編號</th>
        <th>租借品名稱</th>
        <th>租借品單價</th>
        <th>租借品尺寸</th>
        <th>租借品顏色</th>
        <th>租借品資訊</th>
        <th>租借品狀態</th>
        <th>租借品類別編號</th>
    </tr>
    <tr>
        <td>${rentalVO.rNo}</td>
        <td>${rentalVO.rName}</td>
        <td>${rentalVO.rPrice}</td>
        <td>${rentalVO.rSize}</td>
        <td>${rentalVO.rColor}</td>
        <td>${rentalVO.rInfo}</td>
        <td>${rentalVO.rStat}</td>
        <td>${rentalVO.rCatNo}-[${rentalVO.rCatNo.rName}]</td>
    </tr>
</table>
</body>
</html>