package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import stats.StatsService;
import stats.StatsServiceImpl;


public class HomeController implements Initializable{
	@FXML DatePicker startDate, endDate;
	Parent root = null;
	StatsService ss = null;
	public void setRoot(Parent p) {
		this.root = p;
		ss.setRoot(p, startDate, endDate);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		ss = new StatsServiceImpl();
	}
	public void todaySearch() {
		ss.todaySearch();
	}
	public void allSearch() {
		ss.allSearch();
	}
}
