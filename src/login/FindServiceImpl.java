package login;

import common.Common;
import common.MemberDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindServiceImpl implements FindService {
Parent root;
DBservice db;
String msg = null;
public FindServiceImpl() {
	db = new DBserviceImpl();
}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void findId() {
		//System.out.println("아이디찾기 버튼 클릭");
		TextField fxId = (TextField)root.lookup("#fxId");
		TextField fxPwd = (TextField)root.lookup("#fxPwd");
		TextField memId = (TextField) root.lookup("#memId");
		TextField memPwd = (TextField) root.lookup("#memPwd");
		TextField memPwd2 = (TextField) root.lookup("#memPwd2");
		TextField memName = (TextField) root.lookup("#memName");
		TextField memPhNum = (TextField) root.lookup("#memPhNum");
		Button membership = (Button) root.lookup("#membership");
		Button BTNreset = (Button) root.lookup("#BTNreset");
		Button cancel_join = (Button)root.lookup("#cancel_join");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		
		fxId.clear();
		fxPwd.clear();
		memId.setVisible(false);memName.setVisible(true);
		memPwd.setVisible(false);memPhNum.setVisible(true);
		memPwd2.setVisible(false);memPwd2.setVisible(false);
		membership.setVisible(false);
		cancel_join.setVisible(true);
		BTNreset.setVisible(false);
		BTNfind.setVisible(true);
	}
	@Override
	public void rePwd() {
		//System.out.println("비번 재설정");
		TextField RTFname = (TextField)root.lookup("#memName");
		TextField RTFpn = (TextField)root.lookup("#memPhNum");
		TextField RTFid = (TextField)root.lookup("#memId");
		PasswordField RTFpw = (PasswordField)root.lookup("#memPwd");
		PasswordField RTFpwc = (PasswordField)root.lookup("#memPwd2");
		Button BTNreset = (Button)root.lookup("#BTNreset");
		Button BTNjoin = (Button)root.lookup("#membership");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		Button cancel_join = (Button)root.lookup("#cancel_join");
		RTFname.setVisible(false); RTFpn.setVisible(false);RTFpw.setVisible(true);RTFpwc.setVisible(true);
		RTFid.setVisible(true);cancel_join.setVisible(true);
		BTNjoin.setVisible(false); BTNfind.setVisible(false);BTNreset.setVisible(true);
	}

	@Override
	public void BTNfindClicked() {
		TextField tfName = (TextField) root.lookup("#memName");
		TextField tfPhoneNumber = (TextField) root.lookup("#memPhNum");
		TextField fxId = (TextField)root.lookup("#fxId");

		MemberDTO dto = db.FindId(tfName.getText());
		if(tfName.getText().isEmpty() || tfPhoneNumber.getText().isEmpty()) {
			msg = "입력하지 않은 항목을 확인하십시오.";
		}else {
			if (dto != null) {
				if (dto.getPhNum().equals(tfPhoneNumber.getText())) {
					msg = "찾기 성공!\n아이디 : "+ dto.getUserId();
					fxId.setText(dto.getUserId());
					tfName.clear();
					tfPhoneNumber.clear();
				}
				else {
					msg = "전화번호가 틀렸습니다.";
				}
			}else {
				msg = "존재하지 않는 이름입니다.";
			}
		}
		Common.MyAlert(msg);
	}

	@Override
	public void BTNreset() {		
		TextField RTFid = (TextField)root.lookup("#memId");
		PasswordField RTFpw =(PasswordField)root.lookup("#memPwd");
		PasswordField RTFpwc =(PasswordField)root.lookup("#memPwd2");
		if(RTFid.getText().isEmpty() || RTFpw.getText().isEmpty() || RTFpwc.getText().isEmpty()) {
			msg = "입력하지 않은 항목을 확인하십시오.";
		}else {
			if(db.CheckID(RTFid.getText())) {
				if(RTFpw.getText().equals(RTFpwc.getText())) {
					db.rePwd(RTFid.getText(), RTFpw.getText());
					msg = "비밀번호가 재설정 되었습니다";
				}
				else {
					msg = "입력한 비밀번호와 일치하지 않습니다";
				}	
			}else {
				msg = "존재하지 않는 아이디 입니다";
			}
		}
		Common.MyAlert(msg);
	}

}
