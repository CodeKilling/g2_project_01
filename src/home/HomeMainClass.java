package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeMainClass extends Application {

	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = null;
		
		try {
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HomeController hctl = loader.getController();
		hctl.setRoot(root);
		primaryStage.setTitle("2조:도서관리");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
