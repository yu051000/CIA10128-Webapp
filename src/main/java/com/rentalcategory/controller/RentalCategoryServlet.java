package com.rentalcategory.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.rentalcategory.model.*;
import static java.lang.System.out;

@WebServlet("/rentalCategory.do")
@MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024)

public class RentalCategoryServlet  extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
//======================================================================================================================== "insert"
        if ("insert".equals(action)) { // 來自addRentalCategory.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","租借品類別名稱：請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","租借品類別名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());

            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());

            BigDecimal rDesPrice = null;
            try {
                String rDesPriceStr = req.getParameter("rDesPrice").trim();
            } catch (NumberFormatException e) {
                rDesPrice = BigDecimal.ZERO;
                errorMsgs.put("rDesPrice","租借品類別單價：請填數字");
            }

            RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
            rentalCategoryVO.setrCatName(rCatName);
            rentalCategoryVO.setrStockQty(rStockQty);
            rentalCategoryVO.setrRentedQty(rRentedQty);
            rentalCategoryVO.setrDesPrice(rDesPrice);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("rentalCategoryVO", rentalCategoryVO);
                String failurl = "/rentalcategory/update_rentalCategory_input.jsp" ;
                RequestDispatcher failureView = req.getRequestDispatcher(failurl);
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始新增資料***************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            rentalCategorySvc.addRentalCategory(rCatName, rStockQty, rRentedQty, rDesPrice);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            req.setAttribute("rentalCategoryVO", rentalCategoryVO);
            out.println("新增成功!");
        }
//======================================================================================================================== "delete"
//======================================================================================================================== 待處理
 if ("delete".equals(action)) { // 來自listAllRentalCategory.jsp

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

            /***************************2.開始刪除資料***************************************/
            RentalCategoryService rentalCategorySVC = new RentalCategoryService();
            rentalCategorySVC.deleteRentalCategory(rCatNo);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
//======================================================================================================================== "update"
        if ("update".equals(action)) { // 來自update_rentalCategory_input.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());

            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","租借品類別名稱：請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","租借品類別名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());

            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());

            BigDecimal rDesPrice = null;
            try {
                String rDesPriceStr = req.getParameter("rDesPrice").trim();
                rDesPrice = new BigDecimal(rDesPriceStr);
            } catch (NumberFormatException e) {
                rDesPrice = BigDecimal.ZERO;
                errorMsgs.put("rDesPrice","租借品類別單價：請填數字");
            }

            RentalCategoryVO rentalCategoryVO = new RentalCategoryVO();
            rentalCategoryVO.setrCatNo(rCatNo);
            rentalCategoryVO.setrCatName(rCatName);
            rentalCategoryVO.setrStockQty(rStockQty);
            rentalCategoryVO.setrRentedQty(rRentedQty);
            rentalCategoryVO.setrDesPrice(rDesPrice);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("rentalCategoryVO", rentalCategoryVO);
                String failurl = "/rentalcategory/update_rentalCategory_input.jsp" ;
                RequestDispatcher failureView = req.getRequestDispatcher(failurl);
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            rentalCategoryVO = rentalCategorySvc.updateRentalCategory(rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫update成功後,正確的的rentalCategoryVO物件,存入req
            out.println("修改成功!");
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllRentalCategory.jsp
            successView.forward(req, res);
        }
//======================================================================================================================== "getAll"
//======================================================================================================================== 待處理
        if ("getAll".equals(action)) { // 來自select_page.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************2.開始查詢資料*****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            List<RentalCategoryVO> rentalCategoryVOList = rentalCategorySvc.getAll();

            if (rentalCategoryVOList.isEmpty()) {
                errorMsgs.put("result", "查無資料"); // 如果結果為空，設置錯誤消息
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/rentalcategory/select_rentalCategory_page.jsp");
                failureView.forward(req, res);
                return; // 程序中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalCategoryVOList", rentalCategoryVOList); // 將查詢到的通知消息列表存入請求屬性中
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉發到 listAllRentalCategory.jsp 頁面
            successView.forward(req, res);
        }


//======================================================================================================================== "getOne_For_Display"
//======================================================================================================================== 待處理
        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("rCatNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.put("rCatNo", "請輸入租借品類別編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/select_rentalCategory_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            Integer rCatNo = null;
            try {
                rCatNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.put("rCatNo","租借品類別編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/select_rentalCategory_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            RentalCategoryVO rentalCategoryVO = rentalCategorySvc.getOneRentalCategory(rCatNo);
            if (rentalCategoryVO == null) {
                errorMsgs.put("rCatNo","查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("rentalCategoryVO", rentalCategoryVO);
                String failurl = "/rentalcategory/update_rentalCategory_input.jsp" ;
                RequestDispatcher failureView = req.getRequestDispatcher(failurl);
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫取出的rentalCategoryVO物件,存入req
            String url = "/rentalcategory/listOneRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRentalCategory.jsp
            successView.forward(req, res);
        }

//======================================================================================================================== "getOne_For_Update"
        if ("getOne_For_Update".equals(action)) { // 來自listAllRentalCategory.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

            /***************************2.開始查詢資料****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            RentalCategoryVO rentalCategoryVO = rentalCategorySvc.getOneRentalCategory(rCatNo);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("rentalCategoryVO",rentalCategoryVO);
            String url = "/rentalcategory/update_rentalCategory_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rentalCategory_input.jsp
            successView.forward(req, res);
        }
    }
}