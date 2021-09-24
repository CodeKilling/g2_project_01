package common;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Common {
	public static int sessionID = -1;
	public static void MyClose(Parent p) {
		Stage s = (Stage) p.getScene().getWindow();
		s.close();
	}
	
	public static void MyAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
	
	public static Connection con = null;
	public static void MyConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@210.221.253.215:1521:xe","g2","oracle");
			System.out.println("연결성공 : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
