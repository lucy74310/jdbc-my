package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
	
	
	public BookVo get(Long no) {
		//검색
		return null;
	}
	
	public List<BookVo> get(BookVo vo) {
		//검색 
		return null;
	}
	
	public Boolean update(BookVo vo) {
		//대여,반납
		
		return null;
	}
	
	//오버로드
	public Boolean update(Long no, String status) {
		//대여,반납
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();

			
			// 3.statement 객체 생성
			String sql = "update book set status=? where no=? ";
			pstmt = conn.prepareStatement(sql);
			
			
			//3-1. 데이터 바인딩
			pstmt.setString(1,  status);
			pstmt.setLong(2, no);
			
			
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
	
	public Boolean insert(BookVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();

			
			// 3.statement 객체 생성
			String sql = "insert into book values "
					+ "(null, ?, '대여가능', ? );"; 
	
			pstmt = conn.prepareStatement(sql);
			
			
			//3-1. 데이터 바인딩
			pstmt.setString(1,  vo.getTitle());
			pstmt.setLong(2,  vo.getAuthorNo());
			
			
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
	public List<BookVo> getList() {
		List<BookVo> result = new ArrayList<BookVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			
			// 3.statement 객체 생성
			String sql = "select a.title, b.name, a.status " + 
						 "  from book a, author b " + 
					     " where a.author_no = b.no " + 
					   "order by a.no asc " ;
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL문 실행
			
			rs = pstmt.executeQuery();
			
			
			// 5. 결과 가져오기
			while( rs.next() ) {
				String title = rs.getString(1);
				String authorName = rs.getString(2);
				String status = rs.getString(3);
				
				BookVo vo = new BookVo();
				vo.setTitle(title);
				vo.setAuthorName(authorName);
				vo.setStatus(status);
				
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
