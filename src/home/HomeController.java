package home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import KHS.inOutService;
import common.BookDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class HomeController implements Initializable{

	Parent root = null;
	inOutService IOSvc;
	ArrayList<BookDTO> dto;
	@FXML TableColumn bookNameColumn, stockColumn;
	
	public void setRoot(Parent p) {
		this.root = p;
		IOSvc.setRoot(p);
		setColumn();
		IOSvc.getTable();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IOSvc = new inOutService();
		dto = new ArrayList<BookDTO>();
	}
	
	public void inOut() {
		// bookDTO 에서 id값을 받아 1번 처리
		// 2번째 = 현재 로그인한 멤버의 id 값(시퀀스)
		// 3번째 = 선택된 거래처의 id값  >> 거래처 cmb 값을 넣는게 선행되어야 함 (account 테이블에 name 컬럼)
		// 4번째 = 입출고량 >> #inputStock textfield 값을 가져옴
		// 5번째 = datepicker에서 선택한 날짜.toString()
	}
	
	public void cancel() {
		IOSvc.cancel();
	}
	
	
	public void setColumn() {
		bookNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		stockColumn.setCellValueFactory(new PropertyValueFactory("total"));
	}
	
	public void selectTable(MouseEvent event) {
		Label bookName = (Label)root.lookup("#bookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		Label writerName = (Label)root.lookup("#writerName");
		TableView<BookDTO> stockTable = (TableView)root.lookup("#stockTable");
		int sel = stockTable.getSelectionModel().getSelectedIndex();
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
		bookName.setText(data.getName());
		bookPrice.setText(data.getPrice());
		writerName.setText(data.getWriter());
	}
	
	

}
