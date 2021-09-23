package KHS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
	public void test() throws ClassNotFoundException {
		Connection con;
		String sql = "begin procedure_snr(?,?,?,?,?,?); end;";
	      try {
	    	 Class.forName("oracle.jdbc.driver.OracleDriver");
			 con = DriverManager.getConnection("jdbc:oracle:thin:@210.221.253.215:1521:xe","g2","oracle");
			 System.out.println("연결 되었습니다 : " + con);
	         CallableStatement cs = con.prepareCall(sql);
	         cs.setInt(1, 2);
	         cs.setInt(2, 1);
	         cs.setInt(3, 1);
	         cs.setInt(4, 20);
	         cs.setString(5, "20210919");
	         cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
	         cs.execute();
	         ResultSet rs = (ResultSet)cs.getObject(6);
	         while(rs.next()) {
	            System.out.println(rs.getString("name"));
	            System.out.println(rs.getInt("total"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
}
