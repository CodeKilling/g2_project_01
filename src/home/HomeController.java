package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import stats.StatsService;
import stats.StatsServiceImpl;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginService;
import login.LoginServiceImpl;


	
public class HomeController implements Initializable {
	@FXML DatePicker startDate, endDate;
	@FXML TableColumn bookName, price, accountName, memberName, inOut, resultTotal, total, recordDate;
	LoginService ls;
	Parent root = null;
	StatsService ss = null;
	public void setRoot(Parent p) {
		this.root = p;
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
		Common.MyConnection();
		ls = new LoginServiceImpl();
		ss = new StatsServiceImpl();
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
