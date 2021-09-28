package inOut;

import java.util.ArrayList;

import common.BookDTO;

public interface DBService {
	public ArrayList<BookDTO> getDB();
	public ArrayList getAccName();
	public int getAccId(String name);
	public int getBookId(String name);
	public ArrayList<BookDTO> renewTable();
}
