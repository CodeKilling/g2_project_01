package KHS.inOutService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import KHS.dbcommon.DBCommon;
import KHS.dto.bookDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class inOutServiceImpl implements inOutService{
	Parent root;
	KHS.dbService.dbServiceImpl db;
	TableView table;
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		db = new KHS.dbService.dbServiceImpl();
		//setTableView();
	}

	@Override
	public void plusStock() {
		System.out.println("입고 버튼 클릭");
		Label bookName = (Label)root.lookup("#bookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		// Label writerName = (Label)root.lookup("#writerName");
		
		TextField inputStock = (TextField)root.lookup("#inputStock");
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		
		KHS.dto.bookDTO dto = new KHS.dto.bookDTO();
		if(bookName.getText().equals("") || cmbAccount.getValue().toString().equals("") ||
				inputStock.getText().equals("") || eventDate.getValue().toString().equals(""))
		{ // 입력되지 않은 곳이 있으면 재입력 요청하기
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력되지 않은 곳이 있습니다. 다시 입력하세요!!");
			alert.show();
		} else {
			dto.setBookName(bookName.getText());
			dto.setBookPrice(bookPrice.getText());
			dto.setAccount(cmbAccount.getValue().toString());
			dto.setWarehousing(Integer.parseInt(inputStock.getText()));
			dto.setDate(eventDate.getValue().toString());
		
			int result = db.warehousing(dto);
			if(result ==1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("성공");
				alert.setContentText("책 " + bookName.getText() + "이(가)"
										+ inputStock.getText() + "개 만큼 입고되었습니다.");
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("입고 실패! 다시 입력하세요.");
				alert.show();
			}
		}
	}

	@Override
	public void minusStock() {
		System.out.println("출고 버튼 클릭");
		Label bookName = (Label)root.lookup("#bookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		// Label writerName = (Label)root.lookup("#writerName");
		
		TextField inputStock = (TextField)root.lookup("#inputStock");
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		
		KHS.dto.bookDTO dto = new KHS.dto.bookDTO();
		if(bookName.getText().equals("") || cmbAccount.getValue().toString().equals("") ||
				inputStock.getText().equals("") || eventDate.getValue().toString().equals(""))
		{ // 입력되지 않은 곳이 있으면 재입력 요청하기
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력되지 않은 곳이 있습니다. 다시 입력하세요!!");
			alert.show();
		} else {
			dto.setBookName(bookName.getText());
			dto.setBookPrice(bookPrice.getText());
			dto.setAccount(cmbAccount.getValue().toString());
			dto.setRelease(Integer.parseInt(inputStock.getText()));
			dto.setDate(eventDate.getValue().toString());
		
			int result = db.release(dto);
			if(result ==1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("성공");
				alert.setContentText("책 " + bookName.getText() + "이(가)"
										+ inputStock.getText() + "개 만큼 출고되었습니다.");
				alert.show();
			}else if(result == 2) { // 출고량이 재고량보다 많은 경우
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("현재 재고량이 요청된 출고량보다 적습니다.");
				alert.show();
			}else { // ★★★ 재고DB에서 재고량보다 많은 출고량이 잡히면 알림 발생
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("출고 실패! 다시 입력하세요.");
				alert.show();
			}
		}
		
	}

	@Override
	public void cancel() {
		System.out.println("입력값 초기화");
		Label bookName = (Label)root.lookup("#bookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		Label writerName = (Label)root.lookup("#writerName");
		
		TextField inputStock = (TextField)root.lookup("#inputStock");
		DatePicker eventDate = (DatePicker)root.lookup("#eventDate");
		ComboBox cmbAccount = (ComboBox)root.lookup("#cmbAccount");
		
		bookName.setText(""); bookPrice.setText(""); writerName.setText("");
		inputStock.setText("");
		eventDate.setValue(null); // DatePicker 에 아무값도 입력되지 않은 상태
		
		//★★★ cmbAccount.setValue(); // combobox를 FXCollections.observableArrayList 로 초기화 하기
	}
	
	/*
	private ObservableList<ObservableList> data;
	public void setTableView() {
		DBCommon.setDBConnection();
		String sql = "select bookName,stock from stockDB";
		try {
			PreparedStatement ps;
			ResultSet rs;
			ps = DBCommon.con.prepareStatement(sql);
			rs = ps.executeQuery();
			data = FXCollections.observableArrayList();
			while(rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i = 1; i <=rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			table.setItems(data);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("테이블뷰 가져오기 실패");
		}
	}
	*/
	
}
