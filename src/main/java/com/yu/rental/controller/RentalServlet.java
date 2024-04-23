//package com.yu.rental.controller;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.util.*;
//import javax.servlet.*;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.*;
//
////import com.ni.rental.service.RentalService;
////import com.ni.rental.vo.RentalVO;
//
//import static java.lang.System.out;
//
//@MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024)
//public class RentalServlet extends HttpServlet {
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        doPost(req, res);
//    }
//
//    public void doPost(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//
//        req.setCharacterEncoding("UTF-8");
//        res.setContentType("text/html; charset=UTF-8");
//        String action = req.getParameter("action");
////======================================================================================================================== "insert"
//        if ("insert".equals(action)) { // 來自addRental.jsp的請求
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//            String rName = req.getParameter("rName");
//            String rNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//            if (rName == null || rName.trim().length() == 0) {
//                errorMsgs.put("rName","租借品名稱：請勿空白");
//            } else if(!rName.trim().matches(rNameReg)) { //以下練習正則(規)表示式(regular-expression)
//                errorMsgs.put("rName","租借品名稱: 只能是中、英文字母、數字和_");
//            }
//
//            BigDecimal rPrice = null;
//            try {
//                String rPriceStr = req.getParameter("rPrice").trim();
//            } catch (NumberFormatException e) {
//                rPrice = BigDecimal.ZERO;
//                errorMsgs.put("rPrice","租借品單價：請填數字");
//            }
//
//            Integer rSize = Integer.valueOf(req.getParameter("rSize").trim());
//
//            String rColor = req.getParameter("rColor");
//            if (rColor == null || rColor.trim().length() == 0) {
//                errorMsgs.put("rColor","租借品顏色：請勿空白");
//            }
//
//            String rInfo = req.getParameter("rInfo");
//            if (rInfo == null || rInfo.trim().length() == 0) {
//                errorMsgs.put("rInfo","租借品資訊：請勿空白");
//            }
//
//            Byte rStat = null;
//            String rStatString = req.getParameter("rStat");
//            if (rStatString == null || rStatString.trim().isEmpty()) {
//                errorMsgs.put("rStat","租借品狀態：請勿空白");
//            } else {
//                try {
//                    rStat = Byte.parseByte(rStatString);
//                } catch (NumberFormatException e) {
//                    errorMsgs.put("rStat","租借品狀態：請輸入0~5的數字");
//                }
//            }
//
//            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
//
//            RentalVO rentalVO = new RentalVO();
//            rentalVO.setrName(rName);
//            rentalVO.setrPrice(rPrice);
//            rentalVO.setrSize(rSize);
//            rentalVO.setrColor(rColor);
//            rentalVO.setrInfo(rInfo);
//            rentalVO.setrStat(rStat);
////            rentalVO.setrCatNo(rCatNo);
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                req.setAttribute("rentalVO", rentalVO); // 資料庫update成功後,正確的的RentalVO物件,存入req
//                String failurl = "/rental/update_rental_input.jsp" ;
//                RequestDispatcher failureView = req.getRequestDispatcher(failurl);
//                failureView.forward(req, res);
//                return; //程式中斷
//            }
//
//            /***************************2.開始新增資料***************************************/
//            RentalService rentalSvc = new RentalService();
//            rentalSvc.addRental(rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo);
//
//            /***************************3.新增完成,準備轉交(Send the Success view)***********/
//            req.setAttribute("rentalVO", rentalVO); // 資料庫update成功後,正確的的RentalVO物件,存入req
//            out.println("新增成功!");
////            String url = "/rental/listAllRental.jsp";
////            RequestDispatcher successView = req.getRequestDispatcher(url);
////            successView.forward(req, res);// 新增成功後轉交listAllRental.jsp，跳轉網頁
//        }
//
////======================================================================================================================== "delete"
////======================================================================================================================== 待處理
//        if ("delete".equals(action)) { // 來自listAllRental.jsp
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數***************************************/
//            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
//
//            /***************************2.開始刪除資料***************************************/
//            RentalService rentalSvc = new RentalService();
//            rentalSvc.deleteRental(rNo);
//
//            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
//            String url = "/rental/listAllRental.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//            successView.forward(req, res);
//        }
//
////======================================================================================================================== "update"
//        if ("update".equals(action)) { // 來自update_rental_input.jsp的請求
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            Integer rNo = Integer.valueOf(req.getParameter("rNo").trim());
//
//            String rName = req.getParameter("rName");
//            String rNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//            if (rName == null || rName.trim().length() == 0) {
//                errorMsgs.put("rName","租借品名稱：請勿空白");
//            } else if(!rName.trim().matches(rNameReg)) { //以下練習正則(規)表示式(regular-expression)
//                errorMsgs.put("rName","租借品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//            }
//
//            BigDecimal rPrice = null;
//            try {
//                String rPriceStr = req.getParameter("rPrice").trim();
//                rPrice = new BigDecimal(rPriceStr);
//            } catch (NumberFormatException e) {
//                rPrice = BigDecimal.ZERO;
//                errorMsgs.put("rPrice","租借品單價：請填數字");
//            }
//
//            Integer rSize = Integer.valueOf(req.getParameter("rSize").trim());
//
//            String rColor = req.getParameter("rColor");
//            if (rColor == null || rColor.trim().length() == 0) {
//                errorMsgs.put("rColor","租借品顏色：請勿空白");
//            }
//
//            String rInfo = req.getParameter("rInfo");
//            if (rInfo == null || rInfo.trim().length() == 0) {
//                errorMsgs.put("rInfo","租借品資訊：請勿空白");
//            }
//
//            Byte rStat = null;
//            String rStatString = req.getParameter("rStat");
//            if (rStatString == null || rStatString.trim().isEmpty()) {
//                errorMsgs.put("rStat","租借品狀態：請勿空白");
//            } else {
//                try {
//                    rStat = Byte.parseByte(rStatString);
//                } catch (NumberFormatException e) {
//                    errorMsgs.put("rStat","租借品狀態：請輸入0~5的數字");
//                }
//            }
//
//            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
//
//            RentalVO rentalVO = new RentalVO();
//            rentalVO.setrNo(rNo);
//            rentalVO.setrName(rName);
//            rentalVO.setrPrice(rPrice);
//            rentalVO.setrSize(rSize);
//            rentalVO.setrColor(rColor);
//            rentalVO.setrInfo(rInfo);
//            rentalVO.setrStat(rStat);
////            rentalVO.setrCatNo(rCatNo);
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                req.setAttribute("rentalVO", rentalVO); // 資料庫update成功後,正確的的RentalVO物件,存入req
//                String failurl = "/rental/update_rental_input.jsp" ;
//                RequestDispatcher failureView = req.getRequestDispatcher(failurl);
//                failureView.forward(req, res);
//                return; //程式中斷
//            }
//
//            /***************************2.開始修改資料*****************************************/
//            RentalService rentalSvc = new RentalService();
//            rentalVO = rentalSvc.updateRental(rNo, rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo);
//
//            /***************************3.修改完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("rentalVO", rentalVO); // 資料庫update成功後,正確的的RentalVO物件,存入req
//            out.println("修改成功!");
//        }
////======================================================================================================================== "getAll"
////======================================================================================================================== 待處理
//        if ("getAll".equals(action)) { // 來自select_page.jsp的請求
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************2.開始查詢資料*****************************************/
//            RentalService rentalSvc = new RentalService();
//            List<RentalVO> rentalVOList = rentalSvc.getAll();
//
//            if (rentalVOList.isEmpty()) {
//                errorMsgs.put("result", "查無資料"); // 如果結果為空，設置錯誤消息
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req.getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return; // 程序中斷
//            }
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("rentalVOList", rentalVOList); // 將查詢到的通知消息列表存入請求屬性中
//            String url = "/rental/listAllRental.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉發到 listAllRental.jsp 頁面
//            successView.forward(req, res);
//        }
//
//
////======================================================================================================================== "getOne_For_Display"
////======================================================================================================================== 待處理
//        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            String str = req.getParameter("rNo");
//            if (str == null || (str.trim()).length() == 0) {
//                errorMsgs.put("rNo", "請輸入租借品編號");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            Integer rNo = null;
//            try {
//                rNo = Integer.valueOf(str);
//            } catch (Exception e) {
//                errorMsgs.put("rNo","租借品編號格式不正確");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            /***************************2.開始查詢資料*****************************************/
//            RentalService rentalSvc = new RentalService();
//            RentalVO rentalVO = rentalSvc.getOneRental(rNo);
//            if (rentalVO == null) {
//                errorMsgs.put("rNo","查無資料");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("rentalVO", rentalVO); // 資料庫取出的rentalVO物件,存入req
//            String url = "/rental/listOneRental.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRental.jsp
//            successView.forward(req, res);
//        }
//
////======================================================================================================================== "getOne_For_Update"
//        if ("getOne_For_Update".equals(action)) { // 來自listAllRental.jsp的請求
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數****************************************/
//            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
//
//            /***************************2.開始查詢資料****************************************/
//            RentalService rentalSvc = new RentalService();
//            RentalVO rentalVO = rentalSvc.getOneRental(rNo);
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
//            req.setAttribute("rentalVO",rentalVO);
//            String url = "/rental/update_rental_input.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rental_input.jsp
//            successView.forward(req, res);
//        }
////======================================================================================================================== "getRentalsByrCatNo_Display"
//        if ("getRentalsByrCatNo_Display".equals(action)) { // 來自select_page.jsp的請求
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            String str = req.getParameter("rCatNo");
//            if (str == null || (str.trim()).length() == 0) {
//                errorMsgs.put("rCatNo", "請輸入租借品類別編號");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            Integer rCatNo = null;
//            try {
//                rCatNo = Integer.valueOf(str);
//            } catch (Exception e) {
//                errorMsgs.put("rCatNo","租借品類別編號格式不正確");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            /***************************2.開始查詢資料*****************************************/
//            RentalService rentalSvc = new RentalService();
//            Set<RentalVO> rentalVOSet = rentalSvc.getRentalsByrCatNo(rCatNo);
//
//            if (rentalVOSet.isEmpty()) {
//                errorMsgs.put("result", "查無資料"); // 如果結果為空，設置錯誤消息
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req.getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return; // 程序中斷
//            }
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("rentalVOSet", rentalVOSet); // 將查詢到的通知消息列表存入請求屬性中
//            String url = "/rental/listAllRental.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉發到 listAllRental.jsp 頁面
//            successView.forward(req, res);
//        }
//
////======================================================================================================================== "getRentalsByrCatNo_Update"
//        if ("getRentalsByrCatNo_Update".equals(action)) { // 來自listAllRental.jsp的請求
//
//            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數****************************************/
//            Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));
//
//            /***************************2.開始查詢資料****************************************/
//            RentalService rentalSvc = new RentalService();
//            Set<RentalVO> rentalVOSet = rentalSvc.getRentalsByrCatNo(rCatNo);
//
//            if (rentalVOSet.isEmpty()) {
//                errorMsgs.put("result", "查無資料"); // 如果結果為空，設置錯誤消息
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req.getRequestDispatcher("/rental/select_page.jsp");
//                failureView.forward(req, res);
//                return; // 程序中斷
//            }
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
//            req.setAttribute("rentalVOSet", rentalVOSet); // 將查詢到的通知消息列表存入請求屬性中
//            String url = "/rental/update_rental_input.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉發到 update_rental_input.jsp 頁面
//            successView.forward(req, res);
//        }
//    }
//}