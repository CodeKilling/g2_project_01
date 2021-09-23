package login;

import common.Common;
import common.MemberDTO;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService {
	Parent root = null;
	DBservice db;
	Common cm;

	public LoginServiceImpl() {
		db = new DBserviceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void Login() {
		TextField id = (TextField) root.lookup("#fxId");
		PasswordField pwd = (PasswordField) root.lookup("#fxPwd");
		MemberDTO dto = db.LoginCheck(id.getText());
		if (dto != null) {
			if (dto.getPwd().equals(pwd.getText())) {
				String msg ="로그인 성공";
				cm.MyAlert(msg);
			}
			
			else {
				String msg = "비밀번호가 틀렸습니다";
				cm.MyAlert(msg);
			}
		}else {
			String msg = "존재하지 않는 아이디 입니다";
			cm.MyAlert(msg);
		}
	}

}
