package login;

import common.Common;
import common.MemberDTO;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService {
	DBservice db;
	Parent root = null;
	TabPane tabpane = null;
	Tab tabHomeProc, tabBookProc, tabBookInOutProc, tabAccountProc, tabInoutProc, tabLogoutProc;
	public LoginServiceImpl() {
		db = new DBserviceImpl();
	}
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void setTab(TabPane tp, Tab book, Tab bookInOut, Tab account, Tab inOut, Tab logOut) {
		tabpane = tp;
		tabBookProc = book;
		tabBookInOutProc = bookInOut;
		tabAccountProc = account;
		tabInoutProc = inOut;
		tabLogoutProc = logOut;
	}
	
	@Override
	public void Login() {
		TextField id = (TextField) root.lookup("#fxId");
		PasswordField pwd = (PasswordField) root.lookup("#fxPwd");
		MemberDTO dto = db.LoginCheck(id.getText());
		String msg = null;
		if(id.getText().isEmpty()) {//id 입력유무 확인
			msg = "아이디를 입력해 주세요.";
			id.requestFocus();
		}else if(pwd.getText().isEmpty()) {//pwd 입력유무 확인
			msg = "비밀번호를 입력해 주세요.";
			pwd.requestFocus();
		}else {//id, pwd 빈칸이 아닐 경우 dto랑 비교
			if (dto != null) {//db에 id 있음
				if (dto.getPwd().equals(pwd.getText())) {//dto pwd랑 입력 pwd 같을 경우
					msg = "로그인 성공";
					Common.sessionID = dto.getId();
					id.clear();
					pwd.clear();
					setAbleTab();
				}
				else {//dto pwd랑 입력 pwd 다를 경우
					msg = "비밀번호가 틀렸습니다";
				}
			}else {//dto null일 경우
				msg = "존재하지 않는 아이디 입니다";
			}
		}
		Common.MyAlert(msg);
	}
	
	private void setAbleTab() {
		tabHomeProc = tabpane.getTabs().get(0);
		tabHomeProc.setDisable(true);
		
		tabBookProc.setDisable(false);
		tabBookInOutProc.setDisable(false);
		tabAccountProc.setDisable(false);
		tabInoutProc.setDisable(false);
		tabLogoutProc.setDisable(false);
		tabpane.getSelectionModel().select(1);
	}
	
	@Override
	public void setDisableTab() {
		String msg = "정말 로그아웃 하시겠습니까?";
		boolean bool = Common.OkCancleAlert(msg);
		if(bool) {
			System.out.println("Logout OK Clicked.");
			tabHomeProc = tabpane.getTabs().get(0);
			tabHomeProc.setDisable(false);
			
			tabBookProc.setDisable(true);
			tabBookInOutProc.setDisable(true);
			tabAccountProc.setDisable(true);
			tabInoutProc.setDisable(true);
			tabLogoutProc.setDisable(true);
			tabpane.getSelectionModel().select(0);
			Common.sessionID = -1;
		}else
			System.out.println("Logout CANCLE Clicked.");
	}
}
