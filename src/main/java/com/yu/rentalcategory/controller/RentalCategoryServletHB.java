package com.yu.rentalcategory.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.yu.rentalcategory.service.RentalCategoryServiceImpl;
import com.yu.rentalcategory.model.RentalCategoryVO;

    @MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024) //限制大小
    public class RentalCategoryServletHB extends HttpServlet {

        // 一個 servlet 實體對應一個 service 實體
        private RentalCategoryServiceImpl rentalCategoryServiceImpl;

        @Override
        public void init() throws ServletException {
            rentalCategoryServiceImpl = new RentalCategoryServiceImpl();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            doPost(req, res);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//            req.setCharacterEncoding("UTF-8");  //如果沒有filter過濾器統一宣告就要打
            String action = req.getParameter("action");
            res.setContentType("text/html; charset=UTF-8");

            // 依據action判斷
            String forwardPath = "";
            switch (action) {
                case "getAll":
                    forwardPath = getAllRentalCats(req, res);
                    break;
                case "compositeQuery":
                    forwardPath = getByCompositeQuery(req, res);
                    break;
                default:
                    forwardPath = "/index.jsp"; //預設進入首頁
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
            dispatcher.forward(req, res);
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// insert
        private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

            //設定錯誤處理訊息
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
            Integer rCatNo = null;
            try {
                rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請勿空白");
            }

            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = null;
            try {
                rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rStockQty", "租借品庫存數量 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rStockQty", "租借品庫存數量 : 請勿空白");
            }

            Integer rRentedQty = null;
            try {
                rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rRentedQty", "租借品已租借數量 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rRentedQty", "租借品已租借數量 : 請勿空白");
            }

            BigDecimal rDesPrice = null;
            try {
                rDesPrice = new BigDecimal(req.getParameter("rDesPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rDesPrice","押金：請填數字");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rDesPrice", "押金 : 請勿空白");
            }

            // 如果有錯誤，將錯誤處理訊息傳回表單
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                // 將錯誤訊息存入request中
                req.setAttribute("errorMsgs", errorMsgs);
                // 將參數存入request中，方便表單顯示
                req.setAttribute("rCatNo", rCatNo);
                req.setAttribute("rCatName", rCatName);
                req.setAttribute("rStockQty", rStockQty);
                req.setAttribute("rRentedQty", rRentedQty);
                req.setAttribute("rDesPrice", rDesPrice);

                // 請求轉發回原始頁面add
                RequestDispatcher failureView = req.getRequestDispatcher("/rentalcategory/addRentalCategory.jsp");
                failureView.forward(req, res);

            }else{
                // ------------------ 2.開始修改資料 ------------------ //
                rentalCategoryServiceImpl.addRentalCat(rCatName,rStockQty,rRentedQty,rDesPrice);
                // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
                String url = "/rentalcategory/listAllRentalCategory.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmp.jsp
                successView.forward(req, res);
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// update
        private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
            //設定錯誤處理訊息
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
//            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
            Integer rCatNo = null;
            try {
                rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 不能空白!");
            }

            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = null;
            try {
                rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rStockQty", "租借品庫存數量 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rStockQty", "租借品庫存數量 : 請勿空白");
            }

            Integer rRentedQty = null;
            try {
                rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rRentedQty", "租借品已租借數量 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rRentedQty", "租借品已租借數量 : 請勿空白");
            }

            BigDecimal rDesPrice = null;
            try {
                rDesPrice = new BigDecimal(req.getParameter("rDesPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rDesPrice","押金：請填數字");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rDesPrice", "押金 : 請勿空白");
            }

            // 如果有錯誤，將錯誤處理訊息傳回表單
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/update_rentalCategory_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷

            }else{
                // ------------------ 2.開始修改資料 ------------------ //
                RentalCategoryVO rentalCategoryVO = rentalCategoryServiceImpl.updateRentalCat(rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice);

                // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
                req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫update成功後,正確的的rentalCategoryVO物件,存入req
                String url = "/rentalcategory/listOneRentalCategory.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
                successView.forward(req, res);
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// delete
        private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
            //設定錯誤處理訊息
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
            Integer rCatNo = null;
            try {
                rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 不能空白!");
            }
            // ------------------ 2.開始刪除資料 ------------------ //
            rentalCategoryServiceImpl.deleteRentalCat(rCatNo);

            // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);  // 刪除成功後,轉交回送出刪除的來源網頁
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getOneUpdate
        private void getOneUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
            //設定錯誤處理訊息
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

//            String rCatName = req.getParameter("rCatName");
//
//            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty"));
//
//            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty"));
//
//            BigDecimal rDesPrice = new BigDecimal(req.getParameter("rDesPrice"));

            // ------------------ 2.開始查詢資料 ------------------ //
            RentalCategoryVO rentalCategoryVO = rentalCategoryServiceImpl.getOneRentalCat(rCatNo);
            // ------------------ 3.查詢完成,準備轉交(Send the Success view) ------------------ //
//            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫取出的productVO物件,存入req
            String url = "/rentalcategory/update_rentalCategory_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getOneDisplay
        private void getOneDisplay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
            //設定錯誤處理訊息
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
            Integer rCatNo = null;
            try {
                rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rCatNo", "租借品類別編號 : 請勿空白");
            }

            // 如果有錯誤，將錯誤處理訊息傳回表單
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/select_rentalCategory_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷

            }else{
            // ------------------ 2.開始查詢資料 ------------------ //
                RentalCategoryVO rentalCategoryVO = rentalCategoryServiceImpl.getOneRentalCat(rCatNo);
            // ------------------ 3.查詢完成,準備轉交(Send the Success view) ------------------ //
                req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫取出的rentalCategoryVO物件,存入req
                req.setAttribute("getOne_For_Display", "true");
				String url = "/rentalcategory/listOneRentalCategory.jsp";    // 成功轉交 listOneEmp.jsp
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAllRentalCats
        //查詢當前頁面
        private String getAllRentalCats(HttpServletRequest req, HttpServletResponse res) {
            String page = req.getParameter("page"); //取得參數(當前頁面)
            int currentPage = (page == null) ? 1 : Integer.parseInt(page); //如果該參數為空，則將當前頁面設置為 1；否則將其轉換為整數。

            //呼叫ServiceImpl的getByCompositeQuery方法設定分頁，將結果存儲在list中
            List<RentalCategoryVO> rentalCatList = rentalCategoryServiceImpl.getAllRentalCats(currentPage); //呼叫ServiceImpl的檢索租借分類列表

            //如果在請求的會話中沒有名為 "rentalCatPageQty" 的屬性
            if (req.getSession().getAttribute("rentalCatPageQty") == null) {

                //使用 RentalCategoryServiceImpl 的方法獲取總頁數（Page Total），並將其設置到會話屬性中
                int rentalCatPageQty = rentalCategoryServiceImpl.getPageTotal();
                req.getSession().setAttribute("rentalCatPageQty", rentalCatPageQty);
            }

            // 將獲取到的租借分類列表和當前頁面設置到請求屬性中
            req.setAttribute("rentalCatList", rentalCatList);
            req.setAttribute("currentPage", currentPage);

            return "/rentalcategory/listAllRentalCategory.jsp"; //返回查看所有租借品的頁面
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByCompositeQuery
        //複合查詢
        private String getByCompositeQuery(HttpServletRequest req, HttpServletResponse                                                                                                           res) {
            Map<String, String[]> map = req.getParameterMap(); // 獲取參數映射（Parameter Map）

            if (map != null) {
                //呼叫ServiceImpl的getByCompositeQuery方法執行複合查詢，將結果存儲在list中
                List<RentalCategoryVO> list = rentalCategoryServiceImpl.getByCompositeQuery(map);
                req.setAttribute("list", list);

            } else { //如果為空(沒有參數傳遞)
                return "/index.jsp"; //返回到首頁
            }

            //無論是否進行了複合查詢，該方法都會將結果存儲在請求屬性中，並返回指定頁面
            return "/rentalcategory/listCompositeQueryRentalCats.jsp";
        }

    }

