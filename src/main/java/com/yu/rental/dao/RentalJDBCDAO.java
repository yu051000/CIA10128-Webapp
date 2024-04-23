//package com.ni.rental.dao;
//
//import com.ni.rental.vo.RentalVO;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.*;
//
//import static java.lang.System.out;
//
//public class RentalJDBCDAO implements RentalDAO_interface{
//
//	//對應DB帳戶
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/fallelove?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "shirley24608339";
//
//
//	private static final String INSERT_STMT =
//			"INSERT INTO Rental(rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo) VALUES(?, ?, ?, ?, ?, ?, ?)";
//
//	private static final String GET_ALL_STMT =
//			"SELECT rNO, rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo FROM Rental ORDER BY rNo";
//
//	private static final String GET_ONE_STMT =
//			"SELECT rNO, rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo FROM Rental WHERE rNo=?";
//
//	private static final String GET_RENTALS_BYRCATNO_STMT =
//			"SELECT rNO, rName, rPrice, rSize, rColor, rInfo, rStat, rCatNo FROM Rental where rCatNo = ? ORDER BY rNo";
//
//	private static final String UPDATE =
//			"UPDATE Rental SET rName=?, rPrice=?, rSize=?, rColor=?, rInfo=?, rStat=? ,rCatNo=? WHERE rNo=?";
//	private static final String DELETE =
//			"DELETE FROM Rental where rNo = ?";
//
//	@Override
//	public void insert(RentalVO rentalVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, rentalVO.getrName());
//			pstmt.setBigDecimal(2, rentalVO.getrPrice());
//			pstmt.setInt(3, rentalVO.getrSize());
//			pstmt.setString(4, rentalVO.getrColor());
//			pstmt.setString(5, rentalVO.getrInfo());
//			pstmt.setByte(6, rentalVO.getrStat());
//			pstmt.setInt(7, rentalVO.getrCatNo());
//
//			pstmt.executeUpdate();
//			out.println("新增成功");
//
//			// 驅動程式錯誤
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// DB錯誤
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// 清理JDBC資源
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void update(RentalVO rentalVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, rentalVO.getrName());
//			pstmt.setBigDecimal(2, rentalVO.getrPrice());
//			pstmt.setInt(3, rentalVO.getrSize());
//			pstmt.setString(4, rentalVO.getrColor());
//			pstmt.setString(5, rentalVO.getrInfo());
//			pstmt.setByte(6, rentalVO.getrStat());
//			pstmt.setInt(7, rentalVO.getrCatNo());
//			pstmt.setInt(8, rentalVO.getrNo());
//
//			pstmt.executeUpdate();
//			out.println("修改成功");
//
//			// 驅動程式錯誤
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// DB錯誤
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// 清理JDBC資源
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void delete(Integer rNo) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, rNo);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public RentalVO findByPrimaryKey(Integer rNo) {
//
//		RentalVO rentalVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, rNo);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// rentalVO 也稱為 Domain objects
//				rentalVO = new RentalVO();
//
//				rentalVO.setrNo(rs.getInt("rNo"));
//				rentalVO.setrName(rs.getString("rName"));
//				rentalVO.setrPrice(rs.getBigDecimal("rPrice"));
//				rentalVO.setrSize(rs.getInt("rSize"));
//				rentalVO.setrColor(rs.getString("rColor"));
//				rentalVO.setrInfo(rs.getString("rInfo"));
//				rentalVO.setrStat(rs.getByte("rStat"));
//				rentalVO.setrCatNo(rs.getInt("rCatNo"));
//			}
//
//			// 驅動程式錯誤
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// DB錯誤
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//
//			// 清理JDBC資源
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return rentalVO;
//	}
//
//	@Override
//	public List<RentalVO> getAll() {
//		List<RentalVO> list = new ArrayList<RentalVO>();
//		RentalVO rentalVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				rentalVO = new RentalVO();
//				rentalVO.setrNo(rs.getInt("rNo"));
//				rentalVO.setrName(rs.getString("rName"));
//				rentalVO.setrPrice(rs.getBigDecimal("rPrice"));
//				rentalVO.setrSize(rs.getInt("rSize"));
//				rentalVO.setrColor(rs.getString("rColor"));
//				rentalVO.setrInfo(rs.getString("rInfo"));
//				rentalVO.setrStat(rs.getByte("rStat"));
//				rentalVO.setrCatNo(rs.getInt("rCatNo"));
//				list.add(rentalVO);
//			}
//
//			// 驅動程式錯誤
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//
//			// DB錯誤
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//
//			// 清理JDBC資源
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public Set<RentalVO> getRentalsByrCatNo(Integer rCatNo) {
//		Set<RentalVO> set = new LinkedHashSet<RentalVO>();
//		RentalVO rentalVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_RENTALS_BYRCATNO_STMT);
//			pstmt.setInt(1, rCatNo);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				rentalVO = new RentalVO();
//				rentalVO.setrNo(rs.getInt("rNo"));
//				rentalVO.setrName(rs.getString("rName"));
//				rentalVO.setrPrice(rs.getBigDecimal("rPrice"));
//				rentalVO.setrSize(rs.getInt("rSize"));
//				rentalVO.setrColor(rs.getString("rColor"));
//				rentalVO.setrInfo(rs.getString("rInfo"));
//				rentalVO.setrStat(rs.getByte("rStat"));
//				rentalVO.setrCatNo(rs.getInt("rCatNo"));
//				set.add(rentalVO);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return set;
//	}
//}