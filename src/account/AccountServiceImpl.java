package account;

import java.util.ArrayList;

import common.AccountDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountServiceImpl implements AccountService{

	@FXML TableColumn accountName, accountWorkerName, accountContactNumber;
	
	AccountDBService adbs = null;
	ArrayList<AccountDTO> arr = null;
	
	private Parent root = null;
	@Override
	public void setRoot(Parent p) {
		this.root = p;
	}
	
	//생성자
	public AccountServiceImpl() {
		adbs = new AccountDBServiceImpl();
	}
	
	@Override
	public void setView() {
		System.out.println("setView : 거래처관리.");
		
//		accountName.setCellValueFactory(new PropertyValueFactory("accountName"));
//		accountWorkerName.setCellValueFactory(new PropertyValueFactory("accountWorkerName"));
//		accountContactNumber.setCellValueFactory(new PropertyValueFactory("accountContactNumber"));
		
		arr = adbs.viewAccount();
		ObservableList<AccountDTO> view = FXCollections.observableArrayList();
		for(AccountDTO entry : arr) {
			view.add(entry);
		}
		TableView<AccountDTO> tableView = (TableView)root.lookup("#fxTableView_Account");
		tableView.setItems(view);
	}

}
