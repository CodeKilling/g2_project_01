package login;

import common.Common;
import common.MemberDTO;
import home.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindServiceImpl implements FindService {
	Parent root = null;

	DBservice db;

	public FindServiceImpl() {
		db = new DBserviceImpl();
	}

	@Override //아이디 찾기 버튼을 눌렀을 때
	public void findId() {
		TextField fxId = (TextField)root.lookup("#fxId");
		TextField fxPwd = (TextField)root.lookup("#fxPwd");
		TextField RTFid = (TextField) root.lookup("#RTFid");
		TextField RTFpw = (TextField) root.lookup("#RTFpw");
		TextField RTFpwc = (TextField) root.lookup("#RTFpwc");
		Button BTNjoin = (Button) root.lookup("#BTNjoin");
		Button BTNreset = (Button) root.lookup("#BTNreset");
		fxId.clear();
		fxPwd.clear();
		RTFid.setVisible(false);
		RTFpw.setVisible(false);
		RTFpwc.setVisible(false);
		BTNjoin.setVisible(false);
		BTNreset.setVisible(false);

	}

	public void BTNfindClicked() { //찾기 버튼 눌렀을 때

		TextField tfName = (TextField) root.lookup("#RTFname");
		TextField tfPhoneNumber = (TextField) root.lookup("#RTFpn");
		String msg = null;

		MemberDTO dto = db.FindId(tfName.getText());
		if (dto != null) {
			
			if (dto.getPhonenumber().equals(tfPhoneNumber.getText())) {
				msg = "찾기 성공";
				try {

					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Find.fxml"));

					Parent root1 = (Parent) fxmlLoader.load();

					Label id = (Label) root1.lookup("#fxId");
					id.setText(dto.getUserId());

					Stage stage = new Stage();

					stage.setScene(new Scene(root1));
					stage.show();

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			else {
				msg = "전화번호가 틀렸습니다";
			}
		}else {
			msg = "존재하지 않는 이름 입니다";
		}
		Common.MyAlert(msg);
		if(dto != null) {
			if(dto.getPhonenumber().equals(tfPhoneNumber.getText())) {
		
		TextField fxId = (TextField)root.lookup("#fxId");
		
		
		fxId.setText(dto.getUserId());
		tfName.clear();
		tfPhoneNumber.clear();
		
			}
		}
	}

	@Override
	public void rePwd() {
		TextField RTFname = (TextField)root.lookup("#RTFname");
		TextField RTFpn = (TextField)root.lookup("#RTFpn");
		Button BTNjoin = (Button)root.lookup("#BTNjoin");
		Button BTNfind = (Button)root.lookup("#BTNfind");
		RTFname.setVisible(false); RTFpn.setVisible(false);
		BTNjoin.setVisible(false); BTNfind.setVisible(false);
		
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void cancel() { //취소 버튼 눌렀을 때
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

}
