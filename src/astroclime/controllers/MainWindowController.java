package astroclime.controllers;

import astroclime.backend.WeatherData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.aksingh.owmjapis.CurrentWeather;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class MainWindowController {

	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private Label temperatureLabel;
	
	@FXML
	private Label cloudCoverLabel;
	
	@FXML
	private Label visibilityLabel;
	
	@FXML
	private Label humidityLabel;
	
	@FXML 
	private Label rainfallLabel;
	
	@FXML
	private Label sunsetLabel;
	
	@FXML
	private Label sunriseLabel;
	
	@FXML
	private Label cityLabel;
	
	@FXML
	private ImageView weatherImage;

	@FXML
	private ImageView suggestionImg;
	
	@FXML
	private JFXDrawer topDrawer;
	
	@FXML 
	private JFXDrawer rightDrawer;
	@FXML
	private JFXDrawer bottomDrawer;
	
	@FXML
	private JFXButton settingsButton;

	private AnchorPane settingsPane;
	
	private static MainWindowController mainWindowInstance;
	
	public void refresh() throws IOException {
		//refreshes the current weather data
		
		//creates an instance of current weather
		CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);
		//get the current temperature
		temperatureLabel.setText((WeatherData.getTemperature(cwd)) +  WeatherData.UNIT.getSymbol());
		//get the cloud cover
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		//get the visibility
		visibilityLabel.setText("Visibility : " + (int) WeatherData.getVisibility(cwd) + "km");
		//get the humidity
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		//get the rainfall
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd) + "mm");
		
		//get sunrise and sunset time
		sunriseLabel.setText(WeatherData.getSunrise(cwd));
		sunsetLabel.setText(WeatherData.getSunset(cwd));

		//get the current city name
		cityLabel.setText(WeatherData.CITY_NAME);

		//get the current image for the weather
		FileInputStream f = new FileInputStream(Paths.get("Icons/" + cwd.getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());

		//set the image
		Image img = new Image(f, weatherImage.getFitWidth(),weatherImage.getFitHeight(),false,false);
		weatherImage.setImage(img);
		
		//set the suggestion
		String suggestion = "";
		int cloudCover = (int) WeatherData.getCloudCover(cwd);
		int visibility = (int) WeatherData.getVisibility(cwd);
		int rainfall = (int) WeatherData.getRainfall(cwd);
		if(rainfall > 1){
			suggestion = "cross";
		}
		else if(visibility > 30 && cloudCover < 25){
			suggestion = "tick";
		}
		else if(visibility > 10 && cloudCover < 50){
			suggestion = "wave";
		}
		else{
			suggestion = "cross";
		}

		f = new FileInputStream(Paths.get("Icons/" + suggestion + ".PNG").toFile());
		img = new Image(f, suggestionImg.getFitWidth(), suggestionImg.getFitHeight(), false, false);
		suggestionImg.setImage(img);
		
		
	}

	
	public void initialize() throws JSONException, IOException {
		
		//refresh weather data
		refresh();
		
		//set the top drawer to display the map
		AnchorPane map = FXMLLoader.load(getClass().getResource("../scenes/MapView.fxml"));
		topDrawer.setSidePane(map);
		
		//set the right drawer to display the weekly view
		AnchorPane weeklyView = FXMLLoader.load(getClass().getResource("../scenes/WeekView.fxml"));
		rightDrawer.setSidePane(weeklyView);
		
		//sets the bottom drawer to display the hourly view
		ScrollPane hourlyView = FXMLLoader.load(getClass().getResource("../scenes/HourView.fxml"));
		//removes the borders from the hourly view
		hourlyView.setStyle("-fx-focus-color: #333333; -fx-faint-focus-color: #333333; -fx-box-border: #333333;");
		//set the bottom drawer to the hourly view
		bottomDrawer.setSidePane(hourlyView);
		
		//allows us to interact with the main screen
		topDrawer.toBack();
		bottomDrawer.toBack();
		rightDrawer.toBack();
		
		topDrawer.setOnDrawerClosed(event -> {topDrawer.toBack();});
		bottomDrawer.setOnDrawerClosed(event -> {bottomDrawer.toBack();});
		rightDrawer.setOnDrawerClosed(event -> {rightDrawer.toBack();});
		
		settingsPane = FXMLLoader.load(getClass().getResource("../scenes/SettingsView.fxml"));
		
		FileInputStream f = new FileInputStream(Paths.get("Icons/settings.PNG").toFile());
		//set the image
		Image img = new Image(f, settingsButton.getPrefWidth(), settingsButton.getPrefHeight(),false,false);

		
		ImageView imgView = new ImageView(img);
		
		
		imgView.setFitWidth(settingsButton.getWidth());
		imgView.setFitHeight(settingsButton.getHeight());
		
		settingsButton.setGraphic(imgView);
		settingsButton.setOnAction(event -> goToSettings());
		settingsButton.setFocusTraversable(false);
		mainWindowInstance = this;
		
	}
	
	public void swipeInput(KeyEvent key)  {
		
		//this is the event handler for any keyboard inputs.
		//in the real app this would be up, down, left and right swipe, but we can't do this on a laptop
		//every time we go back to the main page we refresh the view
		
		//we bring the drawers to the front and back to allow us to interact with the main screen
	
		try {
			switch (key.getCode()) {
			
			case DOWN : 
				if (bottomDrawer.isShown()) {
					refresh();
					bottomDrawer.close();
				}else if (!rightDrawer.isShown() && !topDrawer.isShown()) {
					topDrawer.toFront();
					topDrawer.open();
					
				}
				break;
			case UP :
				if (topDrawer.isShown()) {
					refresh();
					topDrawer.close();
				}else if (!rightDrawer.isShown() && !bottomDrawer.isShown()) {
					bottomDrawer.toFront();
					bottomDrawer.open();
					HourViewController.getHVC().refresh();
					
				}
				break;
				
			case LEFT :
				if (!topDrawer.isShown() && !bottomDrawer.isShown() && !rightDrawer.isShown()) {
					rightDrawer.toFront();
					rightDrawer.open();
					WeekViewController.getwvc().refresh();
					
					
				}
				break;
				
			case RIGHT : 
				if (rightDrawer.isShown()) {
					refresh();
					rightDrawer.close();
					
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void goToSettings(){
		mainPane.getScene().setRoot(settingsPane);
		bottomDrawer.close();
	}
	
	public AnchorPane getMainPane() {
		return mainPane;
	}
	
	public static MainWindowController getInstance() {
		return mainWindowInstance;
	}
		
   
}
