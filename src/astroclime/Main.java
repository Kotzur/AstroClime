package astroclime;

import astroclime.controllers.MainWindowController;
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
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/MainWindow.fxml"));
		
		Parent root = loader.load();
		primaryStage.setTitle("Welcome");
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
		});

		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());

		MainWindowController controllerHandle = (MainWindowController) loader.getController();
		
		scene.setOnKeyPressed(event -> controllerHandle.test(event));
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}