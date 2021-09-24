package KHS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.BookDTO;
import common.Common;
import javafx.scene.control.TextField;

public class dbService {
	String sql;
	PreparedStatement ps;
	ResultSet rs;
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
		return id;
	}
	
	/*
	 *  로그인기능에서 id 값 받아오기
	 *  ps.setString(1, 입력된 id값) 찾아 넣기
	public int getMemId() {
		sql = "select ID from MEMBER where USERID = ?";
		int id = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, );
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("ID");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	*/
	
}
