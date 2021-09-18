package KHS.dto;

public class bookDTO {
	String bookName;
	String bookPrice;
	String account;
	// String id; // 작업자 id 로그인 기능 구현 후 만들기
	int warehousing; // 입고량
	int release; // 출고량
	int stock; // 재고량
	String date; // 변동일
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getWarehousing() {
		return warehousing;
	}
	public void setWarehousing(int warehousing) {
		this.warehousing = warehousing;
	}
	public int getRelease() {
		return release;
	}
	public void setRelease(int release) {
		this.release = release;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
