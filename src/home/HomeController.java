package home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import KHS.dbService;
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
	dbService db = new dbService();
	Parent root = null;
	inOutService IOSvc;
	ArrayList<BookDTO> dto;
	@FXML TableColumn bookNameColumn, stockColumn;
	
	public void setRoot(Parent p) {
		this.root = p;
		IOSvc.setRoot(p);
		setColumn();
		IOSvc.getTable();
		IOSvc.setAccCmb();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IOSvc = new inOutService();
		dto = new ArrayList<BookDTO>();
	}
	
	public void inOut() {
		IOSvc.inOutService();
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
		//int sel = stockTable.getSelectionModel().getSelectedIndex();
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
		bookName.setText(data.getName());
		bookPrice.setText(data.getPrice());
		writerName.setText(data.getWriter());
	}
	
	

}
