package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.Common;
import common.MemberDTO;

public class DBserviceImpl implements DBservice {

	@Override
	public MemberDTO LoginCheck(String userId) {
		Connection con = null;
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

}
