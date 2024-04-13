<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>�d�߯��ɫ~���O - select_rentalCategory_page.jsp</title>
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
    <tr><td><h3>���ɫ~���OHome</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li>
        <a href='<%=request.getContextPath()%>listAllRentalCategory.jsp'>�d�ߩҦ����ɫ~���O</a><br><br>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do" enctype="multipart/form-data">
            <b>��J���ɫ~���O�s�� (ex. 1):</b>
            <input type="text" name="rCatNo" value="${rentalCategoryVO.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </FORM>
    </li>
    <jsp:useBean id="rentalCategorySvc" scope="page" class="com.rentalcategory.model.RentalCategoryService"/>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do">
            <b>��ܯ��ɫ~���O�s��:</b>
            <select size="1" name="rCatNo">
            <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}">
                <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </FORM>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rentalCategory.do">
            <b>��J���ɫ~���O�W��:</b>
            <input type="text" name="rCatName">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </FORM>
    </li>
</ul>
<br>
<ul>
    <li><a href='addRentalCategory.jsp'>�s�W���ɫ~���O</a></li>
</ul>
</body>
</html>