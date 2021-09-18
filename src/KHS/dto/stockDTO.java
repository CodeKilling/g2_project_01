package KHS.dto;

public class stockDTO {
	String bookName;
	int stock;
	
	public stockDTO(String bn, int st) {
		bookName = bn;
		stock = st;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
