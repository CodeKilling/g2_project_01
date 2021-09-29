package login;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public interface LoginService {
	public void setRoot(Parent root);
	public void Login();
	public void setTab(TabPane tp, Tab book, Tab bookInOut, Tab account, Tab inOut, Tab logOut);
	public void setDisableTab();
}
