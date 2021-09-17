package KHS.dbService;

import java.sql.PreparedStatement;

import KHS.dbcommon.DBCommon;
import KHS.dto.bookDTO;



public class dbServiceImpl implements dbService{
	PreparedStatement ps;
	
	@Override
	public int warehousing(bookDTO dto) {
		String sql = "insert into stockDB values (?,?,?,?,?,?,?)";
		int result = 0 ;
		try {
			ps = DBCommon.con.prepareStatement(sql);
			ps.setString(1, dto.getBookName());
			ps.setString(2, dto.getBookPrice());
			ps.setString(3, dto.getAccount());
			ps.setInt(4, dto.getWarehousing());
			ps.setInt(5, 0);
			ps.setInt(6, 100); //dto.getStock()); stock 데이터를 DB에서 받아와야 함 !!
			ps.setString(7, dto.getDate());
			
			result = ps.executeUpdate();
			
			System.out.println("입고가 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int release(bookDTO dto) {
		String sql = "insert into stockDB values (?,?,?,?,?,?,?)";
		int result = 0 ;
		try {
			ps = DBCommon.con.prepareStatement(sql);
			ps.setString(1, dto.getBookName());
			ps.setString(2, dto.getBookPrice());
			ps.setString(3, dto.getAccount());
			ps.setInt(4, 0);
			ps.setInt(5, dto.getRelease());
			ps.setInt(6, 100); //dto.getStock()); // DB에서 재고량불러와서 출고량 뺴야함.
			ps.setString(7, dto.getDate());
			
			/*
			 if(dto.getStock() - dto.getRelease() < 0){ // 출고량이 재고량보다 많은 경우
			 	return result = 2;
		 	} 
			 */
			result = ps.executeUpdate(); // 정상적인 값이 입력된 경우 db에 입력
			System.out.println("출고가 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; // db 입력 오류 발생(0)
	}
}
