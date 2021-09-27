package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import memservice.MemService;
import memservice.MemServiceImpl;

public class HomeController implements Initializable{

	MemService ms;
	Parent root = null;
	public void setRoot(Parent p) {
		this.root = p;
		ms.setRoot(p);
	}
	
	@Override	
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms = new MemServiceImpl();
		Common.MyConnection();
	}
	public void join() {
		ms.join();
	}
	public void membership() {
		ms.membership();
	}
	public void cancel() {
		ms.cancel();
	}
	

}
