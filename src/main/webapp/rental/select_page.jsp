<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查詢租借品 - select_page.jsp</title>
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
    <li>
        <a href='<%=request.getContextPath()%>listAllRental.jsp'>查詢所有租借品</a><br><br>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do" enctype="multipart/form-data">
            <b>輸入租借品編號 (ex. 5001):</b>
            <input type="text" name="rNo" value="${rentalVO.rNo}"><font color=red>${errorMsgs.rNo}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <jsp:useBean id="rentalSvc" scope="page" class="com.rental.model.RentalService" />
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do">
            <b>選擇租借品類別編號:</b>
            <select size="1" name="rCatNo">
                <c:forEach var="rentalVO" items="${rentalSvc.all}">
                    <option value="${rentalVO.rNo}">${rentalVO.rCatNo}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do">
            <b>輸入租借品名稱:</b>
            <input type="text" name="rName" value="${rentalVO.rName}"><font color=red>${errorMsgs.rName}</font>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do">
            <b>選擇租借品尺寸:</b>
            <select size="1" name="rSize">
                <c:forEach var="rentalVO" items="${rentalSvc.all}" >
                    <option value="${rentalVO.rNo}">${rentalVO.rSize}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do">
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
        <FORM method="post" action="<%=request.getContextPath()%>/rental.do">
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
<br>
<ul>
    <li><a href='addRental.jsp'>新增租借品</a></li>
</ul>
</body>
</html>