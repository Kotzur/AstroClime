package astroclime;


//inport the libraries and stuff
import astroclime.controllers.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//create a new loader from the main window fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/MainWindow.fxml"));
		//load the parent node
		AnchorPane root = (AnchorPane) loader.load();
		//sets the name of the window
		primaryStage.setTitle("AstroClime"); 
		//load icon from file
		FileInputStream f = new FileInputStream(Paths.get("Icons/logo.PNG").toFile());
		//create image object
		Image img = new Image(f);
		//set the stage's icon to the image
		primaryStage.getIcons().add(img);
		//means you cant rescale the window size
		primaryStage.setResizable(false); 
		// when you close the app this will make sure all the bits are terminated properly
		primaryStage.setOnCloseRequest(e -> {Platform.exit();});
		//create a scene with the loaded root as the parent node
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		//get the instance of the controller for the main window
		MainWindowController controllerHandle = (MainWindowController) loader.getController(); 
		//set the on key pressed event handler to the swipe input function in the controller instance
		scene.setOnKeyReleased(event -> {if (scene.getRoot() == root){controllerHandle.swipeInput(event);}});
		//sets the window with the created scene
		primaryStage.setScene(scene);
		//show the window
		primaryStage.show(); 

	}
}