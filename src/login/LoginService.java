package login;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public interface LoginService {
	public void Login(String _id, String _pwd);
	public void setTab(TabPane tp, Tab book, Tab bookInOut, Tab account, Tab inOut);
}
