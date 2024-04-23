<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yu.rentalcategory.model.RentalCategoryVO"%>

<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), 存入req的rentalCategoryVO物件
    pageContext.setAttribute("rentalCategoryVO", rentalCategoryVO);
%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous"/>
    <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>租借品類別資料查詢結果 - listOneRentalCategory.jsp</title>
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
    <style>
        table {
            width: 600px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>

<body>
    <table id="table-1">
        <tr>
            <td>
                <h3>單筆查詢</h3>
                <h3>租借品類別資料</h3>
                <h4><a href="<%=request.getContextPath()%>/rentalcategory/select_rentalCategory_page.jsp">回首頁</a></h4>
            </td>
        </tr>
    </table>
    <ul>
        <li><a href="<%=request.getContextPath()%>/rentalcategory/listAllRentalCategory.jsp">查詢所有租借品類別</a><br></li>
    </ul>
    <table>
        <tr>
            <th>租借品類別編號</th>
            <th>租借品類別名稱</th>
            <th>租借品庫存數量</th>
            <th>租借品已租借數量</th>
            <th>押金</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        <tr>
            <td>${rentalCategoryVO.rCatNo}</td>
            <td>${rentalCategoryVO.rCatName}</td>
            <td>${rentalCategoryVO.rStockQty}</td>
            <td>${rentalCategoryVO.rRentedQty}</td>
            <td>${rentalCategoryVO.rDesPrice}</td>
            <td>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
                    <input type="hidden" name="action" value="getOne_For_Update">
                </FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
                    <input type="hidden" name="action" value="delete">
                </FORM>
            </td>
        </tr>
    </table>
<%--    <script src="./vendors/jquery/jquery-3.7.1.min.js"></script>--%>
<%--    <script>--%>
<%--        $(function(){--%>
<%--            $("button.update").on("click", function(){ &lt;%&ndash;點擊修改按鈕&ndash;%&gt;--%>
<%--                // alert("修改按鈕ok");--%>
<%--                &lt;%&ndash;使用jQuery的屬性替換方法轉移頁面&ndash;%&gt;--%>
<%--                $(location).attr("href","<%=request.getContextPath()%>select_rentalCategory_page.jsp")--%>
<%--            })--%>

<%--            $("button.delete").on("click", function(){ &lt;%&ndash;點擊刪除按鈕&ndash;%&gt;--%>
<%--                // alert("刪除按鈕ok");--%>
<%--                &lt;%&ndash;使用jQuery的屬性替換方法轉移頁面&ndash;%&gt;--%>
<%--                $(location).attr("href","<%=request.getContextPath()%>select_rentalCategory_page.jsp")--%>
<%--            })--%>
<%--        });--%>
<%--    </script>--%>
</body>
</html>

