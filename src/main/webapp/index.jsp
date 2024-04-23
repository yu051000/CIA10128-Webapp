<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首頁</title>
</head>
<body>
<h1>歡迎來到Fallelove商城！</h1>
<form action="<%=request.getContextPath()%>/rentalcategory/select_rentalCategory_page.jsp" method="get">
    <input type="submit" value="點擊進入">
</form>
</body>
</html>
