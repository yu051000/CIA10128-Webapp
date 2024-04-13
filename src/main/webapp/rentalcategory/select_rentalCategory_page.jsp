<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查詢租借品類別 - select_rentalCategory_page.jsp</title>
    <style>
        table#table-1 {
            width: 450px;
            background-color: #CCCCFF;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
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
</head>

<body>
<table id="table-1">
    <tr><td><h3>租借品類別Home</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li>
        <a href='<%=request.getContextPath()%>listAllRentalCategory.jsp'>查詢所有租借品類別</a><br><br>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" enctype="multipart/form-data">
            <b>輸入租借品類別編號 (ex. 1):</b>
            <input type="text" name="rCatNo" value="${rentalCategoryVO.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <jsp:useBean id="rentalCategorySvc" scope="page" class="com.rentalcategory.model.RentalCategoryService"/>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do">
            <b>選擇租借品類別編號:</b>
            <select size="1" name="rCatNo">
            <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}">
                <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do">
            <b>輸入租借品類別名稱:</b>
            <input type="text" name="rCatName">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
</ul>
<br>
<ul>
    <li><a href='addRentalCategory.jsp'>新增租借品類別</a></li>
</ul>
</body>
</html>