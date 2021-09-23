package KHS;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class inOutService {
	Parent root;
	dbService db;
	ArrayList<bookDTO> dto;
	
	public inOutService() {
		db = new dbService();
		dto = new ArrayList<bookDTO>();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void getTable() { 
		dto = db.getDB(); // book DB의 전체정보를 DTO 에 저장
		viewStockTable(dto); // 해당 정보를 테이블뷰에 셋팅함
	}
	
	public void viewStockTable(ArrayList<bookDTO> dto) {
		ObservableList<bookDTO> view = FXCollections.observableArrayList();
		for(int i = 0; i < dto.size(); i++) {
			view.add(dto.get(i));
		}
		TableView<bookDTO> stockTable = (TableView)root.lookup("#stockTable");
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
		eventDate.setValue(null); // DatePicker 에 아무값도 입력되지 않은 상태
		
		//★★★ cmbAccount.setValue(); // combobox를 FXCollections.observableArrayList 로 초기화 하기
	}
}
