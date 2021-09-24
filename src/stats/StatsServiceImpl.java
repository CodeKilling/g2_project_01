package stats;

import java.time.LocalDate;
import java.util.ArrayList;

import common.snrDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class StatsServiceImpl implements StatsService{
	Parent root;
	DatePicker startDate, endDate;
	StatsDB db;
	ArrayList<snrDTO> list;
	public void setRoot(Parent root,DatePicker sd, DatePicker ed) {
		this.root = root;
		this.startDate = sd;
		this.endDate = ed;
		db = new StatsDB();
		list = new ArrayList<snrDTO>();
	}
	@Override
	public void todaySearch() {
		LocalDate date = LocalDate.now();
		startDate.setValue(date); endDate.setValue(date);
		list = db.search(startDate.getValue().toString(), endDate.getValue().toString());
    	view(list);
	}
	@Override
	public void allSearch() {
		startDate.setValue(LocalDate.of(0000, 1, 1));
		endDate.setValue(LocalDate.of(9999, 12, 31));
		list = db.search(startDate.getValue().toString(), endDate.getValue().toString());
    	view(list);
	}
	public void view(ArrayList<snrDTO> dto) {
		ObservableList<snrDTO> view = FXCollections.observableArrayList();
		for(int i=0; i<dto.size(); i++) {
			view.add(dto.get(i));
		}
		TableView<snrDTO> tableView = (TableView)root.lookup("#tableView");
        tableView.setItems(view);
	}
}
