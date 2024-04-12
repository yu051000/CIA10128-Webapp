<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rental.model.*"%>

<%
     RentalVO rentalVO = (RentalVO) request.getAttribute("rentalVO");
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>租借品資料修改 - update_rental_input.jsp</title>

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
    <tr><td>
        <h3>租借品資料修改 - update_rental_input.jsp</h3>
        <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="rental.do" name="form1">
    <table>
        <tr>
            <td>租借品編號:<font color=red><b>*</b></font></td>
            <td>${param.rNo}</td>
        </tr>
        <tr>
            <td>租借品名稱:</td>
            <td><input type="TEXT" name="rName" value="${param.rName}" size="45"/></td> <td>${errorMsgs.rName}</td>
        </tr>
        <tr>
            <td>租借品單價:</td>
            <td><input type="TEXT" name="rPrice"   value="${param.rPrice}"   size="45"/></td> <td>${errorMsgs.rPrice}</td>
        </tr>
        <tr>
            <td>租借品尺寸:</td>
            <td><input type="TEXT" name="rSize"   value="${param.rSize}"   size="45"/></td> <td>${errorMsgs.rSize}</td>
        </tr>
        <tr>
            <td>租借品顏色:</td>
            <td><input type="TEXT" name="rColor"   value="${param.rColor}"   size="45"/></td> <td>${errorMsgs.rColor}</td>
        </tr>
        <tr>
            <td>租借品資訊:</td>
            <td><input type="TEXT" name="rInfo"  value="${param.rInfo}"  size="45"/></td> <td>${errorMsgs.rInfo}</td>
        </tr>
        <tr>
            <td>租借品狀態:</td>
            <td><input type="TEXT" name="rStat"  value="${param.rStat}"  size="45"/></td> <td>${errorMsgs.rStat}</td>
        </tr>

        <jsp:useBean id="rentalSvc" scope="page" class="com.rental.model.RentalService" />
        <tr>
            <td>租借品類別編號:<font color=red><b>*</b></font></td>
            <td><select size="1" name="rCatNo">
                <c:forEach var="rentalVO" items="${rentalSvc.all}">
                <option value="${rentalVO.rCatNo}" ${(param.rCatNo==rentalVO.rCatNo)? 'selected':'' } >${rentalVO.rName}
                    </c:forEach>
            </select></td>
        </tr>

    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="rNo" value="${param.rNo}">
    <input type="submit" value="送出修改"></FORM>
</body>
</html>