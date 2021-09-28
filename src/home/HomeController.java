package home;

import java.net.URL;
import java.util.ResourceBundle;

import KHS.inOutService;
import account.AccountService;
import account.AccountServiceImpl;
import book.BookService;
import book.BookServiceImpl;
import common.BookDTO;
import common.Common;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import login.LoginService;
import login.LoginServiceImpl;
import memservice.MemService;
import memservice.MemServiceImpl;
import stats.StatsDBService;
import stats.StatsDBServiceImpl;
import stats.StatsService;
import stats.StatsServiceImpl;

public class HomeController implements Initializable{
	@FXML TableColumn fxaccountName, fxaccountWorkerName, fxaccountContactNumber;
	//@FXML TableColumn fxaccountName, fxaccountWorkerName, fxaccountContactNumber;
	@FXML TableColumn fxCellBookName, fxCellBookTotal;
	
	TabPane tabpane = null;
	Parent root = null;
	StatsService ss = null;
	StatsDBService sdb = null;
	inOutService IOSvc;
	LoginService ls;
	MemService ms;	
	AccountService as = null;
	BookService bs = null;
	

	public void setRoot(Parent p) {
		this.root = p;
		IOSvc.setRoot(p);
		ss.setRoot(p);
		as.setRoot(p);
		bs.setRoot(p);
		ms.setRoot(p);
	
		tabpane = (TabPane)root.lookup("#fxTabPane");
		tabpane.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
			            switch(t1.getText()) {
			            case "HOME":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            case "도서관리":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	bs.setView();
			            	break;
			            case "도서입출고":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	setColumn();
			            	System.out.println("출력여부확인");
			            	//inOut();
			            	IOSvc.getTable();
			        		IOSvc.setAccCmb();
			            	break;
			            case "거래처관리":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	//AccountSetColumn();
			            	as.setView();
			            	break;
			            case "입출고현황":
			            	System.out.println("Tab Selection changed : " + t1.getText());
			            	break;
			            }
			        }
			    }
			);
	}
	@Override	
	public void initialize(URL arg0, ResourceBundle arg1) {
		Common.MyConnection();
		IOSvc = new inOutService();
		ls = new LoginServiceImpl();
		ss = new StatsServiceImpl();
		as = new AccountServiceImpl();
		bs = new BookServiceImpl();
		ms = new MemServiceImpl();
		sdb = new StatsDBServiceImpl();
	}
	public void login() {
		TextField id = (TextField) root.lookup("#fxId");
		PasswordField pwd = (PasswordField) root.lookup("#fxPwd");
		ls.Login(id.getText(), pwd.getText());
	}
	public void join() {
		ms.join();
	}
	public void membership() {
		ms.membership();
	}
	public void cancel_join() {
		ms.cancel_join();
	}
	public void inOut() {
		IOSvc.IOService();
	}
	public void cancel() {
		IOSvc.cancel();
	}
	public void setColumn() {
		fxCellBookName.setCellValueFactory(new PropertyValueFactory("name"));
		fxCellBookTotal.setCellValueFactory(new PropertyValueFactory("total"));
	}
	public void selectTable(MouseEvent event) {
		Label bookName = (Label)root.lookup("#lbbookName");
		Label bookPrice = (Label)root.lookup("#bookPrice");
		Label writerName = (Label)root.lookup("#writerName");
		TableView<BookDTO> stockTable = (TableView)root.lookup("#fxTV_snr");
		//int sel = stockTable.getSelectionModel().getSelectedIndex();
		BookDTO data = stockTable.getSelectionModel().getSelectedItem();
		bookName.setText(data.getName());
		bookPrice.setText(data.getPrice());
		writerName.setText(data.getWriter());
	}
	
//	private void AccountSetColumn() {
//		fxaccountName.setCellValueFactory(new PropertyValueFactory("name"));
//		fxaccountWorkerName.setCellValueFactory(new PropertyValueFactory("workerName"));
//		fxaccountContactNumber.setCellValueFactory(new PropertyValueFactory("contactNumber"));
//	}
	
	
	
	public void OnAccountAdd() {
		as.Add();
	}
	public void OnAccountModify() {
		as.Modify();
	}
	
	public void OnAccountDelete() {
		as.Delete();
	}
	
	public void OnAccountNew() {
		as.clear();
	}
	
	public void OnBookAdd() {
		bs.Add();
	}
	public void OnBookModify() {
		bs.Modify();
	}
	public void OnBookDelete() {
		bs.Delete();
	}
	public void OnBookNew() {
		bs.clear();
	}
	public void todaySearch() {
		ss.todaySearch();
	}
	public void allSearch() {
		ss.allSearch();
	}
	public void periodSearch() {
		ss.periodSearch();
	}
	public void statsSetCombo() {
		ss.updateCombo(sdb.getCombo());
	}
}
