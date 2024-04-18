<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rentalcategory.model.*"%>

<%
    RentalCategoryVO rentalCategoryVO = (RentalCategoryVO) request.getAttribute("rentalCategoryVO"); //RentalCategoryServlet.java(Controller), �s�Jreq��rentalCategoryVO����
%>

<html>
<head>
    <link rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous"/>
    <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>���ɫ~���O��Ƭd�ߵ��G - listOneRentalCategory.jsp</title>
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
                <h3>�浧�d��</h3>
                <h3>���ɫ~���O���</h3>
                <h4><a href="select_rentalCategory_page.jsp">�^����</a></h4>
            </td>
        </tr>
    </table>
    <ul>
        <li><a href='listAllRentalCategory.jsp'>�d�ߩҦ����ɫ~���O</a><br></li>
    </ul>
    <table>
        <tr>
            <th>���ɫ~���O�s��</th>
            <th>���ɫ~���O�W��</th>
            <th>���ɫ~�w�s�ƶq</th>
            <th>���ɫ~�w���ɼƶq</th>
            <th>���</th>
            <th>�ק�</th>
            <th>�R��</th>
        </tr>
        <tr>
            <td>${rentalCategoryVO.rCatNo}</td>
            <td>${rentalCategoryVO.rCatName}</td>
            <td>${rentalCategoryVO.rStockQty}</td>
            <td>${rentalCategoryVO.rRentedQty}</td>
            <td>${rentalCategoryVO.rDesPrice}</td>
            <td>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" style="margin-bottom: 0px;">
                    <input type="submit" value="�ק�">
                    <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
                    <input type="hidden" name="action" value="getOne_For_Update">
                </FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" style="margin-bottom: 0px;">
                    <input type="submit" value="�R��">
                    <input type="hidden" name="rCatNo" value="${rentalCategoryVO.rCatNo}">
                    <input type="hidden" name="action" value="delete">
                </FORM>
            </td>
        </tr>
    </table>
    <script src="./vendors/jquery/jquery-3.7.1.min.js"></script>
    <script>
        $(function(){
            $("button.update").on("click", function(){ <%--�I���ק���s--%>
                // alert("�ק���sok");
                <%--�ϥ�jQuery���ݩʴ�����k�ಾ����--%>
                $(location).attr("href","<%=request.getContextPath()%>select_rentalCategory_page.jsp")
            })

            $("button.delete").on("click", function(){ <%--�I���R�����s--%>
                // alert("�R�����sok");
                <%--�ϥ�jQuery���ݩʴ�����k�ಾ����--%>
                $(location).attr("href","<%=request.getContextPath()%>select_rentalCategory_page.jsp")
            })
        });
    </script>
</body>
</html>

