package stats;

import java.time.LocalDate;
import java.util.ArrayList;

import common.Common;
import common.snrDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatsServiceImpl implements StatsService{
	Parent root;
	DatePicker startDate, endDate;
	ComboBox<String> statsComboBook;
	StatsDBServiceImpl db;
	ArrayList<snrDTO> list = null;
	public void setRoot(Parent root) {
		this.root = root;
		db = new StatsDBServiceImpl();
		startDate = (DatePicker)root.lookup("#startDate");
		endDate = (DatePicker)root.lookup("#endDate");
		statsComboBook = (ComboBox<String>)root.lookup("#statsComboBook");
		setCombo();
	}
	//오늘 검색
	public void todaySearch() {
		LocalDate date = LocalDate.now();
		startDate.setValue(date); endDate.setValue(date);
		String start = startDate.getValue().toString().replace("-", "");
		String end = endDate.getValue().toString().replace("-", "");
		list = db.search(start, end);
    	view(list);
	}
	//기간 검색
	public void periodSearch() {
		if(startDate.getValue() == null) {
			Common.MyAlert("시작일자를 설정 해주세요.");
			startDate.requestFocus();
		}else if(endDate.getValue() == null){
			Common.MyAlert("종료일자를 설정 해주세요.");
			endDate.requestFocus();
		}else {
			int compare = startDate.getValue().compareTo(endDate.getValue());
			if(compare > 0) {//시작일자가 더 늦을 경우 양수
				Common.MyAlert("시작일자를 종료일자보다 빠르게 설정해주세요.");
				startDate.requestFocus();
			}else if (compare <= 0) {//시작일자가 더 빠를 경우 음수, 같으면 0
				String start = startDate.getValue().toString().replace("-", "");
				String end = endDate.getValue().toString().replace("-", "");
				list = db.search(start, end);
				view(list);
			}
		}
	}
	//전체 검색
	public void allSearch() {
		startDate.setValue(LocalDate.of(0000, 1, 1));
		endDate.setValue(LocalDate.of(9999, 12, 31));
		list = db.search(startDate.getValue().toString(), endDate.getValue().toString());
    	view(list);
	}
	//콤보박스
	public void setCombo() {
		if(statsComboBook != null) {
			statsComboBook.getItems().add("전체조회");
		}else {
			System.out.println("콤보박스를 만들어주세요!");
		}
	}
	public void updateCombo(ArrayList<String> combo) {
		//콤보박스 버튼 클릭하면 기존 책 종류 내역 삭제 후 다시 생성
		statsComboBook.getItems().removeAll(combo);
		for(int i=0; i<combo.size(); i++) {
			statsComboBook.getItems().add(combo.get(i));
		}
	}
	//테이블뷰
	public void view(ArrayList<snrDTO> dto) {
		ObservableList<snrDTO> view = FXCollections.observableArrayList();
		for(int i=0; i<dto.size(); i++) {
			if(statsComboBook.getValue() == null || statsComboBook.getValue().equals("전체조회")) {
				//콤보박스 null or 전체조회일 경우 모든 데이터 저장
				view.add(dto.get(i));
			}else if(statsComboBook.getValue().equals(dto.get(i).getBookName())) {
				//콤보박스 선택값이 있을 경우 해당 데이터만 저장
				view.add(dto.get(i));
			}
		}
		
		TableView<snrDTO> tableView = (TableView)root.lookup("#tableView");
		TableColumn bookName = tableView.getColumns().get(0);
		TableColumn price = tableView.getColumns().get(1);
		TableColumn accountName = tableView.getColumns().get(2);
		TableColumn memberName = tableView.getColumns().get(3);
		TableColumn inOut = tableView.getColumns().get(4);
		TableColumn resultTotal = tableView.getColumns().get(5);
		TableColumn total = tableView.getColumns().get(6);
		TableColumn recordDate = tableView.getColumns().get(7);
		
		bookName.setCellValueFactory(new PropertyValueFactory("bookName"));
		price.setCellValueFactory(new PropertyValueFactory("price"));
		accountName.setCellValueFactory(new PropertyValueFactory("accountName"));
		memberName.setCellValueFactory(new PropertyValueFactory("memberName"));
		inOut.setCellValueFactory(new PropertyValueFactory("inOut"));
		resultTotal.setCellValueFactory(new PropertyValueFactory("resultTotal"));
		total.setCellValueFactory(new PropertyValueFactory("total"));
		recordDate.setCellValueFactory(new PropertyValueFactory("recordDate"));
		
		tableView.setItems(view);
	}
}
