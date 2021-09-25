package book;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BookDTO;
import common.Common;

public class BookDBServiceImpl implements BookDBService{

	String sql = null;
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	
	private ArrayList<BookDTO> createList(ResultSet rs) {
		ArrayList<BookDTO> arr = new ArrayList<BookDTO>();
		BookDTO dto = null;
		try {
			while (rs.next()) {
				dto = new BookDTO();
				dto.setName(rs.getString("도서명"));
				dto.setPrice(rs.getString("가격"));
				dto.setWriter(rs.getString("작가명"));
				dto.setTotal(rs.getInt("재고"));
				dto.setId(rs.getInt("id"));
				arr.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	@Override
	public ArrayList<BookDTO> bookAdd(String bookName, String price, String writer) {
		ArrayList<BookDTO> arr = null;
		sql = "begin procedure_bookadd(?,?,?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setString(1, bookName);
			cs.setString(2, price);
			cs.setString(3, writer);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(4);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<BookDTO> bookDelete(int selectionID) {
		ArrayList<BookDTO> arr = null;
		sql = "begin procedure_bookdelete(?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setInt(1, selectionID);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(2);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<BookDTO> bookModify(int selectionID, String bookName, String price, String writer) {
		ArrayList<BookDTO> arr = null;
		sql = "begin procedure_bookmodify(?,?,?,?,?); end;";
		try {
			cs = Common.con.prepareCall(sql);
			cs.setInt(1, selectionID);
			cs.setString(2, bookName);
			cs.setString(3, price);
			cs.setString(4, writer);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(5);
			arr = this.createList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<BookDTO> bookView() {
		ArrayList<BookDTO> arr = new ArrayList<BookDTO>();
		BookDTO dto = null;
		sql = "select * from book order by book.id";
		try {
			ps = Common.con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new BookDTO();
				dto.setName(rs.getString("name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getString("price"));
				dto.setTotal(rs.getInt("total"));
				dto.setId(rs.getInt("id"));

				arr.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
}
