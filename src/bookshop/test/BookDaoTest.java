package bookshop.test;

import java.util.List;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		
//		insertTest("아리랑" , 2L);
//		insertTest("젊은그들" , 3L);
//		insertTest("아프니깐 청춘이다" , 4L);
//		insertTest("귀천" , 5L);
//		insertTest("태백산맥" , 6L);
//		insertTest("풀하우스" , 7L);
		
		getListTest();
	}
	public static void insertTest(String title, Long authorNo) { 
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setAuthorNo(authorNo);
		new BookDao().insert(vo);
	}
	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();
		
		for( BookVo vo : list ) {
			System.out.println(vo);
		}
	}

}
