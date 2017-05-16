package astroclime.controllers;

import java.io.IOException;

import org.json.JSONException;

import astroclime.backend.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.aksingh.owmjapis.CurrentWeather;

public class MainWindowController {
	
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
	
	
	public void initialize() throws JSONException, IOException {
		CurrentWeather cwd = WeatherData.getCurrentWeather("Cambridge", "GB");
		temperatureLabel.setText((WeatherData.getTemperature(cwd, true)) + "°C");
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		visibilityLabel.setText("Visibility : " + (int) WeatherData.getVisibility(cwd) + "km");
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd));
	}

}
