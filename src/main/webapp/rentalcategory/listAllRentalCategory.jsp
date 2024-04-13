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
  <title>�Ҧ����ɫ~���O��Ƭd�ߵ��G</title>
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
    <h3>�Ҧ����ɫ~���O���</h3>
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
    <th>�ק�</th>
    <th>�R��</th>
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
          <input type="submit" id="update" value="�ק�">
          <input type="hidden" name="rCatNo"  value="${rentalCategoryVO.rCatNo}">
          <input type="hidden" name="action"  value="getOne_For_Update">
        </FORM>
      </td>
      <td>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" style="margin-bottom: 0px;">
          <input type="submit" id="delete" value="�R��">
          <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
          <input type="hidden" name="action" value="delete">
        </FORM>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<ul>
  <li><a href='addRentalCategory.jsp'>�s�W���ɫ~���O</a></li>
</ul>

</body>
</html>