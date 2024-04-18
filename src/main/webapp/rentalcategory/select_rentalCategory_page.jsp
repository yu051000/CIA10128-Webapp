<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>查詢租借品類別</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
          crossorigin="anonymous"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
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
        <tr>
            <td>
                <h3>租借品類別Home</h3><h4>( MVC )</h4>
            </td>
        </tr>
    </table>

    <h3>資料查詢:</h3>

    <%-- 錯誤表列 --%>
     <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message.value}</li>
            </c:forEach>
        </ul>
     </c:if>

    <ul>
        <li><a href='listAllRentalCategory.jsp'>查詢所有租借品類別</a><br><br></li>
        <li><a href='addRentalCategory.jsp'>新增租借品類別</a><br><br></li>

        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data"><%--設置表單MIME編碼--%>
                <b>輸入租借品類別編號 (如：1):</b>
                <input type="text" name="rCatNo" value="${param.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>

        <jsp:useBean id="rentalCategorySvc" scope="page" class="com.rentalcategory.model.RentalCategoryService"/><%--指明要使用一個 bean 物件--%>
        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data">
                <b>選擇租借品類別編號:</b>
                <select size="1" name="rCatNo">
<%--                    <option value="default">請選擇編號</option>--%>
                <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> <%--迭代操作--%>
                    <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}
                </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>

        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data">
                <b>選擇租借品類別名稱:</b>
                <select size="1" name="rCatName">
<%--                    <option value="default">請選擇名稱</option>--%>
                    <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> <%--迭代操作--%>
                        <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatName}
                    </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>
    </ul>
</body>
</html>