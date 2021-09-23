package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import login.LoginService;
import login.LoginServiceImpl;


	
public class HomeController implements Initializable {
	LoginService ls;
	Parent root = null;

	public void setRoot(Parent p) {
		this.root = p;
		ls.setRoot(p);
	}
	public void login() {
		ls.Login();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		ls = new LoginServiceImpl();
	}

}
