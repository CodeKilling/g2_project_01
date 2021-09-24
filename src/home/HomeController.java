package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginService;
import login.LoginServiceImpl;


	
public class HomeController implements Initializable {
	LoginService ls;
	Parent root = null;

	public void setRoot(Parent p) {
		this.root = p;
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
	}

}
