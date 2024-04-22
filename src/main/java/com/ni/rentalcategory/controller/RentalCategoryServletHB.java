package com.ni.rentalcategory.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import com.google.gson.Gson;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ni.rentalcategory.service.RentalCategoryService;
import com.ni.rentalcategory.service.RentalCategoryServiceImpl;
import com.ni.rentalcategory.service.RentalCategoryService_Interface;
import com.ni.rentalcategory.vo.RentalCategoryVO;
import com.ni.util.Constants;

    @WebServlet("/rentalcategory/rentalCategory.do")
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
            req.setCharacterEncoding("UTF-8");  //如果沒有filter過濾器統一宣告就要打
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
                    forwardPath = "/index.jsp";
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
            dispatcher.forward(req, res);
        }

        private String insert(HttpServletRequest req, HttpServletResponse res) {
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
//
//            String rCatName = req.getParameter("rCatName");
//            String rCatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//            if (rCatName == null || rCatName.trim().length() == 0) {
//                errorMsgs.put("rCatName","請勿空白");
//            } else if(!rCatName.trim().matches(rCatNameReg)) { //以下練習正則(規)表示式(regular-expression)
//                errorMsgs.put("rCatName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//            }
//
//            Integer rStockQty = Integer.valueOf(req.getParameter("rStockQty").trim());
//            Integer rRentedQty = Integer.valueOf(req.getParameter("rRentedQty").trim());
//
//            BigDecimal rDesPrice = null;
//            try {
//                String rDesPriceStr = req.getParameter("rDesPrice").trim();
//                if (rDesPriceStr != null && !rDesPriceStr.isEmpty()) {
//                    rDesPrice = new BigDecimal(rDesPriceStr);
//                }
//            } catch (NumberFormatException e) {
//                errorMsgs.put("rDesPrice","押金：請填數字");
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                errorMsgs.put("Exception","修改資料失敗:---------------");
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rentalcategory/addRentalCategory.jsp");
//                failureView.forward(req, res);
//                return; //程式中斷
//            }
//
//            /***************************2.開始修改資料*****************************************/
//            RentalCategoryService rentalCategorySvc = new RentalCategoryService();
//            rentalCategorySvc.addRentalCategory(rCatName,rStockQty,rRentedQty,rDesPrice);
//
//            /***************************3.修改完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("success", "- (新增成功)");
//            String url = "/rentalcategory/listAllRentalCategory.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmp.jsp
//            successView.forward(req, res);
//            return rCatName;
//        }
            return null;
        }




        private String getAllRentalCats(HttpServletRequest req, HttpServletResponse res) {
            String page = req.getParameter("page");
            int currentPage = (page == null) ? 1 : Integer.parseInt(page);

            List<RentalCategoryVO> rentalCatList = rentalCategoryServiceImpl.getAllRentalCats(currentPage);

            if (req.getSession().getAttribute("empPageQty") == null) {
                int empPageQty = rentalCategoryServiceImpl.getPageTotal();
                req.getSession().setAttribute("empPageQty", empPageQty);
            }

            req.setAttribute("rentalCatList", rentalCatList);
            req.setAttribute("currentPage", currentPage);

            return "/rentalcategory/listAllRentalCategory.jsp";
        }

        private String getByCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
            Map<String, String[]> map = req.getParameterMap();

            if (map != null) {
                List<RentalCategoryVO> list = rentalCategoryServiceImpl.getByCompositeQuery(map);
                req.setAttribute("list", list);
            } else {
                return "/index.jsp";
            }
            return "/rentalcategory/listCompositeQueryRentalCats.jsp";
        }



    }

