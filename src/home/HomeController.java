package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class HomeController implements Initializable{

	Parent root = null;
	public void setRoot(Parent p) {
		this.root = p;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
	}
}
