package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			// 1. JDBC Driver class (Maria DB) loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.190:3307/webdb";
			
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
			System.out.println("연결성공");
			
			// 3.statement 객체 생성
			String sql = "select no, name from department";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL문 실행
			
			rs = pstmt.executeQuery();
			
			
			// 5. 결과 가져오기
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				System.out.println(no + ":" + name);
				 
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( rs != null ) {
					rs.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
