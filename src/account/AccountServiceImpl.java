package account;

import java.util.ArrayList;

import common.AccountDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

public class AccountServiceImpl implements AccountService{

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
		
		arr = adbs.viewAccount();
		ObservableList<AccountDTO> view = FXCollections.observableArrayList();
		for(AccountDTO entry : arr) {
			view.add(entry);
		}
		TableView<AccountDTO> tableView = (TableView)root.lookup("#fxTableView_Account");
		tableView.setItems(view);
	}

}
