package home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import KHS.dbService;
import KHS.inOutService;
import common.BookDTO;
import common.Common;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import login.LoginService;
import login.LoginServiceImpl;
import stats.StatsService;
import stats.StatsServiceImpl;

public class HomeController implements Initializable{
	dbService db = new dbService();
	Parent root = null;
	inOutService IOSvc;
	ArrayList<BookDTO> dto;
	LoginService ls;
	StatsService ss = null;
	
	@FXML DatePicker startDate, endDate;
	@FXML TableColumn bookName, price, accountName, memberName, inOut, resultTotal, total, recordDate, bookNameColumn, stockColumn;
	
	
	public void setRoot(Parent p) {
		this.root = p;
		IOSvc.setRoot(p);
		setColumn();
		IOSvc.getTable();
		IOSvc.setAccCmb();
		ss.setRoot(p, startDate, endDate);
		StatsSetColumn();
	}
	
	public void login() {
		TextField id = (TextField) root.lookup("#fxId");
		PasswordField pwd = (PasswordField) root.lookup("#fxPwd");
		ls.Login(id.getText(), pwd.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IOSvc = new inOutService();
		dto = new ArrayList<BookDTO>();
		Common.MyConnection();
		ls = new LoginServiceImpl();
		ss = new StatsServiceImpl();
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
	
	public void StatsSetColumn() {
		bookName.setCellValueFactory(new PropertyValueFactory("bookName"));
		price.setCellValueFactory(new PropertyValueFactory("price"));
		accountName.setCellValueFactory(new PropertyValueFactory("accountName"));
		memberName.setCellValueFactory(new PropertyValueFactory("memberName"));
		inOut.setCellValueFactory(new PropertyValueFactory("inOut"));
		resultTotal.setCellValueFactory(new PropertyValueFactory("resultTotal"));
		total.setCellValueFactory(new PropertyValueFactory("total"));
		recordDate.setCellValueFactory(new PropertyValueFactory("recordDate"));
	}
	public void todaySearch() {
		ss.todaySearch();
	}
	public void allSearch() {
		ss.allSearch();
	}

}
