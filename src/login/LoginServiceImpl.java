package login;

import common.Common;
import common.MemberDTO;
import javafx.scene.Parent;

public class LoginServiceImpl implements LoginService {
	Parent root = null;
	DBservice db;

	public LoginServiceImpl() {
		db = new DBserviceImpl();
	}

	@Override
	public void Login(String _id, String _pwd) {
		MemberDTO dto = db.LoginCheck(_id);
		String msg = null;
		if (dto != null) {
			if (dto.getPwd().equals(_pwd)) {
				msg = "로그인 성공";
			}
			else {
				msg = "비밀번호가 틀렸습니다";
			}
		}else {
			msg = "존재하지 않는 아이디 입니다";
		}
		Common.MyAlert(msg);
	}

}
