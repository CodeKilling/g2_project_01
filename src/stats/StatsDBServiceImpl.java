package stats;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Common;
import common.snrDTO;

public class StatsDBServiceImpl implements StatsDBService{
	PreparedStatement ps;
	ResultSet rs;
	private String sql = null;
	public ArrayList<snrDTO> search(String sd, String ed) {
		ArrayList<snrDTO> list = new ArrayList<snrDTO>();
		sql = "begin procedure_stats(?,?,?); end;";
	      try {
	         CallableStatement cs = Common.con.prepareCall(sql);
	         cs.setString(1, sd); //검색시작일
	         cs.setString(2, ed); //검색종료일
	         cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
	         cs.execute();
	         ResultSet rs = (ResultSet)cs.getObject(3);
	         while(rs.next()) {
	        	snrDTO dto = new snrDTO();
	        	dto.setBookName(rs.getString("도서명"));
	        	dto.setPrice(rs.getString("가격"));
	        	dto.setAccountName(rs.getString("거래처명"));
	        	dto.setMemberName(rs.getString("입출고등록자"));
	        	dto.setInOut(rs.getInt("입출고량"));
	        	dto.setResultTotal(rs.getInt("결과재고"));
	        	dto.setTotal(rs.getInt("현재재고"));
	        	dto.setRecordDate(rs.getString("등록일"));
	        	list.add(dto);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return list;
	}
	public ArrayList<String> getCombo() {
		ArrayList<String> combo = new ArrayList<String>();
		sql = "select distinct name from book";
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				combo.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return combo;
	}
}
