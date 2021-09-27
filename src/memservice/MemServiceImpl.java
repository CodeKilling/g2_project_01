package memservice;

import common.Common;
import common.MemberDTO;
import dbservice.DBService;
import dbservice.DBServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MemServiceImpl implements MemService{
	
	Parent root;
	DBService db;
	MemberDTO dto = new MemberDTO();
	
	public MemServiceImpl() {db = new DBServiceImpl();}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void join() {
		System.out.println("회원가입 버튼 클릭");
		TextField id = (TextField)root.lookup("#memId");
		TextField name = (TextField)root.lookup("#memName");
		TextField phNum = (TextField)root.lookup("#memPhNum");
		PasswordField pwd = (PasswordField)root.lookup("#memPwd");
		PasswordField pwd2 = (PasswordField)root.lookup("#memPwd2");
		
		Button membership = (Button)root.lookup("#membership");
		Button cancel_join = (Button)root.lookup("#cancel_join");
		
		membership.setVisible(true);
		cancel_join.setVisible(true);	
	
		id.setVisible(true);
		name.setVisible(true);
		phNum.setVisible(true);
		pwd.setVisible(true);
		pwd2.setVisible(true);
	}
	
	@Override
	public void membership() {
		System.out.println("가입 버튼 클릭");
		
		TextField id = (TextField)root.lookup("#memId");
		TextField name = (TextField)root.lookup("#memName");
		TextField phNum = (TextField)root.lookup("#memPhNum");
		PasswordField pwd = (PasswordField)root.lookup("#memPwd");
		PasswordField pwd2 = (PasswordField)root.lookup("#memPwd2");
		
		System.out.println(id.getText());
		System.out.println(name.getText());
		System.out.println(phNum.getText());
		System.out.println(pwd.getText());
		System.out.println(pwd2.getText());
				
		dto.setUserId( id.getText() );
		dto.setName( name.getText() );
		dto.setPhNum( phNum.getText() );
		dto.setPwd( pwd.getText() );
		dto.setPwd2( pwd2.getText() );
		
		
		int result = db.checkInfo(dto.getUserId());
		boolean chkPwd = checkPwd(dto);
		boolean empty = isEmpty();
		
		if(result == 1 && chkPwd == true) {
			int insert = db.insertMember(dto);
			if(insert == 1) {
				Common.MyAlert("회원가입 성공");	
			}
		}else if (result == 0 && chkPwd == true){
			if(empty) {
				Common.MyAlert("입력하지 않은 항목을 확인하십시오.");
			}else {
				Common.MyAlert("동일한 아이디가 존재합니다.");
				id.clear();
			}
		}//else if(result == 1 && chkPwd == false) {
		 else {
			if(empty) {
				Common.MyAlert("입력하지 않은 항목을 확인하십시오.");
			}else {
				Common.MyAlert("비밀번호가 일치하지 않습니다.");
				pwd.clear();
				pwd2.clear();
			}
		}

	}
	public void cancel_join() {
		System.out.println("취소 버튼 클릭");
		
		TextField id = (TextField)root.lookup("#memId");
		TextField name = (TextField)root.lookup("#memName");
		TextField phNum = (TextField)root.lookup("#memPhNum");
		PasswordField pwd = (PasswordField)root.lookup("#memPwd");
		PasswordField pwd2 = (PasswordField)root.lookup("#memPwd2");

		id.clear();
		name.clear();
		phNum.clear();
		pwd.clear();
		pwd2.clear();
	}
	public boolean checkPwd(MemberDTO dto) { //비밀번호 확인

		if(dto.getPwd().equals(dto.getPwd2())){
			return true; 
		}else {
			return false;
		}
	}
	public boolean isEmpty() { //공백 확인
		
		TextField id = (TextField)root.lookup("#memId");
		TextField name = (TextField)root.lookup("#memName");
		TextField phNum = (TextField)root.lookup("#memPhNum");
		PasswordField pwd = (PasswordField)root.lookup("#memPwd");
		PasswordField pwd2 = (PasswordField)root.lookup("#memPwd2");
		
		if( 	dto.getUserId().isEmpty() ||
				dto.getName().isEmpty() || 
				dto.getPwd().isEmpty() ||
				dto.getPwd2().isEmpty() || 
				dto.getPhNum().isEmpty() )
		{
			System.out.println(id.getText());
			System.out.println(name.getText());
			System.out.println(phNum.getText());
			System.out.println(pwd.getText());
			System.out.println(pwd2.getText());
			
			return true; //공백 있음
		}else {	
			return false; //모두 입력
		}
		
		
	}

}
