package mainTest;

import controller.Rootcontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
		Parent root = loader.load();
		Rootcontroller rootController = loader.getController();
		rootController.primaryStage = primaryStage;
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Tour Japan");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

}
