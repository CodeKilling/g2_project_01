package stats;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Common;

public class StatsDB {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private String sql = null;
	private int result = 0;
	public void test() {
		String sql = "begin procedure_stats(?,?,?); end;";
	      try {
	         CallableStatement cs = Common.con.prepareCall(sql);
	         cs.setString(1, "20210919"); //검색시작일
	         cs.setString(2, "99991231"); //검색종료일
	         cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
	         cs.execute();
	         ResultSet rs = (ResultSet)cs.getObject(3);
	         while(rs.next()) {
	            System.out.println(rs.getString("도서명"));
	            System.out.println(rs.getInt("결과재고"));
	            System.out.println(rs.getInt("현재재고"));
	            System.out.println(rs.getInt("등록일"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
}
