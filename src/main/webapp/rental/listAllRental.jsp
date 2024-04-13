<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rental.model.*"%>

<%
  RentalService rentalSvc = new RentalService();
  List<RentalVO> list = rentalSvc.getAll();
  pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>所有員工資料查詢結果</title>

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
      <h3>所有租借品資料</h3>
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
    <th>修改</th>
    <th>刪除</th>
  </tr>
  <c:forEach var="rentalVO" items="${list}">

    <tr>
      <td>${rentalVO.rNo}</td>
      <td>${rentalVO.rName}</td>
      <td>${rentalVO.rPrice}</td>
      <td>${rentalVO.rSize}</td>
      <td>${rentalVO.rColor}</td>
      <td>${rentalVO.rInfo}</td>
      <td>${rentalVO.rStat}</td>
      <td>${rentalVO.rCatNo}-[${rentalVO.rCatNo.rName}]</td>
      <td>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do" style="margin-bottom: 0px;">
          <input type="submit" value="修改">
          <input type="hidden" name="rNo"  value="${rentalVO.rNo}">
          <input type="hidden" name="action" value="getOne_For_Update">
        </FORM>
      </td>
      <td>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do" style="margin-bottom: 0px;">
          <input type="submit" value="刪除">
          <input type="hidden" name="rNo"  value="${rentalVO.rNo}">
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