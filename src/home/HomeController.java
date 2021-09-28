package home;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import stats.StatsDBService;
import stats.StatsDBServiceImpl;
import stats.StatsService;
import stats.StatsServiceImpl;

public class HomeController implements Initializable{
	Parent root = null;
	StatsService ss = null;
	StatsDBService sdb = null;
	public void setRoot(Parent p) {
		this.root = p;
		ss.setRoot(p);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		ss = new StatsServiceImpl();
		sdb = new StatsDBServiceImpl();
	}
	public void todaySearch() {
		ss.todaySearch();
	}
	public void periodSearch() {
		ss.periodSearch();
	}
	public void allSearch() {
		ss.allSearch();
	}
	public void statsSetCombo() {
		ss.updateCombo(sdb.getCombo());
	}
}
