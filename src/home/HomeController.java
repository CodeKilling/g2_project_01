package home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import KHS.bookDTO;
import KHS.inOutService;
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
	ArrayList<bookDTO> dto;
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
		dto = new ArrayList<bookDTO>();
	}
	
	public void plusStock() {
		
	}
	
	public void minusStock() {
	
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
		TableView<bookDTO> stockTable = (TableView)root.lookup("#stockTable");
		int sel = stockTable.getSelectionModel().getSelectedIndex();
		bookDTO data = stockTable.getSelectionModel().getSelectedItem();
		bookName.setText(data.getName());
		bookPrice.setText(data.getPrice());
		writerName.setText(data.getWriter());
	}
	
	

}
