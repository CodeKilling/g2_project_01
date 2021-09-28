package inOut;

import java.util.ArrayList;

import common.BookDTO;
import javafx.scene.Parent;

public interface InOutService {
	public void setRoot(Parent root);
	public void getTable();
	public void viewStockTable(ArrayList<BookDTO> dto);
	public void cancel();
	public void setAccCmb();
	public void IOService();
	public String getAccCmb();
	public int findAccId();
	public String getBookName();
	public int findBookId();
	public int findStock();
	public int txtStock();
	public String eventDate();
}
