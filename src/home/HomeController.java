package home;

import java.net.URL;
import java.util.ResourceBundle;

import account.AccountService;
import account.AccountServiceImpl;
import common.Common;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import login.LoginService;
import login.LoginServiceImpl;


	
public class HomeController implements Initializable {
	LoginService ls = null;
	AccountService as = null;
	TabPane tabpane = null;
	Parent root = null;
	public void setRoot(Parent p) {
		this.root = p;
		as.setRoot(p);
		
		tabpane = (TabPane)root.lookup("#fxTabPane");
		tabpane.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
			            switch(t1.getText()) {
			            case "HOME":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            case "도서관리":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            case "도서입출고":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            case "거래처관리":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	as.setView();
			            	break;
			            case "입출고현황":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            }
			        }
			    }
			);
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
		as = new AccountServiceImpl();
	}

}
