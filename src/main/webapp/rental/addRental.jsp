<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rental.model.*"%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>租借品資料新增 - addRental.jsp</title>

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
		<h3>租借品資料新增</h3></td><td>
		<h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增資料：</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="rental.do" name="form1" id="addform1">
	<table>
		<tr>
			<td>租借品名稱:</td>
			<td><input type="TEXT" name="rName" id="rName" value="${param.rName}" size="45"/></td> <td>${errorMsgs.rName}</td>
		</tr>
		<tr>
			<td>租借品單價:</td>
			<td><input type="TEXT" name="rPrice" id="rPrice" value="${param.rPrice}"   size="45"/></td> <td>${errorMsgs.rPrice}</td>
		</tr>
		<tr>
			<td>租借品尺寸:</td>
			<td><input type="TEXT" name="rSize" id="rSize" value="${param.rSize}"   size="45"/></td> <td>${errorMsgs.rSize}</td>
		</tr>
		<tr>
			<td>租借品顏色:</td>
			<td><input type="TEXT" name="rColor" id="rColor" value="${param.rColor}"   size="45"/></td> <td>${errorMsgs.rColor}</td>
		</tr>
		<tr>
			<td>租借品資訊:</td>
			<td><input type="TEXT" name="rInfo" id="rInfo" value="${param.rInfo}"   size="45"/></td> <td>${errorMsgs.rInfo}</td>
		</tr>
		<tr>
			<td>租借品狀態:</td>
			<td><input type="TEXT" name="rStat" id="rStat" value="${param.rStat}"   size="45"/></td> <td>${errorMsgs.rStat}</td>
		</tr>
		<jsp:useBean id="rentalSvc" scope="page" class="com.rental.model.RentalService" />
		<tr>
			<td>租借品類別編號:</td>
			<td><input type="TEXT" name="rCatNo" id="rCatNo" value="${param.rCatNo}" size="45"/></td> <td>${errorMsgs.rCatNo}</td>
		</tr>

	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</FORM>

	<script src="<%=request.getContextPath()%>/jquery/jquery.js"></script>
	<script>
		$(function (){

			$("#addform1").on("submit", function(event){ //表單送出，觸發"submit"事件
				event.preventDefault(); //停止預設

				// 取得表單的數據
				var rName = $("#rName").val();
				var password = $("#password").val();
			});
		});
	</script>




<script>
	function previewImg(file) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function () {
			var imgStr = '<img src="' + reader.result + '" class="preview_img">';
			document.getElementById('preview').innerHTML = imgStr;
		};
	}

	window.onload = function () {
		document.getElementById('pPic').addEventListener('change', function (e) {
			if (this.files.length > 0) {
				previewImg(this.files[0]);
			} else {
				document.getElementById('preview').innerHTML = '<span class="text">預覽圖</span>';
			}
		});
	};
</script>





</body>
</html>