package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			
			// 1. JDBC Driver class (Maria DB) loading
			Class.forName("driver.MyDriver");
			
			// 2. 연결하기
			String url = "jdbc:mydb://192.168.1.190:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
			System.out.println("연결성공 : " + conn);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
