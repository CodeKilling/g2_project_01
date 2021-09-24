package account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AccountDTO;
import common.Common;

public class AccountDBServiceImpl implements AccountDBService {

	String sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public void addAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AccountDTO> viewAccount() {
		ArrayList<AccountDTO> arr = new ArrayList<AccountDTO>();
		AccountDTO dto = null;
		sql = "select *"
			+ "from account";
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new AccountDTO();
				dto.setName(rs.getString("name"));
				dto.setContactNumber(rs.getString("CONTACTNUMBER"));
				dto.setWorkerName(rs.getString("WORKERNAME"));
				dto.setId(rs.getInt("id"));
				
				arr.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

}
