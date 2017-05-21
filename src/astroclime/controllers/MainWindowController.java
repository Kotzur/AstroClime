package astroclime.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONException;

import com.jfoenix.controls.JFXDrawer;

import astroclime.backend.WeatherData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.aksingh.owmjapis.CurrentWeather;

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
	private JFXDrawer topDrawer;
	
	@FXML 
	private JFXDrawer rightDrawer;
	@FXML
	private JFXDrawer bottomDrawer;



	
	private void refresh() throws IOException {
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
		hourlyView.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-box-border: transparent;");
		//set the bottom drawer to the hourly view
		bottomDrawer.setSidePane(hourlyView);
		
		//allows us to interact with the main screen
		topDrawer.toBack();
		bottomDrawer.toBack();
		rightDrawer.toBack();
		
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
					
				}
				break;
				
			case LEFT :
				if (!topDrawer.isShown() && !bottomDrawer.isShown() && !rightDrawer.isShown()) {
					rightDrawer.toFront();
					rightDrawer.open();
					
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
		
   
}
