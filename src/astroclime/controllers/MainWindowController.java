package astroclime.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
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
		CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);
		temperatureLabel.setText((WeatherData.getTemperature(cwd)) +  Character.toString((char) 176 ) + "C");
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		visibilityLabel.setText("Visibility : " + (int) WeatherData.getVisibility(cwd) + "km");
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd) + "mm");

		sunriseLabel.setText(WeatherData.getSunrise(cwd));
		sunsetLabel.setText(WeatherData.getSunset(cwd));

		cityLabel.setText(WeatherData.CITY_NAME);


		FileInputStream f = new FileInputStream(Paths.get("Icons/" + cwd.getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());

		Image img = new Image(f, weatherImage.getFitWidth(),weatherImage.getFitHeight(),false,false);
		weatherImage.setImage(img);
	}

	
	public void initialize() throws JSONException, IOException, URISyntaxException {
		CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);

		temperatureLabel.setText((WeatherData.getTemperature(cwd)) +  Character.toString((char) 176 ) + "C");
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		visibilityLabel.setText("Visibility : " + (int) WeatherData.getVisibility(cwd) + "km");
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd) + "mm");
		
		sunriseLabel.setText(WeatherData.getSunrise(cwd));
		sunsetLabel.setText(WeatherData.getSunset(cwd));
		
		cityLabel.setText(WeatherData.CITY_NAME);
		
		
		FileInputStream f = new FileInputStream(Paths.get("Icons/" + cwd.getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());
		
		Image img = new Image(f, weatherImage.getFitWidth(),weatherImage.getFitHeight(),false,false);
		weatherImage.setImage(img);
		
		
		AnchorPane map = FXMLLoader.load(getClass().getResource("../scenes/MapView.fxml"));
		topDrawer.setSidePane(map);
		
		AnchorPane weeklyView = FXMLLoader.load(getClass().getResource("../scenes/WeekView.fxml"));
		rightDrawer.setSidePane(weeklyView);
		
		ScrollPane hourlyView = FXMLLoader.load(getClass().getResource("../scenes/HourView.fxml"));
		hourlyView.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-box-border: transparent;");
		hourlyView.setOnKeyPressed(event -> closeHourlyView(event));
		bottomDrawer.setSidePane(hourlyView);
	}
	
	public void swipeInput(KeyEvent key)  {
		try {
			switch (key.getCode()) {
			
			case DOWN : 
				if (bottomDrawer.isShown()) {
					refresh();
					bottomDrawer.close();
				}else if (!rightDrawer.isShown() && !topDrawer.isShown()) {
					topDrawer.open();
					topDrawer.toFront();
				}
				break;
			case UP :
				if (topDrawer.isShown()) {
					refresh();
					topDrawer.close();
				}else if (!rightDrawer.isShown() && !bottomDrawer.isShown()) {
					bottomDrawer.open();
					bottomDrawer.toFront();
				}
				break;
				
			case LEFT :
				if (!topDrawer.isShown() && !bottomDrawer.isShown() && !rightDrawer.isShown()) {
					rightDrawer.open();
					rightDrawer.toFront();
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
	
	public void closeHourlyView(KeyEvent key) {
		
			switch (key.getCode()) {
			
			case DOWN : 
				if (bottomDrawer.isShown()) {
					mainPane.requestFocus();
					bottomDrawer.close();
				}
				break;
			}
	}
	
   
}
