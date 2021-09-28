package account;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AccountDTO;
import common.Common;

public class AccountDBServiceImpl implements AccountDBService {

	String sql = null;
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = -1;

	private ArrayList<AccountDTO> createList(ResultSet rs) {
		ArrayList<AccountDTO> arr = new ArrayList<AccountDTO>();
		AccountDTO dto = null;
		try {
			while (rs.next()) {
				dto = new AccountDTO();
				dto.setName(rs.getString("거래처명"));
				dto.setContactNumber(rs.getString("연락처"));
				dto.setWorkerName(rs.getString("담당자명"));
				dto.setId(rs.getInt("id"));
				arr.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public boolean chkName(String _name) {
		sql = "select account.name from account where account.name = ?;";
		try {
			ps = Common.con.prepareStatement(sql);
			ps.setString(1, _name);
			rs = (ResultSet)ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("name").equals(_name)) {
					return false;
				}else{
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<AccountDTO> addAccount(String accountName, String contactNumber, String workerName) {
		ArrayList<AccountDTO> arr = null;
		sql = "begin procedure_accountadd(?,?,?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setString(1, accountName);
			cs.setString(2, contactNumber);
			cs.setString(3, workerName);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(4);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<AccountDTO> deleteAccount(int selectionID) {
		ArrayList<AccountDTO> arr = null;
		sql = "begin procedure_accountdelete(?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setInt(1, selectionID);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(2);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<AccountDTO> modifyAccount(int selectionID, String accountName, String contactNumber,
			String workerName) {
		ArrayList<AccountDTO> arr = null;
		sql = "begin procedure_accountmodify(?,?,?,?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setInt(1, selectionID);
			cs.setString(2, accountName);
			cs.setString(3, contactNumber);
			cs.setString(4, workerName);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(5);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<AccountDTO> viewAccount() {
		ArrayList<AccountDTO> arr = new ArrayList<AccountDTO>();
		AccountDTO dto = null;
		sql = "select * from account order by account.id";
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
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
