<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ni.rentalcategory.vo.RentalCategoryVO" %>

<%
	RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), 存入req的rentalCategoryVO物件
%>
--<%= rentalCategoryVO==null %>--${rentalCategoryVO.rCatNo}--
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>租借品類別資料新增</title>
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
				<h3>租借品類別資料新增</h3>
				<h4><a href="<%=request.getContextPath()%>select_rentalCategory_page.jsp">回首頁</a></h4>
			</td>
		</tr>
	</table>

	<h3>新增資料：</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM method="post" action="<%=request.getContextPath()%>rentalCategory.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>租借品類別名稱:</td>
				<td><input type="TEXT" name="rCatName" id="rCatName" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrCatName()%>"></td>
			</tr>
			<tr>
				<td>租借品庫存數量:</td>
				<td><input type="TEXT" name="rStockQty" id="rStockQty" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrStockQty()%>"></td>
			</tr>
			<tr>
				<td>租借品已租借數量:</td>
				<td><input type="TEXT" name="rRentedQty" id="rRentedQty" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrRentedQty()%>"></td>
			</tr>
			<tr>
				<td>押金:</td>
				<td><input type="TEXT" name="rDesPrice" id="rDesPrice" value="<%=(rentalCategoryVO==null) ? "" : rentalCategoryVO.getrDesPrice()%>"></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" id="submitAdd" value="送出新增">
	</FORM>
</body>
</html>