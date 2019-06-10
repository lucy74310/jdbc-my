package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	public List<EmployeeVO> getList(String keyword) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeVO> result = new ArrayList<EmployeeVO>();
		try {
			
			// 1. JDBC Driver class (Maria DB) loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.190:3307/employees";
			
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			
			System.out.println("연결성공");
			
			// 3.statement 객체 생성
			//stmt = conn.createStatement();
			
			// 4. SQL문 실행
//			String sql = 
//				    "select emp_no, first_name, last_name, hire_date " + 
//					"  from employees " + 
//					" where first_name like '%" + keyword + "%' " + 
//					"	or first_name like '%" + keyword + "%' ";
			// 보안상의 이유로 string + 하는 식으로 문자열 만드는 건 옳지 않다. - SQLInjection 
			// 값을 바인딩 하는 쿼리를 사용해야 한다 (?) 다음과 같이 
			
			String sql = 
				    "select emp_no, first_name, last_name, hire_date " + 
					"  from employees " + 
					" where first_name like ?" + 
					"	or first_name like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//바인딩
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			
			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();
			
			// 5. 결과 가져오기
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVO vo = new EmployeeVO();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
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
				/*
				 * if( stmt != null ) { stmt.close(); }
				 */
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
		return result;

		
	}
}
