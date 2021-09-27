package KHS;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BookDTO;
import common.Common;
import javafx.scene.Parent;

public class dbService{
	String sql;
	PreparedStatement ps;
	ResultSet rs;
	Parent root;
	inOutService IOSvc;
	
	public dbService() {
		//Common.MyConnection();
	}
	
	
	public ArrayList<BookDTO> getDB(){	// BOOK db에 있는 값 가져오기	
		sql = "select * from BOOK";
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setName(rs.getString("NAME"));
				dto.setPrice(rs.getString("PRICE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTotal(rs.getInt("TOTAL"));
				dto.setId(rs.getInt("ID"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getDB() 메소드 에러");
		}
		return list;
	}
	
	public ArrayList getAccName() { // 가져온 거래처 이름을 list로 저장
		sql = "select NAME from ACCOUNT";
		ArrayList list = new ArrayList();
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("NAME"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getAccId(String name) {
		sql = "select ID from ACCOUNT where name = ?";
		int id = 0;
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("ID");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public int getBookId(String name) {
		sql = "select ID from BOOK where name = ?";
		int id = 0;
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("ID");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("책의 id 값 : " + id);
		return id;
	}
	

	// 0927 이하 작성
	
	public ArrayList<BookDTO> renewTable() {
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		IOSvc = new inOutService();
		int bookId = IOSvc.findBookId(); // 현재 테이블뷰에 선택된 책의 id(시퀀스) 값
		int AccId = IOSvc.findAccId(); // 현재 선택된 거래처의 id(시퀀스) 값
		
		sql = "begin procedure_snr(?,?,?,?,?,?); end;";
		 try {
	         CallableStatement cs = Common.con.prepareCall(sql);
	         cs.setInt(1, bookId);
	         if(Common.sessionID < 0) {
	        	 Common.MyAlert("로그인 하고 테스트 하세요.");
	        	 // 이 조건문 차후 필요없어짐. 차후 삭제 할것.
	         }
	         cs.setInt(2, Common.sessionID); // 로그인한 멤버 id(시퀀스 값) from MemberDTO ?
	         cs.setInt(3, AccId); // 거래처의 id 값
	         cs.setInt(4, IOSvc.txtStock()); // #inputStock
	         cs.setString(5, IOSvc.eventDate()); // #eventDate
	         cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
	         cs.execute();
	         ResultSet rs = (ResultSet)cs.getObject(6);
	         while(rs.next()) {
	            System.out.println(rs.getString("name"));
	            System.out.println(rs.getInt("total"));
	            
	            BookDTO dto = new BookDTO();
	            dto.setName(rs.getString("NAME"));
				dto.setPrice(rs.getString("PRICE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTotal(rs.getInt("TOTAL"));
				dto.setId(rs.getInt("ID"));
				list.add(dto);	// 프로시저 내에서 갱신된 bookTable의 정보를 list에 저장함
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		 return list;
	}
	

}
