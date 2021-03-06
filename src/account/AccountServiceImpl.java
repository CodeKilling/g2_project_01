package account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AccountDTO;
import common.Common;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountServiceImpl implements AccountService {

	AccountDBService adbs = null;
	ArrayList<AccountDTO> arr = null;
	TableView<AccountDTO> tv = null;
	TextField tF_AccountName = null, tF_WorkerName = null, tF_ContactNumber = null;
	int selectionID = -1;
	String oldAccountName = null, oldWorkerName = null, oldContactNumber = null;

	private Parent root = null;

	@Override
	public void setRoot(Parent p) {
		this.root = p;

		tF_AccountName = (TextField) root.lookup("#fxTF_AccountName");
		tF_WorkerName = (TextField) root.lookup("#fxTF_WorkerName");
		tF_ContactNumber = (TextField) root.lookup("#fxTF_ContactNumber");
		tv = (TableView<AccountDTO>) root.lookup("#fxTableView_Account");
		tv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				tF_AccountName.setText(newSelection.getName());
				tF_WorkerName.setText(newSelection.getWorkerName());
				tF_ContactNumber.setText(newSelection.getContactNumber());
				selectionID = newSelection.getId();
				oldAccountName = newSelection.getName();
				oldWorkerName = newSelection.getWorkerName();
				oldContactNumber = newSelection.getContactNumber();
			}
		});

		TableColumn fxaccountName = tv.getColumns().get(0);
		fxaccountName.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn fxaccountWorkerName = tv.getColumns().get(1);
		fxaccountWorkerName.setCellValueFactory(new PropertyValueFactory("workerName"));
		TableColumn fxaccountContactNumber = tv.getColumns().get(2);
		fxaccountContactNumber.setCellValueFactory(new PropertyValueFactory("contactNumber"));
	}

	// ?????????
	public AccountServiceImpl() {
		adbs = new AccountDBServiceImpl();
	}

	@Override
	public void setView() {
		System.out.println("setView : ???????????????.");

		this.clear();
		arr = adbs.viewAccount();
		this.setList(arr);
	}

	private void setList(ArrayList<AccountDTO> arr) {
		ObservableList<AccountDTO> view = FXCollections.observableArrayList();
		for (AccountDTO entry : arr) {
			view.add(entry);
		}
		tv.setItems(view);
	}

	@Override
	public void Add() {
		if(tF_AccountName.getText().isEmpty()
				|| tF_ContactNumber.getText().isEmpty()
				|| tF_WorkerName.getText().isEmpty()) {
			Common.MyAlert("????????? ????????????.");
		}else {
			arr = adbs.addAccount(tF_AccountName.getText(), tF_ContactNumber.getText(), tF_WorkerName.getText());
			if (arr.size() > 0) {
				Common.MyAlert("?????? ??????!");
				this.setList(arr);
				tv.getSelectionModel().select(arr.size()-1);
			} else {
				Common.MyAlert("?????? ??????! (ex : ??????????????? ?????? ?????? ?????????.)");
			}
		}
	}

	@Override
	public void Modify() {
		if(tF_AccountName.getText().isEmpty()
				|| tF_ContactNumber.getText().isEmpty()
				|| tF_WorkerName.getText().isEmpty()) {
			Common.MyAlert("????????? ????????????.");
		}else {
			if (selectionID == -1) {
				Common.MyAlert("????????? ???????????? ???????????????.");
			}
			String msg = oldAccountName + " -> " + tF_AccountName.getText() + ", " + oldContactNumber + " -> "
					+ tF_ContactNumber.getText() + ", " + oldWorkerName + " -> " + tF_WorkerName.getText() + ", "
					+ "??? ?????????????????????????";
			boolean bool = Common.OkCancleAlert(msg);
			if (bool) {
				System.out.println("Account Modify OK");
				arr = adbs.modifyAccount(selectionID, tF_AccountName.getText(), tF_ContactNumber.getText(),
						tF_WorkerName.getText());
				if (arr.size() > 0) {
					Common.MyAlert("?????? ??????!");
					this.setList(arr);
					tv.getSelectionModel().select(selectionID);
				} else {
					Common.MyAlert("?????? ??????! (ex : .)");
				}
			} else {
				System.out.println("Account Modify CANCLE");
			}
		}
	}

	@Override
	public void Delete() {
		if(tF_AccountName.getText().isEmpty()
				|| tF_ContactNumber.getText().isEmpty()
				|| tF_WorkerName.getText().isEmpty()) {
			Common.MyAlert("????????? ????????????.");
		}else {
			if (selectionID == -1) {
				Common.MyAlert("????????? ???????????? ???????????????.");
			}
			String msg = oldAccountName + ", "
					   + oldContactNumber + ", "
					   + oldWorkerName + "?????? ?????????????????????????";
			boolean bool = Common.OkCancleAlert(msg);
			if (bool) {
				System.out.println("Account Delete OK");
				arr = adbs.deleteAccount(selectionID);
				if (arr.size() > 0) {
					Common.MyAlert("?????? ??????!");
					this.setList(arr);
				} else {
					Common.MyAlert("?????? ??????! (ex : .)");
				}
			} else {
				System.out.println("Account Delete CANCLE");
			}
		}
	}

	@Override
	public void clear() {
		//tv.getSelectionModel().select(-1);
		tv.getSelectionModel().clearSelection();
		selectionID = -1;
		tF_AccountName.clear();
		tF_WorkerName.clear();
		tF_ContactNumber.clear();
	}

}
