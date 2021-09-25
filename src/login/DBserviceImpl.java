package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Common;
import common.MemberDTO;

public class DBserviceImpl implements DBservice {

	@Override
	public MemberDTO LoginCheck(String userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

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
		PreparedStatement ps = null;
		ResultSet rs = null;
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
				dto.setPhonenumber(rs.getString("PHONENUMBER"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return dto;
	}

}
