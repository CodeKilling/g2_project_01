package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import login.LoginService;
import login.LoginServiceImpl;
import login.FindServiceImpl;
import login.FindService;


	
public class HomeController implements Initializable {
	LoginService ls;
	FindService fs;
	Parent root = null;

	public void setRoot(Parent p) {
		this.root = p;
		ls.setRoot(p);
		fs.setRoot(p);
	}
	public void login() {
		ls.Login(); 
	}
	public void findId() {
		fs.findId();
	}
	public void BTNfindClicked() {
		fs.BTNfindClicked();
	}
	public void rePwd() {
		fs.rePwd();
	}
	public void homecancel() {
		fs.homecancel();
	}
	public void BTNresetClick() {
		fs.BTNreset();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		ls = new LoginServiceImpl();
		fs = new FindServiceImpl();
	}

}
