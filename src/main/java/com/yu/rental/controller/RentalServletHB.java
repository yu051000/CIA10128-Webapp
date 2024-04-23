package com.yu.rental.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import com.google.gson.Gson;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.yu.rental.model.RentalVO;
import com.yu.rental.service.RentalServiceImpl;
import com.yu.rental.service.RentalService_Interface;
import com.yu.rental.model.RentalVO;
import com.yu.util.Constants;

@WebServlet("/rental/rental.do")
@MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024) //限制大小
public class RentalServletHB extends HttpServlet {

    // 一個 servlet 實體對應一個 service 實體
    private RentalServiceImpl rentalServiceImpl;

    @Override
    public void init() throws ServletException {
        rentalServiceImpl = new RentalServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");  //如果沒有filter過濾器統一宣告就要打
        String action = req.getParameter("action");
        res.setContentType("text/html; charset=UTF-8");

        // 依據action判斷
        String forwardPath = "";
        switch (action) {
            case "getAll":
//                forwardPath = getAllRentals(req, res);
                break;
            case "compositeQuery":
//                forwardPath = getByCompositeQuery(req, res);
                break;
            default:
                forwardPath = "/index.jsp";
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
        Integer rNo = null;
        try {
            rNo = Integer.valueOf(req.getParameter("rNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rNo", "租借品編號 : 請填數字!");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rNo", "租借品編號 : 不能空白!");
        }

        Integer rCatNo = null;
        try {
            rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字!");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rCatNo", "租借品類別編號 : 請勿空白");
        }

        String rName = req.getParameter("rName");
        String rNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rName == null || rName.trim().length() == 0) {
            errorMsgs.put("rName","租借品名稱：請勿空白");
        } else if(!rName.trim().matches(rNameReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rName","租借品名稱：只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        BigDecimal rPrice = null;
        try {
            rPrice = new BigDecimal(req.getParameter("rPrice"));
        } catch (NumberFormatException e) {
            errorMsgs.put("rPrice","租借品單價：請填數字");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rPrice", "租借品單價 : 請勿空白");
        }

        Integer rSize = null;
        try {
            rSize = Integer.valueOf(req.getParameter("rSize").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rSize", "尺寸 : 請填數字。(對照表：S碼-> 1、M碼-> 2、L碼-> 3)");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rSize", "尺寸 : 請勿空白");
        }

        String rColor = req.getParameter("rColor");
        String rColorReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rColor == null || rColor.trim().length() == 0) {
            errorMsgs.put("rColor","顏色：請勿空白");
        } else if(!rColor.trim().matches(rColorReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rColor","顏色：只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        String rInfo = req.getParameter("rInfo");
        String rInfoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rInfo == null || rInfo.trim().length() == 0) {
            errorMsgs.put("rInfo","租借品資訊：請勿空白");
        } else if(!rInfo.trim().matches(rInfoReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rInfo","租借品資訊：只能是中、英文字母、數字和_ , 且長度必需在1000以內");
        }

        Byte rStat = null;
        String rStatString = req.getParameter("rStat");
        if (rStatString == null || rStatString.trim().isEmpty()) {
            errorMsgs.put("rStat","租借品狀態：請勿空白");
        } else {
            try {
                rStat = Byte.parseByte(rStatString);
            } catch (NumberFormatException e) {
                errorMsgs.put("rStat","租借品狀態：請輸入0~5的數字");
            }
        }

        RentalVO rentalVO = new RentalVO(); // 創建一個RentalVO物件

        // 將參數存入rentalVO中
        rentalVO.setrNo(rNo);
        rentalVO.setrName(rName);
        rentalVO.setrPrice(rPrice);
        rentalVO.setrSize(rSize);
        rentalVO.setrColor(rColor);
        rentalVO.setrInfo(rInfo);
        rentalVO.setrStat(rStat);

        // 如果有錯誤，將錯誤處理訊息傳回表單
        if (!errorMsgs.isEmpty()) {
            errorMsgs.put("Exception","修改資料失敗:---------------");
            // 將錯誤訊息存入request中
            req.setAttribute("errorMsgs", errorMsgs);

            // 請求轉發回原始頁面add
            RequestDispatcher failureView = req.getRequestDispatcher("/rental/addRental.jsp");
            failureView.forward(req, res);

        }else{
            // ------------------ 2.開始修改資料 ------------------ //
            rentalServiceImpl.addRental(rentalVO);
            // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
            String url = "/rental/listAllRental.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllRental.jsp
            successView.forward(req, res);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// update
    private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //設定錯誤處理訊息
        Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
        req.setAttribute("errorMsgs", errorMsgs);

        // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
        Integer rNo = null;
        try {
            rNo = Integer.valueOf(req.getParameter("rNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rNo", "租借品編號 : 請填數字!");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rNo", "租借品編號 : 不能空白!");
        }

        Integer rCatNo = null;
        try {
            rCatNo = Integer.valueOf(req.getParameter("rCatNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rCatNo", "租借品類別編號 : 請填數字!");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rCatNo", "租借品類別編號 : 請勿空白");
        }

        String rName = req.getParameter("rName");
        String rNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rName == null || rName.trim().length() == 0) {
            errorMsgs.put("rName","租借品名稱：請勿空白");
        } else if(!rName.trim().matches(rNameReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rName","租借品名稱：只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        BigDecimal rPrice = null;
        try {
            rPrice = new BigDecimal(req.getParameter("rPrice"));
        } catch (NumberFormatException e) {
            errorMsgs.put("rPrice","租借品單價：請填數字");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rPrice", "租借品單價 : 請勿空白");
        }

        Integer rSize = null;
        try {
            rSize = Integer.valueOf(req.getParameter("rSize").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rSize", "尺寸 : 請填數字。(對照表：S碼-> 1、M碼-> 2、L碼-> 3)");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rSize", "尺寸 : 請勿空白");
        }

        String rColor = req.getParameter("rColor");
        String rColorReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rColor == null || rColor.trim().length() == 0) {
            errorMsgs.put("rColor","顏色：請勿空白");
        } else if(!rColor.trim().matches(rColorReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rColor","顏色：只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }

        String rInfo = req.getParameter("rInfo");
        String rInfoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        if (rInfo == null || rInfo.trim().length() == 0) {
            errorMsgs.put("rInfo","租借品資訊：請勿空白");
        } else if(!rInfo.trim().matches(rInfoReg)) { //以下練習正則(規)表示式(regular-expression)
            errorMsgs.put("rInfo","租借品資訊：只能是中、英文字母、數字和_ , 且長度必需在1000以內");
        }

        Byte rStat = null;
        String rStatString = req.getParameter("rStat");
        if (rStatString == null || rStatString.trim().isEmpty()) {
            errorMsgs.put("rStat","租借品狀態：請勿空白");
        } else {
            try {
                rStat = Byte.parseByte(rStatString);
            } catch (NumberFormatException e) {
                errorMsgs.put("rStat","租借品狀態：請輸入0~5的數字");
            }
        }

        RentalVO rentalVO = new RentalVO(); // 創建一個RentalVO物件

        // 將參數存入rentalVO中
        rentalVO.setrNo(rNo);
        rentalVO.setrName(rName);
        rentalVO.setrPrice(rPrice);
        rentalVO.setrSize(rSize);
        rentalVO.setrColor(rColor);
        rentalVO.setrInfo(rInfo);
        rentalVO.setrStat(rStat);

        // 如果有錯誤，將錯誤處理訊息傳回表單
        if (!errorMsgs.isEmpty()) {
            errorMsgs.put("Exception","修改資料失敗:---------------");
            // 將錯誤訊息存入request中
            req.setAttribute("errorMsgs", errorMsgs);

            // 請求轉發回原始頁面add
            RequestDispatcher failureView = req.getRequestDispatcher("/rental/addRental.jsp");
            failureView.forward(req, res);

        }else{
            // ------------------ 2.開始修改資料 ------------------ //
            rentalServiceImpl.updateRental(rentalVO);
            // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
            String url = "/rental/listAllRental.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllRental.jsp
            successView.forward(req, res);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// delete
    private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //設定錯誤處理訊息
        Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
        req.setAttribute("errorMsgs", errorMsgs);

        // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
        Integer rNo = null;
        try {
            rNo = Integer.valueOf(req.getParameter("rNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rNo", "租借品編號 : 請填數字!");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rNo", "租借品編號 : 不能空白!");
        }
        // ------------------ 2.開始刪除資料 ------------------ //
        rentalServiceImpl.deleteRental(rNo);

        // ------------------ 3.修改完成,準備轉交(Send the Success view) ------------------ //
        String url = "/rental/listAllRental.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url);
        successView.forward(req, res);  // 刪除成功後,轉交回送出刪除的來源網頁
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getOneUpdate
    private void getOneUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //設定錯誤處理訊息
        Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
        req.setAttribute("errorMsgs", errorMsgs);

        // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
        Integer rNo = Integer.valueOf(req.getParameter("rNo"));

        Integer rCatNo = Integer.valueOf(req.getParameter("rCatNo"));

        String rName = req.getParameter("rName");

        BigDecimal rPrice = new BigDecimal(req.getParameter("rPrice"));

        Integer rSize = Integer.valueOf(req.getParameter("rSize"));

        String rColor = req.getParameter("rColor");

        String rInfo = req.getParameter("rInfo");

        Byte rStat = Byte.valueOf(req.getParameter("rStat"));

        // ------------------ 2.開始查詢資料 ------------------ //
        RentalVO rentalVO = rentalServiceImpl.getOneRental(rNo);
        // ------------------ 3.查詢完成,準備轉交(Send the Success view) ------------------ //
//            req.setAttribute("rentalVO", rentalVO); // 資料庫取出的productVO物件,存入req
        String url = "/rental/update_rental_input.jsp";
        RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rental_input.jsp
        successView.forward(req, res);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getOneDisplay
    private void getOneDisplay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //設定錯誤處理訊息
        Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
        req.setAttribute("errorMsgs", errorMsgs);

        // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
        Integer rNo = null;
        try {
            rNo = Integer.valueOf(req.getParameter("rNo").trim());
        } catch (NumberFormatException e) {
            errorMsgs.put("rNo", "租借品編號 : 請填數字");
        } catch (NullPointerException nullPointerException) {
            errorMsgs.put("rNo", "租借品編號 : 請勿空白");
        }

        // 如果有錯誤，將錯誤處理訊息傳回表單
        if (!errorMsgs.isEmpty()) {
            RequestDispatcher failureView = req
                    .getRequestDispatcher("/rental/select_page.jsp");
            failureView.forward(req, res);
            return;//程式中斷

        }else{
            // ------------------ 2.開始查詢資料 ------------------ //
            RentalVO rentalVO = rentalServiceImpl.getOneRental(rNo);
            // ------------------ 3.查詢完成,準備轉交(Send the Success view) ------------------ //
            req.setAttribute("rentalVO", rentalVO); // 資料庫取出的rentalVO物件,存入req
            req.setAttribute("getOne_For_Display", "true");
            String url = "/rental/listOneRental.jsp";    // 成功轉交 listOneRental.jsp
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAllRentals
    //查詢當前頁面
    private String getAllRentals(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page"); //取得參數(當前頁面)
        int currentPage = (page == null) ? 1 : Integer.parseInt(page); //如果該參數為空，則將當前頁面設置為 1；否則將其轉換為整數。

        //呼叫ServiceImpl的getByCompositeQuery方法設定分頁，將結果存儲在list中
        List<RentalVO> rentalList = rentalServiceImpl.getAllRentals(currentPage); //呼叫ServiceImpl的檢索租借分類列表

        //如果在請求的會話中沒有名為 "rentalPageQty" 的屬性
        if (req.getSession().getAttribute("rentalPageQty") == null) {

            //使用 RentalServiceImpl 的方法獲取總頁數（Page Total），並將其設置到會話屬性中
            long rentalPageQty = rentalServiceImpl.getPageTotal();
            req.getSession().setAttribute("rentalPageQty", rentalPageQty);
        }

        // 將獲取到的租借分類列表和當前頁面設置到請求屬性中
        req.setAttribute("rentalList", rentalList);
        req.setAttribute("currentPage", currentPage);

        return "/rental/listAllRental.jsp"; //返回查看所有租借品的頁面
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByCompositeQuery
    //複合查詢
    private String getByCompositeQuery(HttpServletRequest req, HttpServletResponse                                                                                                           res) {
        Map<String, String[]> map = req.getParameterMap(); // 獲取參數映射（Parameter Map）

        if (map != null) {
            //呼叫ServiceImpl的getByCompositeQuery方法執行複合查詢，將結果存儲在list中
            List<RentalVO> list = rentalServiceImpl.getByCompositeQuery(map);
            req.setAttribute("list", list);

        } else { //如果為空(沒有參數傳遞)
            return "/index.jsp"; //返回到首頁
        }

        //無論是否進行了複合查詢，該方法都會將結果存儲在請求屬性中，並返回指定頁面
        return "/rental/listCompositeQueryRentals.jsp";
    }
}
