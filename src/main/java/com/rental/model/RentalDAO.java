package com.rental.model;

	import java.util.*;
	import java.sql.*;

	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

	public class RentalDAO implements RentalDAO_interface {

		// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

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
				con = ds.getConnection();
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

				con = ds.getConnection();
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
				con = ds.getConnection();
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

				con = ds.getConnection();
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
	}