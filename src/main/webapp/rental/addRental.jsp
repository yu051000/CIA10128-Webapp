<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rental.model.*"%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>���ɫ~��Ʒs�W - addRental.jsp</title>

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
		<h3>���ɫ~��Ʒs�W</h3></td><td>
		<h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>�s�W��ơG</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="rental.do" name="form1" id="addform1">
	<table>
		<tr>
			<td>���ɫ~�W��:</td>
			<td><input type="TEXT" name="rName" id="rName" value="${param.rName}" size="45"/></td> <td>${errorMsgs.rName}</td>
		</tr>
		<tr>
			<td>���ɫ~���:</td>
			<td><input type="TEXT" name="rPrice" id="rPrice" value="${param.rPrice}"   size="45"/></td> <td>${errorMsgs.rPrice}</td>
		</tr>
		<tr>
			<td>���ɫ~�ؤo:</td>
			<td><input type="TEXT" name="rSize" id="rSize" value="${param.rSize}"   size="45"/></td> <td>${errorMsgs.rSize}</td>
		</tr>
		<tr>
			<td>���ɫ~�C��:</td>
			<td><input type="TEXT" name="rColor" id="rColor" value="${param.rColor}"   size="45"/></td> <td>${errorMsgs.rColor}</td>
		</tr>
		<tr>
			<td>���ɫ~��T:</td>
			<td><input type="TEXT" name="rInfo" id="rInfo" value="${param.rInfo}"   size="45"/></td> <td>${errorMsgs.rInfo}</td>
		</tr>
		<tr>
			<td>���ɫ~���A:</td>
			<td><input type="TEXT" name="rStat" id="rStat" value="${param.rStat}"   size="45"/></td> <td>${errorMsgs.rStat}</td>
		</tr>
		<jsp:useBean id="rentalSvc" scope="page" class="com.rental.model.RentalService" />
		<tr>
			<td>���ɫ~���O�s��:</td>
			<td><input type="TEXT" name="rCatNo" id="rCatNo" value="${param.rCatNo}" size="45"/></td> <td>${errorMsgs.rCatNo}</td>
		</tr>

	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="�e�X�s�W">
</FORM>

	<script src="<%=request.getContextPath()%>/jquery/jquery.js"></script>
	<script>
		$(function (){

			$("#addform1").on("submit", function(event){ //���e�X�AĲ�o"submit"�ƥ�
				event.preventDefault(); //����w�]

				// ���o��檺�ƾ�
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
				document.getElementById('preview').innerHTML = '<span class="text">�w����</span>';
			}
		});
	};
</script>





</body>
</html>