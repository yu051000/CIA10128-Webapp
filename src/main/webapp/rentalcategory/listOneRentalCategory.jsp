<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rentalcategory.model.*"%>

<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), �s�Jreq��rentalCategoryVO����
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>���ɫ~���O��Ƭd�ߵ��G - listOneRentalCategory.jsp</title>
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
            <h3>���ɫ~���O���</h3>
            <h4><a href="<%=request.getContextPath()%>select_rentalCategory_page.jsp">�^����</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>���ɫ~���O�s��</th>
        <th>���ɫ~���O�W��</th>
        <th>���ɫ~�w�s�ƶq</th>
        <th>���ɫ~�w���ɼƶq</th>
        <th>���</th>
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