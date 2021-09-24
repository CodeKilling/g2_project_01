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

	// 생성자
	public AccountServiceImpl() {
		adbs = new AccountDBServiceImpl();
	}

	@Override
	public void setView() {
		System.out.println("setView : 거래처관리.");

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
		arr = adbs.addAccount(tF_AccountName.getText(), tF_ContactNumber.getText(), tF_WorkerName.getText());
		if (arr.size() > 0) {
			Common.MyAlert("등록 성공!");
			this.setList(arr);
		} else {
			Common.MyAlert("등록 실패! (ex : 거래처명이 이미 존재 합니다.)");
		}
	}

	@Override
	public void Modify() {
		if (selectionID == -1) {
			Common.MyAlert("수정할 거래처를 선택하세요.");
		}
		String msg = oldAccountName + " -> " + tF_AccountName.getText() + ", " + oldContactNumber + " -> "
				+ tF_ContactNumber.getText() + ", " + oldWorkerName + " -> " + tF_WorkerName.getText() + ", "
				+ "로 수정하시겠습니까?";
		boolean bool = Common.OkCancleAlert(msg);
		if (bool) {
			System.out.println("Account Modify OK");
			arr = adbs.modifyAccount(selectionID, tF_AccountName.getText(), tF_ContactNumber.getText(),
					tF_WorkerName.getText());
			if (arr.size() > 0) {
				Common.MyAlert("수정 성공!");
				this.setList(arr);
			} else {
				Common.MyAlert("수정 실패! (ex : .)");
			}
		} else {
			System.out.println("Account Modify CANCLE");
		}
	}

	@Override
	public void Delete() {
		if (selectionID == -1) {
			Common.MyAlert("삭제할 거래처를 선택하세요.");
		}
		String msg = oldAccountName + ", "
				   + oldContactNumber + ", "
				   + oldWorkerName + "정말 삭제하시겠습니까?";
		boolean bool = Common.OkCancleAlert(msg);
		if (bool) {
			System.out.println("Account Delete OK");
			arr = adbs.deleteAccount(selectionID);
			if (arr.size() > 0) {
				Common.MyAlert("삭제 성공!");
				this.setList(arr);
			} else {
				Common.MyAlert("삭제 실패! (ex : .)");
			}
		} else {
			System.out.println("Account Delete CANCLE");
		}
	}

	@Override
	public void clear() {
		tF_AccountName.clear();
		tF_WorkerName.clear();
		tF_ContactNumber.clear();
	}

}
