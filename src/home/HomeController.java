package home;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import KHS.dbcommon.DBCommon;
import KHS.dto.bookDTO;
import KHS.dto.stockDTO;
import KHS.inOutService.inOutServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable{

	Parent root = null;
	KHS.inOutService.inOutServiceImpl svc;
	ArrayList<stockDTO> dto;
	@FXML TableColumn bookNameColumn, stockColumn;
	
	public void setRoot(Parent p) {
		this.root = p;
		svc.setRoot(p);
		svc.setComboBox();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		svc = new inOutServiceImpl();
		dto = new ArrayList<stockDTO>();
		//setColumn();
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
	/*
	public void setColumn() {
		bookNameColumn.setCellValueFactory(new PropertyValueFactory("bookName"));
		stockColumn.setCellValueFactory(new PropertyValueFactory("stock"));
	}
	
	public void viewTable(ArrayList<stockDTO> dto) { 
		ObservableList<stockDTO> viewTable = FXCollections.observableArrayList();
		DBCommon.setDBConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			System.out.println("db접속까진 괜춘");
			String sql = "select bookName, stock from stockDB";
			ps = DBCommon.con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("쿼리작동까진 괜춘");
			while(rs.next()) {
				dto.add(new stockDTO(rs.getString(1), rs.getInt(2)));
			}
			for(int i = 0; i < dto.size(); i++) {
				viewTable.add(dto.get(i));
			}
			TableView<stockDTO> tableView = (TableView)root.lookup("#stockTable");
			tableView.setItems(viewTable);
		} catch(Exception e) {
			e.printStackTrace();	
		}
		*/
		
	}
	
	

}
