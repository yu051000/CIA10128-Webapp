<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>租借品 - select_page.jsp</title>
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
<body bgcolor='white'>

<table id="table-1">
    <tr><td><h3>租借品Home</h3><h4>( MVC )</h4></td></tr>
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
    <li><a href='listALLRental.jsp'>List</a> 查詢所有租借品 <br><br></li>
    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>輸入租借品編號 (ex. 5001):</b>
            <input type="text" name="rNo" value="${param.rNo}"><font color=red>${errorMsgs.rNo}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <jsp:useBean id="rentalSvc" scope="page" class="com.rental.model.RentalService" />
    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>輸入租借品類別編號 (ex. 1):</b>
            <input type="text" name="rCatNo" value="${param.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>輸入租借品名稱:</b>
            <input type="text" name="rName" value="${param.rName}"><font color=red>${errorMsgs.rName}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>選擇租借品尺寸:</b>
            <select size="1" name="rSize">
                <c:forEach var="rentalVO" items="${rentalSvc.all}" >
                <option value="${rentalVO.rSize}">${rentalVO.rSize}
                    </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>租借品顏色:</b>
            <select size="1" name="rColor">
                <c:forEach var="rentalVO" items="${rentalSvc.all}" >
                <option value="${rentalVO.rNo}">${rentalVO.rColor}
                    </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="rental.do" >
            <b>租借品狀態:</b>
            <select size="1" name="rStat">
                <c:forEach var="rentalVO" items="${rentalSvc.all}" >
                <option value="${rentalVO.rNo}">${rentalVO.rStat}
                    </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
</ul>


<h3>租借品管理</h3>

<ul>
    <li><a href='addRental.jsp'>新增</a>租借品</li>
</ul>

</body>
</html>