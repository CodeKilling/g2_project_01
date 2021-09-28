package book;

import java.util.ArrayList;

import common.AccountDTO;
import common.BookDTO;
import common.Common;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookServiceImpl implements BookService{

	BookDBService bdbs = null;
	TableView<BookDTO> tv = null;
	ArrayList<BookDTO> arr = null;
	TextField textfield_BookName = null, textfield_BookWriter = null, textfield_BookPrice = null;
	int selectionID = -1;
	String oldBookName = null, oldBookWriter = null, oldBookPrice = null;
	
	private Parent root = null;
	@Override
	public void setRoot(Parent p) {
		this.root = p;
		
		textfield_BookName = (TextField)root.lookup("#bookTF_Name");
		textfield_BookWriter = (TextField)root.lookup("#bookTF_Writer");
		textfield_BookPrice = (TextField)root.lookup("#bookTF_Price");
		tv = (TableView)root.lookup("#fxtableviewBook");
		tv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				textfield_BookName.setText(newSelection.getName());
				textfield_BookWriter.setText(newSelection.getWriter());
				textfield_BookPrice.setText(newSelection.getPrice());
				selectionID = newSelection.getId();
				oldBookName = newSelection.getName();
				oldBookWriter = newSelection.getWriter();
				oldBookPrice = newSelection.getPrice();
			}
		});
		
		TableColumn tableColumnBookName = tv.getColumns().get(0);
		tableColumnBookName.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn tableColumnBookWriter = tv.getColumns().get(1);
		tableColumnBookWriter.setCellValueFactory(new PropertyValueFactory("writer"));
		TableColumn tableColumnBookPrice = tv.getColumns().get(2);
		tableColumnBookPrice.setCellValueFactory(new PropertyValueFactory("price"));
	}
	
	// 생성자
	public BookServiceImpl() {
		bdbs = new BookDBServiceImpl();
	}

	@Override
	public void setView() {
		System.out.println("setView : 도서관리.");
		this.clear();
		arr = bdbs.bookView();
		this.setList(arr);
	}
	
	private void setList(ArrayList<BookDTO> arr) {
		ObservableList<BookDTO> view = FXCollections.observableArrayList();
		for (BookDTO entry : arr) {
			view.add(entry);
		}
		tv.setItems(view);
	}

	@Override
	public void Add() {
		if(textfield_BookName.getText().isEmpty() 
				|| textfield_BookPrice.getText().isEmpty()
				|| textfield_BookWriter.getText().isEmpty()) {
			Common.MyAlert("공백이 있습니다.");
		}else {
			if(bdbs.chkName(textfield_BookName.getText())) {
				arr = bdbs.bookAdd(textfield_BookName.getText(), textfield_BookPrice.getText(), textfield_BookWriter.getText());
				if (arr.size() > 0) {
					Common.MyAlert("등록 성공!");
					this.setList(arr);
					tv.getSelectionModel().select(arr.size()-1);
				} else {
					Common.MyAlert("등록 실패! (ex : 도서명이 이미 존재 합니다.)");
				}
			}else {
				Common.MyAlert("도서명이 이미 존재 합니다. : " + textfield_BookName.getText());
			}
		}
	}

	@Override
	public void Modify() {
		if(textfield_BookName.getText().isEmpty() 
				|| textfield_BookPrice.getText().isEmpty()
				|| textfield_BookWriter.getText().isEmpty()) {
			Common.MyAlert("공백이 있습니다.");
		}else {
			if (selectionID == -1) {
				Common.MyAlert("수정할 도서를 선택하세요.");
			}
			String msg = oldBookName + " -> " + textfield_BookName.getText() + ", " + oldBookPrice + " -> "
					+ textfield_BookPrice.getText() + ", " + oldBookWriter + " -> " + textfield_BookWriter.getText() + ", "
					+ "로 수정하시겠습니까?";
			boolean bool = Common.OkCancleAlert(msg);
			if (bool) {
				System.out.println("Account Modify OK");
				arr = bdbs.bookModify(selectionID, textfield_BookName.getText(), textfield_BookPrice.getText(),
						textfield_BookWriter.getText());
				if (arr.size() > 0) {
					Common.MyAlert("수정 성공!");
					this.setList(arr);
					tv.getSelectionModel().select(selectionID);
				} else {
					Common.MyAlert("수정 실패! (ex : .)");
				}
			} else {
				System.out.println("Book Modify CANCLE");
			}
		}
	}

	@Override
	public void Delete() {
		if(textfield_BookName.getText().isEmpty() 
				|| textfield_BookPrice.getText().isEmpty()
				|| textfield_BookWriter.getText().isEmpty()) {
			Common.MyAlert("공백이 있습니다.");
		}else {
			if (selectionID == -1) {
				Common.MyAlert("삭제할 도서를 선택하세요.");
			}
			String msg = oldBookName + ", "
					   + oldBookPrice + ", "
					   + oldBookWriter + "정말 삭제하시겠습니까?";
			boolean bool = Common.OkCancleAlert(msg);
			if (bool) {
				System.out.println("Account Delete OK");
				arr = bdbs.bookDelete(selectionID);
				if (arr.size() > 0) {
					Common.MyAlert("삭제 성공!");
					this.setList(arr);
				} else {
					Common.MyAlert("삭제 실패! (ex : .)");
				}
			} else {
				System.out.println("Book Delete CANCLE");
			}
		}
	}

	@Override
	public void clear() {
		tv.getSelectionModel().clearSelection();
		selectionID = -1;
		textfield_BookName.clear();
		textfield_BookWriter.clear();
		textfield_BookPrice.clear();
	}
	
}
