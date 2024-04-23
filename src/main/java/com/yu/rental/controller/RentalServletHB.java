//package com.yu.rental.controller;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.util.*;
//import javax.servlet.*;
//import com.google.gson.Gson;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import com.yu.rental.service.RentalServiceImpl;
//import com.yu.rentalcategory.service.RentalCategoryService;
//import com.yu.rentalcategory.service.RentalCategoryServiceImpl;
//import com.yu.rentalcategory.service.RentalCategoryService_Interface;
//import com.yu.rentalcategory.model.RentalCategoryVO;
//import com.yu.util.Constants;
//
//@WebServlet("/rentalcategory/rentalCategory.do")
//@MultipartConfig(fileSizeThreshold=1024*1024,maxRequestSize=5*5*1024*1024) //限制大小
//public class RentalServletHB extends HttpServlet {
//
//    // 一個 servlet 實體對應一個 service 實體
//    private RentalServiceImpl rentalServiceImpl;
//
//    @Override
//    public void init() throws ServletException {
//        rentalServiceImpl = new RentalServiceImpl();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        doPost(req, res);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");  //如果沒有filter過濾器統一宣告就要打
//        String action = req.getParameter("action");
//        res.setContentType("text/html; charset=UTF-8");
//
//        // 依據action判斷
//        String forwardPath = "";
//        switch (action) {
//            case "getAll":
//                forwardPath = getAllRentals(req, res);
//                break;
//            case "compositeQuery":
//                forwardPath = getByCompositeQuery(req, res);
//                break;
//            default:
//                forwardPath = "/index.jsp";
//        }
//        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//        dispatcher.forward(req, res);
//    }
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// insert
//    private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//
//        //設定錯誤處理訊息
//        Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//        req.setAttribute("errorMsgs", errorMsgs);
//
//        // ------------------ 1.接收請求參數 - 輸入格式的錯誤處理 ------------------ //
//
//}
