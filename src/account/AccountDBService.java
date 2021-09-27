package account;

import java.util.ArrayList;

import common.AccountDTO;

public interface AccountDBService {
	public ArrayList<AccountDTO> addAccount(String accountName, String contactNumber, String workerName);
	public ArrayList<AccountDTO> deleteAccount(int selectionID);
	public ArrayList<AccountDTO> modifyAccount(int selectionID, String accountName, String contactNumber, String workerName);
	public ArrayList<AccountDTO> viewAccount();
}
