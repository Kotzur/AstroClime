package astroclime.controllers;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.json.JSONException;

import com.jfoenix.controls.JFXDrawer;

import astroclime.backend.WeatherData;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	private Canvas weatherImage;
	
	@FXML
	private JFXDrawer topDrawer;
	
	@FXML 
	private JFXDrawer rightDrawer;


	public void refresh() throws IOException {
		CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);
		temperatureLabel.setText((WeatherData.getTemperature(cwd)) + "�C");
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		visibilityLabel.setText("Vis : " + (int) WeatherData.getVisibility(cwd) + "km");
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd) + "mm");

		sunriseLabel.setText(WeatherData.getSunrise(cwd));
		sunsetLabel.setText(WeatherData.getSunset(cwd));

		cityLabel.setText(WeatherData.CITY_NAME);


		FileInputStream f = new FileInputStream(Paths.get("Icons/" + cwd.getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());

		Image img = new Image(f, weatherImage.getWidth(),weatherImage.getHeight(),false,false);
		GraphicsContext gc = weatherImage.getGraphicsContext2D();
		gc.setGlobalBlendMode(BlendMode.SCREEN);
		gc.drawImage(img, 0, 0);
	}

	
	private void initialize() throws JSONException, IOException, URISyntaxException {
		CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);

		temperatureLabel.setText((WeatherData.getTemperature(cwd)) + "�C");
		cloudCoverLabel.setText("Cloud Cover : " + (int) WeatherData.getCloudCover(cwd) + "%");
		visibilityLabel.setText("Visibility : " + (int) WeatherData.getVisibility(cwd) + "km");
		humidityLabel.setText("Humidity : " + (int) WeatherData.getHumidity(cwd) + "%");
		rainfallLabel.setText("Rainfall : " + WeatherData.getRainfall(cwd) + "mm");
		
		sunriseLabel.setText(WeatherData.getSunrise(cwd));
		sunsetLabel.setText(WeatherData.getSunset(cwd));
		
		cityLabel.setText(WeatherData.CITY_NAME);
		
		
		FileInputStream f = new FileInputStream(Paths.get("Icons/" + cwd.getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());
		
		Image img = new Image(f, weatherImage.getWidth(),weatherImage.getHeight(),false,false);
		GraphicsContext gc = weatherImage.getGraphicsContext2D();
		gc.setGlobalBlendMode(BlendMode.SCREEN);
		gc.drawImage(img, 0, 0);
		
		
		AnchorPane map = FXMLLoader.load(getClass().getResource("../scenes/MapView.fxml"));
		topDrawer.setSidePane(map);
		
		AnchorPane weeklyView = FXMLLoader.load(getClass().getResource("../scenes/WeekView.fxml"));
		rightDrawer.setSidePane(weeklyView);
	}
	
	public void test(KeyEvent key)  {
		try {
			if (key.getCode().equals(KeyCode.DOWN)) {
				if (topDrawer.isShown()) {
					refresh();
					topDrawer.close();
				} else {
					topDrawer.open();
				}
			}

			if (key.getCode().equals(KeyCode.RIGHT)) {
				if (rightDrawer.isShown()) {
					refresh();
					rightDrawer.close();
				} else {
					rightDrawer.open();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
   
}
