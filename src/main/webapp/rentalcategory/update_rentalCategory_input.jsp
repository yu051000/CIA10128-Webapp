<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalcategory.model.*"%>

<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>���ɫ~���O��ƭק�</title>
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
            <h3>���ɫ~���O��ƭק�</h3>
            <h4><a href="<%=request.getContextPath()%>select_rentalCategory_page.jsp">�^����</a></h4>
        </td>
    </tr>
</table>
<h3>��ƭק�:</h3>

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
            <td>���ɫ~���O�s��:<font color=red><b>*</b></font></td>
            <td><%=rentalCategoryVO.getrCatNo()%></td>
        </tr>
        <tr>
            <td>���ɫ~���O�W��:</td>
            <td><input type="TEXT" name="rCatName" value="<%=rentalCategoryVO.getrCatName()%>"></td>
        </tr>
        <tr>
            <td>���ɫ~�w�s�ƶq:</td>
            <td><input type="TEXT" name="rStockQty" value="<%=rentalCategoryVO.getrStockQty()%>"></td>
        </tr>
        <tr>
            <td>���ɫ~�w���ɼƶq:</td>
            <td><input type="TEXT" name="rRentedQty" value="<%=rentalCategoryVO.getrRentedQty()%>"></td>
        </tr>
        <tr>
            <td>���:</td>
            <td><input type="TEXT" name="rDesPrice" value="<%=rentalCategoryVO.getrDesPrice()%>"></td>
        </tr>
        <jsp:useBean id="rentalCategorySvc" scope="page" class="com.rentalcategory.model.RentalCategoryService" />
    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="rCatNo" value="<%=rentalCategoryVO.getrCatNo()%>">
    <input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>