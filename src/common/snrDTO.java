package common;

import java.sql.Date;

public class snrDTO {
	private Number bookId;
	private Number memberId;
	private Number accountId;
	private Number inComing;
	private Number outGoing;
	private Number total;
	private Date date;
	private Number id;
	public Number getBookId() {
		return bookId;
	}
	public void setBookId(Number bookId) {
		this.bookId = bookId;
	}
	public Number getMemberId() {
		return memberId;
	}
	public void setMemberId(Number memberId) {
		this.memberId = memberId;
	}
	public Number getAccountId() {
		return accountId;
	}
	public void setAccountId(Number accountId) {
		this.accountId = accountId;
	}
	public Number getInComing() {
		return inComing;
	}
	public void setInComing(Number inComing) {
		this.inComing = inComing;
	}
	public Number getOutGoing() {
		return outGoing;
	}
	public void setOutGoing(Number outGoing) {
		this.outGoing = outGoing;
	}
	public Number getTotal() {
		return total;
	}
	public void setTotal(Number total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	
}
