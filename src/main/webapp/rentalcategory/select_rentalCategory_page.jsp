<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>首頁 - 查詢租借品類別</title>

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

    <!-- Css Styles -->
    <link rel="stylesheet" href="../shop/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../shop/css/style.css" type="text/css">
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
        <li><a href='<%=request.getContextPath()%>/rentalcategory/listAllRentalCategory.jsp'>查詢所有租借品類別</a><br><br></li>
        <li><a href='<%=request.getContextPath()%>/rentalcategory/addRentalCategory.jsp'>新增租借品類別</a><br><br></li>

        <jsp:useBean id="rentalCategorySvc" scope="page" class="com.yu.rentalcategory.service.RentalCategoryServiceImpl"/><%-- bean 物件--%>

<%--        <li>--%>
<%--            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" enctype="multipart/form-data">&lt;%&ndash;傳入伺服器的資料為檔案上傳&ndash;%&gt;--%>
<%--                <b>輸入租借品類別編號 (如：1):</b>--%>
<%--                <input type="text" name="rCatNo" value="${param.rCatNo}"><font color=red>${errorMsgs.rCatNo}</font>--%>
<%--                <input type="hidden" name="action" value="getOne_For_Display">--%>
<%--                <input type="submit" value="送出">--%>
<%--            </FORM>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" enctype="multipart/form-data">--%>
<%--                <b>選擇租借品類別編號:</b>--%>
<%--                <select size="1" name="rCatNo">--%>
<%--                <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}">--%>
<%--                    <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}--%>
<%--                </c:forEach>--%>
<%--                </select>--%>
<%--                <input type="hidden" name="action" value="getOne_For_Display">--%>
<%--                <input type="submit" value="送出">--%>
<%--            </FORM>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" enctype="multipart/form-data">--%>
<%--                <b>選擇租借品類別名稱:</b>--%>
<%--                <select size="1" name="rCatName">--%>
<%--                    <option value="default">請選擇名稱</option>--%>
<%--                    <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> &lt;%&ndash;迭代操作&ndash;%&gt;--%>
<%--                        <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatName}--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--                <input type="hidden" name="action" value="getOne_For_Display">--%>
<%--                <input type="submit" value="送出">--%>
<%--            </FORM>--%>
<%--        </li>--%>
    </ul>
    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="shop__sidebar">
                        <div class="shop__sidebar__search">
                            <form action="#">
                                <input type="text" placeholder="Search...">
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        <div class="shop__sidebar__accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card">
                                    <div class="card-heading">
                                        <a data-toggle="collapse" data-target="#collapseOne">租借品類別</a>
                                    </div>
                                    <!--                   租借品類別篩選                                                --->
                                    <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <div class="shop__sidebar__categories">
                                                <ul class="nice-scroll">
                                                    <li>
                                                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" enctype="multipart/form-data">
                                                            <b>選擇租借品類別編號:</b>
                                                            <select size="1" name="rCatNo">
                                                                <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}">
                                                                <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatNo}
                                                                    </c:forEach>
                                                            </select>
                                                            <input type="hidden" name="action" value="getOne_For_Display">
<%--                                                            <input type="submit" value="送出">--%>
                                                        </FORM>
                                                    </li>
                                                    <li>
                                                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentalcategory/rentalCategory.do" enctype="multipart/form-data">
                                                            <b>選擇租借品類別名稱:</b>
                                                            <select size="1" name="rCatName">
                                                                <option value="default">請選擇名稱</option>
                                                                <c:forEach var="rentalCategoryVO" items="${rentalCategorySvc.all}"> <%--迭代操作--%>
                                                                <option value="${rentalCategoryVO.rCatNo}">${rentalCategoryVO.rCatName}
                                                                    </c:forEach>
                                                            </select>
<%--                                                            <input type="hidden" name="action" value="getOne_For_Display">--%>
<%--                                                            <input type="submit" value="送出">--%>
                                                        </FORM>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="shop__product__option">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__left">
                                    <p>可輸入文字</p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__right">
                                    <p>選擇排序:</p>
                                    <select>
                                        <option value="">最新</option>
                                        <option value="">最熱門</option>
                                        <option value="">價格由低到高</option>
                                        <option value="">價格由高到低</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="../shop/img/product/product-2.jpg">
                                    <ul class="product__hover">
                                        <li><a href="#"><img src="../shop/img/icon/heart.png" alt=""></a></li><%-- 添加我的最愛 --%>
                                    </ul>
                                </div>
                                <div class="product__item__text"> <!--商品資訊，個別顯示區域-->
                                    <h6>西裝</h6>
                                    <a href="#" class="add-cart"> + 加入購物車</a>
                                    <div class="rating">
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <h5>$67.24</h5>
                                    <div class="product__color__select">
                                        <label for="pc-4">
                                            <input type="radio" id="pc-4"> <%-- 選擇顏色 --%>
                                        </label>
                                        <label class="active black" for="pc-5"><%-- 選擇顏色 --%>
                                            <input type="radio" id="pc-5">
                                        </label>
                                        <label class="grey" for="pc-6">
                                            <input type="radio" id="pc-6"><%-- 選擇顏色 --%>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- Js Plugins -->
<script src="../shop/js/jquery-3.3.1.min.js"></script>
<script src="../shop/js/bootstrap.min.js"></script>
<script src="../shop/js/jquery.nice-select.min.js"></script>
<script src="../shop/js/jquery.nicescroll.min.js"></script>
<script src="../shop/js/jquery.magnific-popup.min.js"></script>
<script src="../shop/js/jquery.countdown.min.js"></script>
<script src="../shop/js/jquery.slicknav.js"></script>
<script src="../shop/js/mixitup.min.js"></script>
<script src="../shop/js/owl.carousel.min.js"></script>
<script src="../shop/js/main.js"></script>
</body>
</html>