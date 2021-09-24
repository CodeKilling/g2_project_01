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
		Label bookName = (Label)root.lookup("#bookName");
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
		TextField inputStock = (TextField)root.lookup("#inputStock");
		int stock = Integer.parseInt(inputStock.getText());
		
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		String FommatDate;
		if(Objects.equals(eventDate.getValue(),null)) { // 날짜를 입력하지 않은 경우 에러가 발생하지 않도록 함
			FommatDate = null;
		}else {
			FommatDate = eventDate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		}
		
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		
		String sql = "begin procedure_snr(?,?,?,?,?,?); end;";
		
		int realStock = findStock(); // 현재 테이블뷰에 선택된 책의 실제 재고량을 저장하는 변수
		int bookId = findBookId(); // 현재 테이블뷰에 선택된 책의 id(시퀀스) 값
		int AccId = findAccId(); // 현재 선택된 거래처의 id(시퀀스) 값
		
		if(realStock + stock < 0) { // 현재 저장된 책 재고보다 많이 출고하려고 할 경우 경고 메세지 출력
			// 출고의 경우 stock의 값이 음수(-)로 들어옴으로 실제량+입력량의 합으로 계산
			Common.MyAlert("실제 재고량보다 많은 출고량을 입력했습니다.");
		}else if(inputStock.getText().equals(null) || Objects.equals(eventDate.getValue(),null)
				|| cmbAccount.getValue().toString().equals(null)){ // 입출고량, 날짜, 거래처 칸중 하나라도 입력하지 않은 경우
			Common.MyAlert("입력하지 않은 칸이 있습니다.");
		}else {
		      try {
		         CallableStatement cs = Common.con.prepareCall(sql);
		         cs.setInt(1, bookId);
		         cs.setInt(2, 1); // 로그인한 멤버 id(시퀀스 값) from MemberDTO ?
		         cs.setInt(3, AccId); // 거래처의 id 값
		         cs.setInt(4, stock); // #inputStock
		         cs.setString(5, FommatDate); // #eventDate
		         cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(6);
		         while(rs.next()) {
		            System.out.println(rs.getString("name"));
		            System.out.println(rs.getInt("total"));
		         }
		         getTable(); // 갱신된 정보로 테이블을 셋팅함
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
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
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
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
	
}
