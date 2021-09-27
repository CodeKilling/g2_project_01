package dbservice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.Common;
import dto.MembershipDTO;

public class DBServiceImpl implements DBService{

	PreparedStatement ps=null;
	ResultSet rs=null;
	
	@Override
	public int checkInfo(String userId) {
		System.out.println("입력 정보 확인");
		String sql = "SELECT * FROM member WHERE USERID = ?";

		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			if(rs.next()) {
				System.out.println("동일한 아이디 존재");
			}else {
				return 1;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertMember(MembershipDTO dto) {
		System.out.println("DB에 회원가입 합니다.");
		String sql = "INSERT INTO member(USERID,NAME,PWD,PHONENUMBER) VALUES(?,?,?,?)";
		
		int result = 0;
		
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getPhNum());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
