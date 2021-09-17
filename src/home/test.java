package home;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import KHS.dbcommon.DBCommon;

public class test {

	public static void main(String[] args) {
			DBCommon.setDBConnection();
			ArrayList list = new ArrayList();
			String sql = "select bookName,stock from stockDB";
			try {
				PreparedStatement ps;
				ResultSet rs;
				ps = DBCommon.con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					list.add(rs.getString(1));
					list.add(rs.getInt(2));
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(list.get(2));
			
		
	}

}
