package KHS;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import common.BookDTO;
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
		TableView<BookDTO> stockTable = (TableView)root.lookup("#stockTable");
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
		
		//cmbAccount.setValue(null); // combobox를 FXCollections.observableArrayList 로 초기화 하기
	}
	
	
}
