package login;

import common.Common;
import common.MemberDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindServiceImpl implements FindService {
	Parent root;

	DBservice db;
	public FindServiceImpl() {
		db = new DBserviceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void findId() {
		TextField fxId = (TextField)root.lookup("#fxId");
		TextField fxPwd = (TextField)root.lookup("#fxPwd");
		TextField RTFid = (TextField) root.lookup("#RTFid");
		TextField RTFpw = (TextField) root.lookup("#RTFpw");
		TextField RTFpwc = (TextField) root.lookup("#RTFpwc");
		TextField RTFname = (TextField) root.lookup("#RTFname");
		TextField RTFpn = (TextField) root.lookup("#RTFpn");
		Button BTNjoin = (Button) root.lookup("#BTNjoin");
		Button BTNreset = (Button) root.lookup("#BTNreset");
		Button BTNcancel = (Button)root.lookup("#BTNcancel");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		
		fxId.clear();
		fxPwd.clear();
		RTFid.setVisible(false);RTFname.setVisible(true);
		RTFpw.setVisible(false);RTFpn.setVisible(true);
		RTFpwc.setVisible(false);BTNfind.setVisible(true);
		BTNjoin.setVisible(false);
		BTNreset.setVisible(false);
	}

	@Override
	public void homecancel() {
		Button BTNcancel = (Button)root.lookup("#BTNcancel");
		Button BTNjoin = (Button)root.lookup("#BTNjoin");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		Button BTNreset = (Button)root.lookup("#BTNreset");
		TextField fxId = (TextField)root.lookup("#fxId");
		PasswordField fxPwd = (PasswordField)root.lookup("#fxPwd");
		TextField RTFid = (TextField)root.lookup("#RTFid");
		TextField RTFname = (TextField)root.lookup("#RTFname");
		TextField RTFpn = (TextField)root.lookup("#RTFpn");
		PasswordField RTFpw =(PasswordField)root.lookup("#RTFpw");
		PasswordField RTFpwc =(PasswordField)root.lookup("#RTFpwc");
		fxId.clear();fxPwd.clear();RTFid.clear();RTFname.clear();RTFpn.clear();
		RTFpw.clear();RTFpwc.clear();
		RTFid.setVisible(true);RTFname.setVisible(true);
		RTFpn.setVisible(true);RTFpw.setVisible(true);RTFpwc.setVisible(true);
		BTNjoin.setVisible(true);   BTNfind.setVisible(true); BTNreset.setVisible(true);
	}

	@Override
	public void rePwd() {
		String msg;
		TextField RTFname = (TextField)root.lookup("#RTFname");
		TextField RTFpn = (TextField)root.lookup("#RTFpn");
		TextField RTFid = (TextField)root.lookup("#RTFid");
		PasswordField RTFpw = (PasswordField)root.lookup("#RTFpw");
		PasswordField RTFpwc = (PasswordField)root.lookup("#RTFpwc");
		Button BTNreset = (Button)root.lookup("#BTNreset");
		Button BTNjoin = (Button)root.lookup("#BTNjoin");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		RTFname.setVisible(false); RTFpn.setVisible(false);RTFpw.setVisible(true);RTFpwc.setVisible(true);
		RTFid.setVisible(true);
		BTNjoin.setVisible(false); BTNfind.setVisible(false);BTNreset.setVisible(true);
	}

	@Override
	public void BTNfindClicked() {
		
	}

	@Override
	public void BTNreset() {
		String msg;
		PasswordField RTFpw =(PasswordField)root.lookup("#RTFpw");
		PasswordField RTFpwc =(PasswordField)root.lookup("#RTFpwc");
		TextField RTFid = (TextField)root.lookup("#RTFid");
		
		if(db.CheckID(RTFid.getText())) {
			if(RTFpw.getText().equals(RTFpwc.getText())) {
				db.rePwd(RTFid.getText(), RTFpw.getText());
				msg = "비밀번호가 재설정 되었습니다";
						Common.MyAlert(msg);
			}
			else {
				msg = "입력한 비밀번호와 일치하지 않습니다";
				Common.MyAlert(msg);
			}	
		}else {
			msg = "존재하지 않는 아이디 입니다";
			Common.MyAlert(msg);
		}
		
	}

}
