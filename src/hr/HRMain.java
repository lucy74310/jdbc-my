package hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("검색어>");
		String keyword = scanner.nextLine();
		
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVO> list = dao.getList(keyword);
		
		for(EmployeeVO vo : list ) {
			System.out.println(vo);
		}
	    
	}

}
