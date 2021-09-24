package account;

import java.util.ArrayList;

import common.AccountDTO;

public interface AccountDBService {
	public void addAccount();
	public void deleteAccount();
	public void modifyAccount();
	public ArrayList<AccountDTO> viewAccount();
}
