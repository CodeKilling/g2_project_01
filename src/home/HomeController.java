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

	@FXML TableColumn bookName, price, accountName, memberName, inOut, resultTotal, total, recordDate;
	//@FXML TableColumn fxaccountName, fxaccountWorkerName, fxaccountContactNumber;
	@FXML TableColumn fxCellBookName, fxCellBookTotal;
	
	
	inOutService IOSvc;
	ArrayList<BookDTO> dto;

	
	Parent root = null;
	
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
		fxCellBookName.setCellValueFactory(new PropertyValueFactory("name"));
		fxCellBookTotal.setCellValueFactory(new PropertyValueFactory("total"));
	}
	
	public void selectTable(MouseEvent event) {
		Label bookName = (Label)root.lookup("#lbbookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		Label writerName = (Label)root.lookup("#writerName");
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		//int sel = stockTable.getSelectionModel().getSelectedIndex();
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
		bookName.setText(data.getName());
		bookPrice.setText(data.getPrice());
		writerName.setText(data.getWriter());
	}
	
	

}
