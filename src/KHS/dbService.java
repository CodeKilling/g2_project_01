package KHS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.Common;

public class dbService {
	String sql;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public dbService() {
		Common.MyConnection();
	}
	
	public ArrayList<bookDTO> getDB(){	// BOOK db에 있는 값 가져오기	
		sql = "select * from BOOK";
		ArrayList<bookDTO> list = new ArrayList<bookDTO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@210.221.253.215:1521:xe","g2","oracle");
			System.out.println("연결성공 : " + con);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				bookDTO dto = new bookDTO();
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
}
