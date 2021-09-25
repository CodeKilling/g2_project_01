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
	FindService ss;
	Parent root = null;

	public void setRoot(Parent p) {
		this.root = p;
		ls.setRoot(p);
		ss.setRoot(p);
	}
	public void login() {
		ls.Login(); 
	}
	public void findId() {
		ss.findId();
	}
	public void BTNfindClicked() {
		ss.BTNfindClicked();
	}
	public void rePwd() {
		ss.rePwd();
	}
	public void cancel() {
		ss.cancel();
	}
	public void BTNresetClick() {
		ss.BTNreset();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		ls = new LoginServiceImpl();
		ss = new FindServiceImpl();
	}

}
