package com.rental.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class RentalJDBCDAO implements RentalDAO_interface{

    //對應DB帳戶
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/fallelove?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "shirley24608339";


    private static final String INSERT_STMT =
        "INSERT INTO Rental(rNO, rCatNo, rName, rPrice, rSize, rColor, rInfo, rStat) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
        "UPDATE Rental SET rCatNo=?, rName=?, rPrice=?, rSize=?, rColor=?, rInfo=?, rStat=? WHERE rNo=?";

    private static final String GET_ONE_STMT =
        "SELECT rNO, rCatNo, rName, rPrice, rSize, rColor, rInfo, rStat WHERE rNo=?";

    private static final String GET_ALL_STMT =
        "SELECT rNO, rCatNo, rName, rPrice, rSize, rColor, rInfo, rStat FROM Rental ORDER BY rNo";

    @Override
    public void insert(RentalVO rentalVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, rentalVO.getrNo());
            pstmt.setInt(2, rentalVO.getrCatNo());
            pstmt.setString(3, rentalVO.getrName());
            pstmt.setBigDecimal(5, rentalVO.getrPrice());
            pstmt.setInt(6, rentalVO.getrSize());
            pstmt.setString(7, rentalVO.getrColor());
            pstmt.setString(8, rentalVO.getrInfo());
            pstmt.setByte(9, (byte) rentalVO.getrStat());

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
    public void update(RentalVO rentalVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(2, rentalVO.getrCatNo());
            pstmt.setString(3, rentalVO.getrName());
            pstmt.setBigDecimal(5, rentalVO.getrPrice());
            pstmt.setInt(6, rentalVO.getrSize());
            pstmt.setString(7, rentalVO.getrColor());
            pstmt.setString(8, rentalVO.getrInfo());
            pstmt.setByte(9, (byte) rentalVO.getrStat());

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
	public RentalVO findByPrimaryKey(Integer rNo) {

		RentalVO rentalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalVO = new RentalVO();
				rentalVO.setrNo(rs.getInt("rNo"));
				rentalVO.setrCatNo(rs.getInt("rCatNo"));
				rentalVO.setrName(rs.getString("rName"));
				rentalVO.setrPrice(rs.getBigDecimal("rPrice"));
				rentalVO.setrSize(rs.getInt("rSize"));
				rentalVO.setrColor(rs.getString("rColor"));
				rentalVO.setrInfo(rs.getString("rInfo"));
				rentalVO.setrStat(rs.getByte("rStat"));
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
		return rentalVO;
	}

	@Override
	public List<RentalVO> getAll() {
		List<RentalVO> list = new ArrayList<RentalVO>();
		RentalVO rentalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalVO = new RentalVO();
				rentalVO.setrNo(rs.getInt("rNo"));
				rentalVO.setrCatNo(rs.getInt("rCatNo"));
				rentalVO.setrName(rs.getString("rName"));
				rentalVO.setrPrice(rs.getBigDecimal("rPrice"));
				rentalVO.setrSize(rs.getInt("rSize"));
				rentalVO.setrColor(rs.getString("rColor"));
				rentalVO.setrInfo(rs.getString("rInfo"));
				rentalVO.setrStat(rs.getByte("rStat"));
				list.add(rentalVO); 
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
	
//------------------------------------------------------------------------//
	public static void main(String[] args) {

		RentalJDBCDAO dao = new RentalJDBCDAO();

		// insert
		RentalVO rentalVO1 = new RentalVO();
		rentalVO1.setrNo(5002);
		rentalVO1.setrCatNo(9);
		rentalVO1.setrName("格紋成套西裝");
		rentalVO1.setrPrice(new BigDecimal(10200));
		rentalVO1.setrSize(2);
		rentalVO1.setrColor("黑色");
		rentalVO1.setrInfo("款式：劍領 單排釦。適用場合：婚攝 婚宴 宴客 求婚");
		rentalVO1.setrStat((byte) 2);
		dao.insert(rentalVO1);

		// update
		RentalVO rentalVO2 = new RentalVO();
		rentalVO1.setrNo(5015);
		rentalVO1.setrCatNo(7);
		rentalVO1.setrName("千鳥紋西裝外套");
		rentalVO1.setrPrice(new BigDecimal(9999));
		rentalVO1.setrSize(4);
		rentalVO1.setrColor("黑白紋");
		rentalVO1.setrInfo("款式：標準領 雙排釦。適用場合：婚攝 婚宴 宴客 求婚 日常");
		rentalVO1.setrStat((byte) 3);
		dao.update(rentalVO2);

		// 單筆查詢
		RentalVO rentalVO3 = dao.findByPrimaryKey(5001);
		System.out.print(rentalVO3.getrNo() + ",");
		System.out.print(rentalVO3.getrCatNo() + ",");
		System.out.print(rentalVO3.getrName() + ",");
		System.out.print(rentalVO3.getrPrice() + ",");
		System.out.print(rentalVO3.getrSize() + ",");
		System.out.print(rentalVO3.getrColor() + ",");
		System.out.print(rentalVO3.getrInfo() + ",");
		System.out.println(rentalVO3.getrStat());
		System.out.println("---------------------");

		// 全部查詢
		List<RentalVO> list = dao.getAll();
		for (RentalVO aRental : list) {
			System.out.print(aRental.getrNo() + ",");
			System.out.print(aRental.getrCatNo() + ",");
			System.out.print(aRental.getrName() + ",");
			System.out.print(aRental.getrPrice() + ",");
			System.out.print(aRental.getrSize() + ",");
			System.out.print(aRental.getrColor() + ",");
			System.out.print(aRental.getrInfo() + ",");
			System.out.println(aRental.getrStat());
			System.out.println();
		}
	}
}
