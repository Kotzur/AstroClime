package astroclime;


//inport the libraries and stuff
import astroclime.controllers.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/MainWindow.fxml")); //selects the frame to load
		
		Parent root = loader.load(); //loads the frame
		primaryStage.setTitle("AstroClimeGazes"); //sets the name of the window
		primaryStage.setResizable(false); //means you cant rescale the window size
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit(); // when you close the app this will make sure all the bits are terminated properly
		});

		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); //creates the main frame

		MainWindowController controllerHandle = (MainWindowController) loader.getController(); //loads in the observer
		
		scene.setOnKeyPressed(event -> controllerHandle.test(event)); //don't know why this is here
		
		primaryStage.setScene(scene); // sets to show the main screen
		primaryStage.show(); //makes the app visible

	}
}