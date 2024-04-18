<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>�d�߯��ɫ~���O</title>

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
                <h3>���ɫ~���OHome</h3><h4>( MVC )</h4>
            </td>
        </tr>
    </table>

    <h3>��Ƭd��:</h3>

    <%-- ���~��C --%>
     <c:if test="${not empty errorMsgs}">
        <font style="color:red">�Эץ��H�U���~:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message.value}</li>
            </c:forEach>
        </ul>
     </c:if>

    <ul>
        <li><a href='listAllRentalCategory.jsp'>�d�ߩҦ����ɫ~���O</a><br><br></li>
        <li><a href='addRentalCategory.jsp'>�s�W���ɫ~���O</a><br><br></li>

        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data"><%--�]�m���MIME�s�X--%>
                <b>��J���ɫ~���O�s�� (�p�G1):</b>
                <input type="text" name="rCatNo" value="${param.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="�e�X">
            </FORM>
        </li>

        <jsp:useBean id="rentalCategorySvc" scope="page" class="com.rentalcategory.model.RentalCategoryService"/><%--�����n�ϥΤ@�� bean ����--%>
        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data">
                <b>��ܯ��ɫ~���O�s��:</b>
                <select size="1" name="rCatNo">
<%--                    <option value="default">�п�ܽs��</option>--%>
                <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> <%--���N�ާ@--%>
                    <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}
                </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="�e�X">
            </FORM>
        </li>

        <li>
            <FORM METHOD="post" ACTION="rentalCategory.do" enctype="multipart/form-data">
                <b>��ܯ��ɫ~���O�W��:</b>
                <select size="1" name="rCatName">
<%--                    <option value="default">�п�ܦW��</option>--%>
                    <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> <%--���N�ާ@--%>
                        <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatName}
                    </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="�e�X">
            </FORM>
        </li>
    </ul>
</body>
</html>