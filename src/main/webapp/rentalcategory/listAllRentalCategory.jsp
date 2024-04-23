<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yu.rentalcategory.service.RentalCategoryServiceImpl" %>
<%@ page import="com.yu.rentalcategory.model.RentalCategoryVO"%>

<%
  RentalCategoryServiceImpl rentalCategorySvc = new RentalCategoryServiceImpl();
  List<RentalCategoryVO> list = rentalCategorySvc.getAll();
  pageContext.setAttribute("list",list);
%>

<html>
<head>
  <meta charset="UTF-8">
  <title>所有租借品類別資料</title>

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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
<table id="table-1">
  <tr>
    <td>
      <h3>所有租借品類別資料</h3>
      <h4><a href="<%=request.getContextPath()%>/rentalcategory/select_rentalCategory_page.jsp">回首頁</a></h4>
    </td>
  </tr>
</table>
<c:if test="${pageQty > 0}">
  <b><font color=red>第${currentPage}/${pageQty}頁</font></b>
</c:if>
<ul>
  <li><a href='addRentalCategory.jsp'>新增租借品類別</a></li>
</ul>
<table id="sort-table" data-toggle="table">
  <thead>
  <tr>
    <th data-field="rCatNo" data-sortable="true">租借品類別編號</th>
    <th data-field="rCatName" data-sortable="true">租借品類別名稱</th>
    <th data-field="rStockQty" data-sortable="true">租借品庫存數量</th>
    <th data-field="rRentedQty" data-sortable="true">租借品已租借數量</th>
    <th data-field="rDesPrice" data-sortable="true">押金</th>
    <th data-field="update" data-sortable="true">修改</th>
    <th data-field="Delete" data-sortable="true">刪除</th>
  </tr>
  </thead>
  <c:forEach var="rentalCategoryVO" items="${list}">
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
              <input type="hidden" name="action" value="getOne_For_Update"></FORM>
      </td>
      <td>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" style="margin-bottom: 0px;">
              <input type="submit" value="刪除">
              <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
              <input type="hidden" name="action" value="delete"></FORM>
      </td>
    </tr>
  </c:forEach>
</table>
<c:if test="${currentPage > 1}">
  <a href="${pageContext.request.contextPath}/rentalcategory/rentalCategory.do?action=getAll&page=1">至第一頁</a>&nbsp;
</c:if>
<c:if test="${currentPage - 1 != 0}">
  <a href="${pageContext.request.contextPath}/rentalcategory/rentalCategory.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
</c:if>
<c:if test="${currentPage + 1 <= pageQty}">
  <a href="${pageContext.request.contextPath}/rentalcategory/rentalCategory.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
</c:if>
<c:if test="${currentPage != pageQty}">
  <a href="${pageContext.request.contextPath}/rentalcategory/rentalCategory.do?action=getAll&page=${pageQty}">至最後一頁</a>&nbsp;
</c:if>
<br>
<br>
<br>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
<script src="./Webapp/jquery/jquery-3.7.1.min.js"></script>

<%--  <script>--%>
<%--    $(function(){--%>
<%--      $("button.update").on("click", function(){ &lt;%&ndash;點擊修改按鈕&ndash;%&gt;--%>
<%--        // alert("ok");  //有觸發--%>
<%--        $(location).attr("href","<%=request.getContextPath()%>update_rentalCategory_input.jsp") &lt;%&ndash;移至修改頁面&ndash;%&gt;--%>
<%--      })--%>

<%--      $("button.delete").on("click", function(){ &lt;%&ndash;點擊刪除按鈕&ndash;%&gt;--%>
<%--        // alert("ok");  //有觸發--%>
<%--          $(location).attr("href","<%=request.getContextPath()%>update_rentalCategory_input.jsp")--%>
<%--      })--%>
<%--    });--%>
<%--  </script>--%>
</body>
</html>