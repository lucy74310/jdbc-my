package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;

public class AuthorDao {
	public Boolean insert(AuthorVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();

			
			// 3.statement 객체 생성
			String sql = "insert into author values(null, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			
			//3-1. 데이터 바인딩
			pstmt.setString(1,  vo.getName());
			
			
			// 4. SQL문 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}
	public List<AuthorVo> getList() {
		List<AuthorVo> result = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			
			// 3.statement 객체 생성
			String sql = "select no, name from author";
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL문 실행
			
			rs = pstmt.executeQuery();
			
			
			// 5. 결과 가져오기
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);
				 
			}
			
			
			
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
		
		
		return result;
	}
	
	
	
	private Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.190:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
}
