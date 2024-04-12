package com.rentalcategory.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static java.lang.System.out;

public class RentalCategoryJDBCDAO implements RentalCategoryDAO_interface {

    //對應DB帳戶
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/fallelove?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "shirley24608339";


    private static final String INSERT_STMT =
            "INSERT INTO RentalCategory(rCatName, rStockQty, rRentedQty, rDesPrice) VALUES(?, ?, ?, ?)";

    private static final String GET_ALL_STMT =
            "SELECT rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice FROM RentalCategory ORDER BY rCatNo";

    private static final String GET_ONE_STMT =
            "SELECT rCatNo, rCatName, rStockQty, rRentedQty, rDesPrice FROM RentalCategory WHERE rCatNo=?";

    private static final String UPDATE =
            "UPDATE RentalCategory SET rCatName=?, rStockQty=?, rRentedQty=?, rDesPrice=? WHERE rCatNo=?";
	private static final String DELETE =
			"DELETE FROM Rental where rCatNo = ?";

    @Override
    public void insert(RentalCategoryVO rentalCategoryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, rentalCategoryVO.getrCatName());
            pstmt.setInt(2, rentalCategoryVO.getrStockQty());
            pstmt.setInt(3, rentalCategoryVO.getrRentedQty());
            pstmt.setBigDecimal(4, rentalCategoryVO.getrDesPrice());

            pstmt.executeUpdate();
            out.println("新增成功");

            // 驅動程式錯誤
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // DB錯誤
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // 清理JDBC資源
        } finally {
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
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, rentalCategoryVO.getrCatName());
            pstmt.setInt(2, rentalCategoryVO.getrStockQty());
            pstmt.setInt(3, rentalCategoryVO.getrRentedQty());
            pstmt.setBigDecimal(4, rentalCategoryVO.getrDesPrice());
            pstmt.setInt(5, rentalCategoryVO.getrCatNo());

            pstmt.executeUpdate();

            // 驅動程式錯誤
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // DB錯誤
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // 清理JDBC資源
        } finally {
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

        RentalCategoryVO rentalCategoryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, rCatNo);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                rentalCategoryVO = new RentalCategoryVO();

                rentalCategoryVO.setrCatNo(rs.getInt("rCatNo"));
                rentalCategoryVO.setrCatName(rs.getString("rCatName"));
                rentalCategoryVO.setrStockQty(rs.getInt("rStockQty"));
                rentalCategoryVO.setrRentedQty(rs.getInt("rRentedQty"));
                rentalCategoryVO.setrDesPrice(rs.getBigDecimal("rDesPrice"));
            }

            // 驅動程式錯誤
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // DB錯誤
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

            // 清理JDBC資源
        } finally {
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
        return rentalCategoryVO;
    }

    @Override
    public List<RentalCategoryVO> getAll() {
        List<RentalCategoryVO> list = new ArrayList<RentalCategoryVO>();
        RentalCategoryVO rentalCategoryVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rentalCategoryVO = new RentalCategoryVO();

                rentalCategoryVO.setrCatNo(rs.getInt("rCatNo"));
                rentalCategoryVO.setrCatName(rs.getString("rCatName"));
                rentalCategoryVO.setrStockQty(rs.getInt("rStockQty"));
                rentalCategoryVO.setrRentedQty(rs.getInt("rRentedQty"));
                rentalCategoryVO.setrDesPrice(rs.getBigDecimal("rDesPrice"));

                list.add(rentalCategoryVO);
            }

            // 驅動程式錯誤
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

            // DB錯誤
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

            // 清理JDBC資源
        } finally {
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

    @Override
	public void delete(Integer rCatNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rCatNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
//------------------------------------------------------------------------//
    public static void main(String[] args) {

        RentalCategoryJDBCDAO dao = new RentalCategoryJDBCDAO();

        // insert
        RentalCategoryVO rentalCategoryVO1 = new RentalCategoryVO();

        rentalCategoryVO1.setrCatNo(5);
        rentalCategoryVO1.setrCatName("西裝");
        rentalCategoryVO1.setrStockQty(2);
        rentalCategoryVO1.setrRentedQty(2);
        rentalCategoryVO1.setrDesPrice(new BigDecimal(10200));
        dao.insert(rentalCategoryVO1);

        // update
        RentalCategoryVO rentalCategoryVO2 = new RentalCategoryVO();

        rentalCategoryVO2.setrCatNo(7);
        rentalCategoryVO2.setrCatName("禮服");
        rentalCategoryVO2.setrStockQty(4);
        rentalCategoryVO2.setrRentedQty(2);
        rentalCategoryVO2.setrDesPrice(new BigDecimal(9999));
        dao.update(rentalCategoryVO2);

		// 刪除
		dao.delete(2);

        // 單筆查詢
        RentalCategoryVO rentalCategoryVO3 = dao.findByPrimaryKey(2);
        out.print(rentalCategoryVO3.getrCatNo() + ",");
        out.print(rentalCategoryVO3.getrCatName() + ",");
        out.print(rentalCategoryVO3.getrStockQty() + ",");
        out.print(rentalCategoryVO3.getrRentedQty() + ",");
        out.print(rentalCategoryVO3.getrDesPrice());
        out.println("---------------------");

        // 全部查詢
        List<RentalCategoryVO> list = dao.getAll();
        for (RentalCategoryVO aRentalCategory : list) {
            out.print(aRentalCategory.getrCatNo() + ",");
            out.print(aRentalCategory.getrCatName() + ",");
            out.print(aRentalCategory.getrStockQty() + ",");
            out.print(aRentalCategory.getrRentedQty() + ",");
            out.print(aRentalCategory.getrDesPrice() + ",");
            out.println();
        }
    }
}
