package login;

import common.Common;
import common.MemberDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class LoginServiceImpl implements LoginService {
	DBservice db;
	TabPane tabpane = null;
	Tab tabHomeProc, tabBookProc, tabBookInOutProc, tabAccountProc, tabInoutProc;
	public LoginServiceImpl() {
		db = new DBserviceImpl();
	}
	public void setTab(TabPane tp, Tab book, Tab bookInOut, Tab account, Tab inOut) {
		tabpane = tp;
		tabBookProc = book;
		tabBookInOutProc = bookInOut;
		tabAccountProc = account;
		tabInoutProc = inOut;
	}
	@Override
	public void Login(String _id, String _pwd) {
		MemberDTO dto = db.LoginCheck(_id);
		String msg = null;
		if (dto != null) {
			if (dto.getPwd().equals(_pwd)) {
				msg = "로그인 성공";
				Common.sessionID = dto.getId();
				setAbleTab();
			}
			else {
				msg = "비밀번호가 틀렸습니다";
			}
		}else {
			msg = "존재하지 않는 아이디 입니다";
		}
		Common.MyAlert(msg);
	}
	public void setAbleTab() {
		tabHomeProc = tabpane.getTabs().get(0);
		tabHomeProc.setDisable(true);
		
		tabBookProc.setDisable(false);
		tabBookInOutProc.setDisable(false);
		tabAccountProc.setDisable(false);
		tabInoutProc.setDisable(false);
		tabpane.getSelectionModel().select(1);
	}
}
