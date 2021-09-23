package stats;

import java.time.LocalDate;

import javafx.scene.Parent;
import javafx.scene.control.DatePicker;

public class StatsServiceImpl implements StatsService{
	Parent root;
	DatePicker startDate, endDate;
	StatsDB db;
	public void setRoot(Parent root,DatePicker sd, DatePicker ed) {
		this.root = root;
		this.startDate = sd;
		this.endDate = ed;
		db = new StatsDB();
	}
	@Override
	public void todaySearch() {
		System.out.println("오늘 검색");
		//오늘 날짜 조회
		//LocalDate date = LocalDate.now();
		//startDate.setValue(date); endDate.setValue(date);
		db.test();
	}
	@Override
	public void allSearch() {
		System.out.println("전체 검색");
		
	}
}
