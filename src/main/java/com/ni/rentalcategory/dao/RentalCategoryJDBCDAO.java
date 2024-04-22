package com.ni.rentalcategory.dao;

import com.ni.rentalcategory.vo.RentalCategoryVO;

import java.sql.*;
import java.util.*;

public class RentalCategoryJDBCDAO implements RentalCategoryDAO_interface {

    //建立連線資訊
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/fallelove?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "shirley24608339";

    //宣告spl指令
    private static final String INSERT_STMT =
            "INSERT INTO RentalCategory(rCatName, rStockQty, rRentedQty, rDesPrice) VALUES(?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE RentalCategory SET rCatName=?, rStockQty=?, rRentedQty=?, rDesPrice=? WHERE rCatNo=?";

    //單筆查詢
    private static final String GET_ONE_STMT =
            "SELECT rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice FROM RentalCategory WHERE rCatNo=?";

    //查詢全部
    private static final String GET_ALL_STMT =
            "SELECT rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice FROM RentalCategory ORDER BY rCatNo";

    //刪除 (目前是用sql級聯版。Hibernate有自己處理的方式，若用Hibernate，sql要換回一般的)
    private static final String DELETE =
            "DELETE FROM RentalCategory WHERE rCatNo= ?";

    @Override
    public void insert(RentalCategoryVO rentalCategoryVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            con.setAutoCommit(false);  //關閉自動交付(commit)的功能

            pstmt = con.prepareStatement(INSERT_STMT);   //使用DELETE的sql指令

            pstmt.setString(1, rentalCategoryVO.getrCatName()); //設定參數、運行語句
            pstmt.setInt(2, rentalCategoryVO.getrStockQty());
            pstmt.setInt(3, rentalCategoryVO.getrRentedQty());
            pstmt.setBigDecimal(4, rentalCategoryVO.getrDesPrice());

            pstmt.executeUpdate();  //更改DB內容
            con.commit();  // 提交交易
            con.setAutoCommit(true);  // 恢復自動交付

        } catch (ClassNotFoundException e) { // 驅動程式錯誤
            throw new RuntimeException("Couldn't load database driver." + e.getMessage());

        } catch (SQLException se) { // DB錯誤
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
    }

    @Override
    public void delete(Integer rCatNo) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            con.setAutoCommit(false);  //關閉自動交付(commit)的功能
            pstmt = con.prepareStatement(DELETE);   //使用DELETE的sql指令

            pstmt.setInt(1, rCatNo);
            pstmt.executeUpdate();  //更改DB內容

            con.commit();  // 提交交易

        } catch (ClassNotFoundException e) {   //驅動程式錯誤
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {  //DB錯誤
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

        } finally {  //清理JDBC資源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void update(RentalCategoryVO rentalCategoryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            con.setAutoCommit(false);  //關閉自動交付(commit)的功能
            pstmt = con.prepareStatement(UPDATE);   //使用UPDATE的sql指令

            pstmt.setString(1, rentalCategoryVO.getrCatName()); //設定參數、運行語句
            pstmt.setInt(2, rentalCategoryVO.getrStockQty());
            pstmt.setInt(3, rentalCategoryVO.getrRentedQty());
            pstmt.setBigDecimal(4, rentalCategoryVO.getrDesPrice());
            pstmt.setInt(5, rentalCategoryVO.getrCatNo());

            pstmt.executeUpdate(); //更改DB內容

        } catch (ClassNotFoundException e) {   //驅動程式錯誤
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {   // DB錯誤
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

        } finally {  // 清理JDBC資源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public RentalCategoryVO findByPrimaryKey(Integer rCatNo) {

        RentalCategoryVO rentalCategoryVO = null; // 宣告VO並指定空值，若查詢無結果會出現空值，後續於Controller作錯誤處理
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);   //使用哪種已宣告的sql指令
            con.setAutoCommit(false);  //關閉自動交付(commit)的功能

            pstmt.setInt(1, rCatNo); // 將request的rCatNo放入SQL


            // Statement關閉，ResultSet也會自動關閉
            rs = pstmt.executeQuery(); // [執行SQL查詢並得到ResultSet物件] ResultSet並非存取詳實資料

            while (rs.next()) {  // ResultSet取值⽅法 [ResultSet.getXXX(index)搭配欄位索引取值]，把資料放入VO
                rentalCategoryVO = new RentalCategoryVO();
                rentalCategoryVO.setrCatNo(rs.getInt("rCatNo")); //設定參數、運行語句
                rentalCategoryVO.setrCatName(rs.getString("rCatName"));
                rentalCategoryVO.setrStockQty(rs.getInt("rStockQty"));
                rentalCategoryVO.setrRentedQty(rs.getInt("rRentedQty"));
                rentalCategoryVO.setrDesPrice(rs.getBigDecimal("rDesPrice"));

            }
        } catch (ClassNotFoundException e) {   //驅動程式錯誤
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {  // DB錯誤
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

        } finally {  // 清理JDBC資源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return rentalCategoryVO; // 回傳VO，待後續Controller導至View呈現
    }

    @Override
    public List<RentalCategoryVO> getAll() {

        List<RentalCategoryVO> list = new ArrayList<RentalCategoryVO>();
        RentalCategoryVO rentalCategoryVO = null; // 宣告VO並指定空值，若查詢無結果會出現空值，後續於Controller作錯誤處理
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);   //使用GET_ALL_STMT的sql指令
            con.setAutoCommit(false);  //關閉自動交付(commit)的功能

            //Statement關閉，ResultSet也會自動關閉，
            rs = pstmt.executeQuery();  // [執行SQL查詢並得到ResultSet物件] ResultSet並非存取詳實資料

            while (rs.next()) { // ResultSet取值⽅法 [ResultSet.getXXX(index)搭配欄位索引取值]，把資料放入VO
                rentalCategoryVO = new RentalCategoryVO();
                rentalCategoryVO.setrCatNo(rs.getInt("rCatNo")); //設定參數、運行語句
                rentalCategoryVO.setrCatName(rs.getString("rCatName"));
                rentalCategoryVO.setrStockQty(rs.getInt("rStockQty"));
                rentalCategoryVO.setrRentedQty(rs.getInt("rRentedQty"));
                rentalCategoryVO.setrDesPrice(rs.getBigDecimal("rDesPrice"));

                list.add(rentalCategoryVO); //資料新增到list
            }
        } catch (ClassNotFoundException e) {   //驅動程式錯誤
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {  // DB錯誤
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

        } finally {  // 清理JDBC資源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }
}
