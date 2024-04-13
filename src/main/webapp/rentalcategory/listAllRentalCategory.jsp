<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalcategory.model.*"%>

<%
  RentalCategoryService rentalCategorySvc = new RentalCategoryService();
  List<RentalCategoryVO> list = rentalCategorySvc.getAll();
  pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>所有租借品類別資料查詢結果</title>
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
      width: 800px;
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
    <h3>所有租借品類別資料</h3>
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
    <th>修改</th>
    <th>刪除</th>
  </tr>
  <c:forEach var="rentalCategoryVO" items="${list}">
    <tr>
      <td>${rentalCategoryVO.rCatNo}</td>
      <td>${rentalCategoryVO.rCatName}</td>
      <td>${rentalCategoryVO.rStockQty}</td>
      <td>${rentalCategoryVO.rRentedQty}</td>
      <td>${rentalCategoryVO.rDesPrice}</td>
      <td>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" style="margin-bottom: 0px;">
          <input type="submit" id="update" value="修改">
          <input type="hidden" name="rCatNo"  value="${rentalCategoryVO.rCatNo}">
          <input type="hidden" name="action"  value="getOne_For_Update">
        </FORM>
      </td>
      <td>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" style="margin-bottom: 0px;">
          <input type="submit" id="delete" value="刪除">
          <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
          <input type="hidden" name="action" value="delete">
        </FORM>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<ul>
  <li><a href='addRentalCategory.jsp'>新增租借品類別</a></li>
</ul>

</body>
</html>