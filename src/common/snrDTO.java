package common;


import java.sql.Date;

public class snrDTO {
	private String bookName; //도서명
	private String price; //가격
	private String accountName; //거래처명
	private String memberName; //입출고자명
	private int inOut; //입출고량
	private int resultTotal; //결과재고
	private int total; //현재재고
	private String recordDate; //일자
	private int id;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getInOut() {
		return inOut;
	}
	public void setInOut(int inOut) {
		this.inOut = inOut;
	}
	public int getResultTotal() {
		return resultTotal;
	}
	public void setResultTotal(int resultTotal) {
		this.resultTotal = resultTotal;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
