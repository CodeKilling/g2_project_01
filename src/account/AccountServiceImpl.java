package account;

import java.util.ArrayList;

import common.AccountDTO;
import javafx.scene.Parent;

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
		
	}

}
