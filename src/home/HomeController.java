package home;

import java.net.URL;
import java.util.ResourceBundle;

import KHS.dbcommon.DBCommon;
import KHS.inOutService.inOutServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class HomeController implements Initializable{

	Parent root = null;
	KHS.inOutService.inOutServiceImpl svc;
	
	public void setRoot(Parent p) {
		this.root = p;
		svc.setRoot(p);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		svc = new inOutServiceImpl();
		DBCommon.setDBConnection();
	}
	
	public void plusStock() {
		svc.plusStock();
	}
	
	public void minusStock() {
		svc.minusStock();
	}
	
	public void cancel() {
		svc.cancel();
	}
	
	

}
