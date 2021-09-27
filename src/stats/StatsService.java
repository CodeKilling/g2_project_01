package stats;

import java.util.ArrayList;
import javafx.scene.Parent;

public interface StatsService {
	public void setRoot(Parent root);
	public void todaySearch();
	public void periodSearch();
	public void allSearch();
	public void updateCombo(ArrayList<String> combo);
}
