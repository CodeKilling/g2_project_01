package book;

import java.util.ArrayList;

import common.BookDTO;

public interface BookDBService {
	public ArrayList<BookDTO> bookAdd(String bookName, String price, String writer);
	public ArrayList<BookDTO> bookDelete(int selectionID);
	public ArrayList<BookDTO> bookModify(int selectionID, String bookName, String price, String writer);
	public ArrayList<BookDTO> bookView();
}
