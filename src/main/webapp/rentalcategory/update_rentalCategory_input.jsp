<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ni.rentalcategory.vo.RentalCategoryVO" %>

<%--  RentalCategoryServlet.java(Controller), �s�Jreq��rentalCategoryVO����  --%>
<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO");
    pageContext.setAttribute("rentalCategoryVO", rentalCategoryVO);
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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
<body bgcolor='white'>

<table id="table-1">
        <tr>
            <td>
                <h3>���ɫ~���O��ƭק�</h3>
                <h4><a href="<%=request.getContextPath()%>/rentalcategory/select_rentalCategory_page.jsp">�^����</a></h4>
            </td>
        </tr>
    </table>
    <h3>��ƭק�:</h3>

    <%-- ���~��C --%>
     <c:if test="${not empty errorMsgs}">
    	<font style="color:red">�Эץ��H�U���~:</font>
    	<ul>
     		<c:forEach var="message" items="${errorMsgs}">
     			<li style="color:red">${message.value}</li>
     		</c:forEach>
     	</ul>
     </c:if>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" name="form1">
        <table>
            <tr>
                <td>���ɫ~���O�s��:<font color=red><b>*</b></font></td>
                <td>
                    <select size="1" name="rentalCategorySelect" id="rentalCategorySelect" onchange="updateRentalCategoryDetails()">
                        <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}">
                            <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>���ɫ~���O�W��:</td>
                <td><input type="TEXT" name="rCatName" id="rCatName"
                           value="<%= (rentalCategoryVO == null)? "���" : rentalCategoryVO.getrCatName() %>" size="45"/></td>
            </tr>
            <tr>
                <td>���ɫ~�w�s�ƶq:</td>
                <td><input type="TEXT" name="rStockQty" id="rStockQty"
                           value="<%= (rentalCategoryVO == null)? "1" : rentalCategoryVO.getrStockQty() %>" size="45"/></td>
            </tr>
            <tr>
                <td>���ɫ~�w���ɼƶq:</td>
                <td><input type="TEXT" name="rRentedQty" id="rRentedQty"
                           value="<%= (rentalCategoryVO == null)? "1" : rentalCategoryVO.getrRentedQty() %>" size="45"/></td>
            </tr>
            <tr>
                <td>���:</td>
                <td><input type="TEXT" name="rDesPrice" id="rDesPrice"
                           value="<%= (rentalCategoryVO == null)? "�Ŧ�" : rentalCategoryVO.getrDesPrice() %>" size="45"/></td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
        <input type="submit" value="�e�X�ק�"></FORM>
        <script>
            function updateRentalCategoryDetails() {
                var selectedRCatNo = document.getElementById('rentalCategorySelect').value;

                var xhr = new XMLHttpRequest();
                xhr.open('post', 'rentalCategory.do?action=getRentalCategoryDetails', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                <%-- ����A���^���T���ɡA�p�G�T�������A�X��200�]�Y���\�^�A���|�ѪRJSON�榡���^���A�M��N�������O���ԲӸ�T��s������W������������--%>
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        var rentalCategoryDetails = JSON.parse(xhr.responseText);

                        document.getElementById('rCatName').value = rentalCategoryDetails.rCatName;
                        document.getElementById('rStockQty').value = rentalCategoryDetails.rStockQty;
                        document.getElementById('rRentedQty').value = rentalCategoryDetails.rRentedQty;
                        document.getElementById('rDesPrice').value = rentalCategoryDetails.rDesPrice;

                    } else {
                        console.error('Failed to fetch rentalCategory details');
                    }
                };
                var requestData = 'rCatNo=' + encodeURIComponent(selectedRCatNo);
                xhr.send(requestData);
                }
        </script>
</body>
</html>