<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalcategory.model.*"%>

<%
	RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), �s�Jreq��rentalCategoryVO����
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>���ɫ~���O��Ʒs�W - addRentalCategory.jsp</title>
	<style>
		table#table-1 {
			width: 450px;
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
			background-color: white;
			margin-top: 1px;
			margin-bottom: 1px;
		}
		table, th, td {
			border: 0px solid #CCCCFF;
		}
		th, td {
			padding: 1px;
		}
	</style>
</head>

<body>
<table id="table-1">
	<tr>
		<td>
			<h3>���ɫ~���O��Ʒs�W</h3>
			<h4><a href="<%=request.getContextPath()%>select_rentalCategory_page.jsp">�^����</a></h4>
		</td>
	</tr>
</table>

<h3>�s�W��ơG</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>���ɫ~���O�W��:</td>
			<td><input type="TEXT" name="rCatName" id="rCatName" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrCatName()%>"></td>
		</tr>
		<tr>
			<td>���ɫ~�w�s�ƶq:</td>
			<td><input type="TEXT" name="rStockQty" id="rStockQty" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrStockQty()%>"></td>
		</tr>
		<tr>
			<td>���ɫ~�w���ɼƶq:</td>
			<td><input type="TEXT" name="rRentedQty" id="rRentedQty" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrRentedQty()%>"></td>
		</tr>
		<tr>
			<td>���:</td>
			<td><input type="TEXT" name="rDesPrice" id="rDesPrice" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrDesPrice()%>"></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" id="submitAdd" value="�e�X�s�W">
</FORM>
	<script src="<%=request.getContextPath()%>/jquery/jquery.js"></script>
</body>
</html>