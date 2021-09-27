package KHS;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import common.BookDTO;
import common.Common;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class inOutService {
	Parent root;
	dbService db;
	ArrayList<BookDTO> dto;
	
	public inOutService() {
		db = new dbService();
		dto = new ArrayList<BookDTO>();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void getTable() { 
		dto = db.getDB(); // book DB의 전체정보를 DTO 에 저장
		viewStockTable(dto); // 해당 정보를 테이블뷰에 셋팅함
	}
	
	public void viewStockTable(ArrayList<BookDTO> dto) {
		ObservableList<BookDTO> view = FXCollections.observableArrayList();
		for(int i = 0; i < dto.size(); i++) {
			view.add(dto.get(i));
		}
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		stockTable.setItems(view);
	}
	
	public void cancel() {
		System.out.println("입력값 초기화");
		Label bookName = (Label)root.lookup("#lbbookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		Label writerName = (Label)root.lookup("#writerName");
		
		TextField inputStock = (TextField)root.lookup("#inputStock");
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		
		bookName.setText(""); bookPrice.setText(""); writerName.setText("");
		inputStock.setText("");
		
		LocalDate date = LocalDate.now();
		eventDate.setValue(date); // DatePicker 오늘 날짜값을 넣음
		
		//setAccCmb();
		cmbAccount.setValue("거래처");
		
	}
	
	public void setAccCmb() {
		ArrayList list = new ArrayList();
		list = db.getAccName();
		ObservableList accList = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++) {
			accList.add(list.get(i));
		}
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		cmbAccount.setItems(accList);
	}
		

	
	// bookDTO 에서 id값을 받아 1번 처리
			// 2번째 = 현재 로그인한 멤버의 id 값(시퀀스)
			// 3번째 = 선택된 거래처의 id값  >> 거래처 cmb 값을 넣는게 선행되어야 함 (account 테이블에 name 컬럼)
			// 4번째 = 입출고량 >> #inputStock textfield 값을 가져옴
			// 5번째 = datepicker에서 선택한 날짜.toString()

	public void inOutService() {

		int stock = txtStock(); // JavaFx inputStock 에 입력한 숫자를 가져옴
		// inputStock에 아무 값이 입력되지 않았다면 0 을 반환하도록 함
		
		String FormatDate = eventDate();
		// JavaFx DatePicker에 입력된 날짜를 미리 지정한 날짜 형식으로 가져옴
		// 날짜가 선택되지 않은 경우에는 null 값을 가짐
		
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		int realStock = findStock(); // 현재 테이블뷰에 선택된 책의 실제 재고량을 저장하는 변수
		
		if(realStock + stock < 0) { // 현재 저장된 책 재고보다 많이 출고하려고 할 경우 경고 메세지 출력
			// 출고의 경우 stock의 값이 음수(-)로 들어옴으로 실제량+입력량의 합으로 계산
			Common.MyAlert("실제 재고량보다 많은 출고량을 입력했습니다.");
		}else if(stock == 0 || FormatDate.equals(null)
				|| Objects.equals(cmbAccount.getValue(),null)){ 
			// 입출고량, 날짜, 거래처 칸중 하나라도 입력하지 않은 경우
			Common.MyAlert("입력하지 않은 칸이 있습니다.");
		}else {
		    ArrayList<BookDTO> dto = db.renewTable();
			viewStockTable(dto);
		}
	}
	
	public String getAccCmb() { // javafx에서 선택된 cmb의 값 가져오기
		String name;
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		if(Objects.equals(cmbAccount.getValue(), null)) {
			name = null;
		}else {
			name = cmbAccount.getValue().toString();
		}
		return name;
	}
	
	public int findAccId() {
		String name = getAccCmb();
		int AccId = 0;
		try{
			AccId = db.getAccId(name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("선택된 거래처 id : " + AccId);
		return AccId;
	}
	
	
	public String getBookName() {
		String name;
		System.out.println("테이블을 못찾음");
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		System.out.println("테이블 찾기까진 됨");
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
		System.out.println("테이블에서 데이터를 선택하는 것이 안됨.");
		name = data.getName().toString();
		System.out.println("책이름 : " + name);
		return name;
	}
	
	public int findBookId() {
		String name = getBookName();
		int bookId = 0;
		try{
			bookId = db.getBookId(name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("선택된 책의 id 값 : " + bookId);
		return bookId;
	}
	
	public int findStock() {	
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		int bookStock = 0;
		try {
			BookDTO data = stockTable.getSelectionModel().getSelectedItem();
			bookStock = data.getTotal();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bookStock;
	}
	
	// 0927 이하 작성
	
	public int txtStock() { // javaFx textField에 입력한 재고 입력값을 반환하는 메소드
		int stock;
		TextField inputStock = (TextField)root.lookup("#inputStock");
		if(Objects.equals(inputStock.getText(),null) || Objects.equals(inputStock.getText(),"")) {
			stock = 0;
		}else {
			stock = Integer.parseInt(inputStock.getText());
		}
		return stock;
	}
	
	public String eventDate() {
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		String FormatDate;
		if(Objects.equals(eventDate.getValue(),null)) { // 날짜를 입력하지 않은 경우 에러가 발생하지 않도록 함
			FormatDate = null;
		}else {
			FormatDate = eventDate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		}
		return FormatDate;
	}
}
