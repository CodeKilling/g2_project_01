package account;

import javafx.scene.Parent;

public interface AccountService {
	public void setRoot(Parent p);
	public void setView();
	public void Add();
	public void Modify();
	public void Delete();
	public void clear();
}
