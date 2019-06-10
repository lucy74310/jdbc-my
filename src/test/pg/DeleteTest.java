package test.pg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean result = delete( 8L ) ;
		if( result ) {
			System.out.println("입력 성공! ");
		}

	}
	
	public static boolean delete(Long no) {
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			// 1. JDBC Driver class (PostgreSQL) loading
			Class.forName("org.postgresql.Driver");
			
			// 2. 연결하기
			String url = "jdbc:postgresql://192.168.1.190:5432/webdb";
			
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
			System.out.println("연결성공");
			
			// 3.statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "delete from author where no = "  + no;
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
