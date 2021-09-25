package login;

import javafx.scene.Parent;

public interface FindService {
	public void setRoot(Parent root);
	
	public void findId();
	public void cancel();
	public void rePwd();
	public void BTNfindClicked();
	public void BTNreset();
}
