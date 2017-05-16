package astroclime;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("scenes/WeekView.fxml"));
		primaryStage.setTitle("Welcome");
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
		});

		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());

		primaryStage.setScene(scene);
		primaryStage.show();

	}
}