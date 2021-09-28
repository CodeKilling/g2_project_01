package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Common;
import common.MemberDTO;

public class DBserviceImpl implements DBservice {
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public MemberDTO LoginCheck(String userId) {
		String sql = "select * from MEMBER where userID = ?";
		MemberDTO dto = null;
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setUserId(rs.getString("ID"));
				dto.setPwd(rs.getString("PWD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public MemberDTO FindId(String name) {
		MemberDTO dto = null;
		String sql = "select * from MEMBER where NAME = ?";
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setUserId(rs.getString("USERID"));
				dto.setName(rs.getString("NAME"));
				dto.setPhNum(rs.getString("PHONENUMBER"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public MemberDTO rePwd(String userId, String pwd) {
		MemberDTO dto = null;
		String sql = "update MEMBER SET PWD = ? where USERID = ?";
		try {

			dto = new MemberDTO();
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, userId);
			int r = ps.executeUpdate();

			if(r > 0 ) {
				dto.setPwd("PWD");
			}
			
			return dto;
		} catch (SQLException e) {
			return dto;
		}
	}

	@Override
	public boolean CheckID(String userId) {
		
		System.out.println("userId : " + userId);
		String sql = "select * from MEMBER where USERID = ?";

		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
