package com.ni.rentalcategory.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import com.google.gson.Gson;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.ni.rentalcategory.service.RentalCategoryService;
import com.ni.rentalcategory.service.RentalCategoryServiceImpl;
import com.ni.rentalcategory.service.RentalCategoryService_Interface;
import com.ni.rentalcategory.vo.RentalCategoryVO;

@WebServlet("/rentalcategory/rentalCategory.do")
@MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024) //限制大小
public class RentalCategoryServlet  extends HttpServlet {

    // 一個 servlet 實體對應一個 service 實體
    private RentalCategoryServlet rentalCategoryServlet;

    @Override
    public void init() throws ServletException {
        rentalCategoryServlet = new RentalCategoryServlet();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); //如果沒有filter過濾器統一宣告就要打
        res.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("rCatNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.put("rCatNo","請輸入編號");
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
                errorMsgs.put("rCatNo", "編號格式不正確");
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
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/select_rentalCategory_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫取出的rentalCategoryVO物件,存入req
            req.setAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.jsp的第125行 -->
//				String url = "/rentalcategory/listOneRentalCategory.jsp";    // 成功轉交 listOneEmp.jsp
            String url = "/rentalcategory/select_rentalCategory_page.jsp";   // 查詢完成後轉交select_page.jsp由其include file="listOneEmp-div.fragment"
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

/////////////////////////////////////////////////////////////////////////////////////////
        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

            /***************************2.開始查詢資料****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            RentalCategoryVO rentalCategoryVO = rentalCategorySvc.getOneRentalCategory(rCatNo);
            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫取出的productVO物件,存入req
            String url = "/rentalcategory/update_rentalCategory_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }
////////////////////////////////////////////////////////////////////////////////////
        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());

            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());
            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());

            BigDecimal rDesPrice = null;
            try {
                String rDesPriceStr = req.getParameter("rDesPrice").trim();
                if (rDesPriceStr != null && !rDesPriceStr.isEmpty()) {
                    rDesPrice = new BigDecimal(rDesPriceStr);
                }
            } catch (NumberFormatException e) {
                errorMsgs.put("rDesPrice","押金：請填數字");
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/update_rentalCategory_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            RentalCategoryVO rentalCategoryVO = rentalCategorySvc.updateRentalCategory(rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("success", "- (修改成功)");
            req.setAttribute("rentalCategoryVO", rentalCategoryVO); // 資料庫update成功後,正確的的rentalCategoryVO物件,存入req
            String url = "/rentalcategory/listOneRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }


////////////////////////////////////////////////////////////////////////////////////
        if ("insert".equals(action)) { // 來自addRentalCategory.jsp的請求

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());

            String rCatName = req.getParameter("rCatName");
            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (rCatName == null || rCatName.trim().length() == 0) {
                errorMsgs.put("rCatName","請勿空白");
            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("rCatName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());
            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());

            BigDecimal rDesPrice = null;
            try {
                String rDesPriceStr = req.getParameter("rDesPrice").trim();
                if (rDesPriceStr != null && !rDesPriceStr.isEmpty()) {
                    rDesPrice = new BigDecimal(rDesPriceStr);
                }
            } catch (NumberFormatException e) {
                errorMsgs.put("rDesPrice","押金：請填數字");
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                errorMsgs.put("Exception","修改資料失敗:---------------");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/rentalcategory/addRentalCategory.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            rentalCategorySvc.addRentalCategory(rCatName,rStockQty,rRentedQty,rDesPrice);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("success", "- (新增成功)");
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmp.jsp
            successView.forward(req, res);
        }
////////////////////////////////////////////////////////////////////////////////////
        if ("delete".equals(action)) { // 來自listAllRentalCategory.jsp

            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

            /***************************2.開始刪除資料***************************************/
            RentalCategoryService rentalCategorySVC = new RentalCategoryService();
            rentalCategorySVC.deleteRentalCategory(rCatNo); // 刪除rCatNo

            /***************************3.刪除完成,準備轉交***********************************/
            req.setAttribute("success", "- (刪除成功)");
            String url = "/rentalcategory/listAllRentalCategory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);  // 刪除成功後,轉交回送出刪除的來源網頁
        }
        if ("getRentalCategoryDetails".equals(action)) {

            /*************************** 1.接收請求參數 ****************************************/
            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));
            /***************************2.開始查詢資料*****************************************/
            // 根據編號獲取詳細資訊
            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
            RentalCategoryVO rentalCategoryVO = rentalCategorySvc.getOneRentalCategory(rCatNo);

            if (rentalCategoryVO != null) {
                // 將詳細資訊轉換成JSON格式
                Gson gson = new Gson();
                String json = gson.toJson(rentalCategoryVO);

                // 設置響應類型為JSON
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                // 將JSON數據發送至客戶端
                res.getWriter().write(json);
            } else {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }



//            /*// 限定下拉式選單顯示的選項
//             * 【 第二種作法 】 Method used to populate the Map Data in view.
//             *  如 : <form:select path="deptno" id="deptno" items="${mapData}" />
//             */
//            @ModelAttribute("mapData") //
//            protected Map<Integer, String> referenceMapData() {
//                Map<Integer, String> mapData = new LinkedHashMap<Integer, String>();
//                mapData.put(10, "財務部");
//                mapData.put(20, "研發部");
//                mapData.put(30, "業務部");
//                mapData.put(40, "生管部");
//                return mapData;
//            }
//
//
//            /*// 依據表格內資料可做下拉式選單
////             * 第三種作法 Method used to populate the List Data in view.
////             *  如 : <form:select path="deptno" id="deptno" items="${listData}" itemValue="deptno" itemLabel="dname" />
////             */
//            @ModelAttribute("listData")
//            protected List<DeptVO> referenceListData() {
//                DeptService deptSvc = new DeptService();
//                List<DeptVO> listData = deptSvc.getAll();
//                return listData;
//            }
        }
    }
}

