package stats;

import javafx.scene.Parent;
import javafx.scene.control.DatePicker;

public interface StatsService {
	public void setRoot(Parent root, DatePicker sd, DatePicker ed);
	public void todaySearch();
	public void allSearch();
}
