package astroclime.controllers;

import astroclime.backend.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.aksingh.owmjapis.DailyForecast;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

import org.json.JSONException;

public class WeekViewController {

    @FXML
    private Label day1_name;

    @FXML
    private Label day1_date;

    @FXML
    private Label day1_month;

    @FXML
    private Label day1_visibility;

    @FXML
    private Label day1_precipitation;

    @FXML
    private Label day1_cloudCover;

    @FXML
    private ImageView day1_img;

    @FXML
    private Label day2_name;

    @FXML
    private Label day2_date;

    @FXML
    private Label day2_month;

    @FXML
    private Label day2_visibility;

    @FXML
    private Label day2_precipitation;

    @FXML
    private Label day2_cloudCover;

    @FXML
    private ImageView day2_img;

    @FXML
    private Label day3_name;

    @FXML
    private Label day3_date;

    @FXML
    private Label day3_month;

    @FXML
    private Label day3_visibility;

    @FXML
    private Label day3_precipitation;

    @FXML
    private Label day3_cloudCover;

    @FXML
    private ImageView day3_img;

    @FXML
    private Label day4_name;

    @FXML
    private Label day4_date;

    @FXML
    private Label day4_month;

    @FXML
    private Label day4_visibility;

    @FXML
    private Label day4_precipitation;

    @FXML
    private Label day4_cloudCover;

    @FXML
    private ImageView day4_img;

    @FXML
    private Label day5_name;

    @FXML
    private Label day5_date;

    @FXML
    private Label day5_month;

    @FXML
    private Label day5_visibility;

    @FXML
    private Label day5_precipitation;

    @FXML
    private Label day5_cloudCover;

    @FXML
    private ImageView day5_img;

    @FXML
    private Label day6_name;

    @FXML
    private Label day6_date;

    @FXML
    private Label day6_month;

    @FXML
    private Label day6_visibility;

    @FXML
    private Label day6_precipitation;

    @FXML
    private Label day6_cloudCover;

    @FXML
    private ImageView day6_img;

    @FXML
    private Label day7_name;

    @FXML
    private Label day7_date;

    @FXML
    private Label day7_month;

    @FXML
    private Label day7_visibility;

    @FXML
    private Label day7_precipitation;

    @FXML
    private Label day7_cloudCover;

    @FXML
    private ImageView day7_img;
    
    private static WeekViewController wvc;

    @FXML
    public void initialize() throws IOException{
       refresh();
       wvc = this;
    }
    
    public void refresh() throws JSONException, IOException {
    	 //populating arrays for labels for 7 days
        Label [] dayNames = new Label[]{day1_name, day2_name, day3_name, day4_name, day5_name, day6_name, day7_name};
        Label [] dayDates = new Label[]{day1_date, day2_date, day3_date, day4_date, day5_date, day6_date, day7_date};
        Label [] dayMonths = new Label[]{day1_month, day2_month, day3_month, day4_month, day5_month, day6_month, day7_month};
        Label [] precipitation = new Label[]{day1_precipitation, day2_precipitation, day3_precipitation, day4_precipitation, day5_precipitation, day6_precipitation, day7_precipitation};
        Label [] cloudCover = new Label[]{day1_cloudCover, day2_cloudCover, day3_cloudCover, day4_cloudCover, day5_cloudCover, day6_cloudCover, day7_cloudCover};
        Label [] visibility = new Label[]{day1_visibility, day2_visibility, day3_visibility, day4_visibility, day5_visibility, day6_visibility, day7_visibility};
        ImageView [] images = new ImageView[]{day1_img, day2_img, day3_img, day4_img, day5_img, day6_img, day7_img};

        //get the daily forecast
        DailyForecast df = WeatherData.getDailyForecast(WeatherData.CITY_NAME, WeatherData.COUNTRY_CODE);
        
        
        //starting at tomorrow's forecast, loop over all 7 days of forecasts
        for (int i = 1; i < df.getForecastCount(); i++) {
        	
        	//set the icon for each day
        	FileInputStream f = new FileInputStream(Paths.get("Icons/" + df.getForecastInstance(i).getWeatherInstance(0).getWeatherIconName() + ".PNG").toFile());
    		Image img = new Image(f, images[i-1].getFitWidth(),images[i-1].getFitHeight(),false,false);
    		images[i-1].setImage(img);
    		
    		//set the cloud percentage
	    	float c = df.getForecastInstance(i).getPercentageOfClouds();
	    	cloudCover[i-1].setText(String.valueOf((int)c) + "%");
	    	
	    	//set the rain forecast - be weary of NaNs
	    	float p = df.getForecastInstance(i).getRain();
	    	if (Float.isNaN(p)) {
	    		p=0;
	    	}
	    	precipitation[i-1].setText(String.valueOf(p) + "mm");
	    	
	    	
	    	//estimate visibility depending on cloud cover as we can not get this with the current info
	    	if (c > 75f) {
	    		visibility[i-1].setText("Poor Visibility");
	    	}else if (c > 50f) {
	    		visibility[i-1].setText("Moderate Visibility");
	    	}else {
	    		visibility[i-1].setText("Good Visibility");
	    	} 
	    	
        }
        
        
        ZonedDateTime today = ZonedDateTime.now();
        //get today's date
        for(int i = 1; i <= 7; i++){
            //set the day's date for each forecast
            dayNames[i-1].setText(today.plusDays(i).getDayOfWeek().toString());
            dayDates[i-1].setText(Integer.toString(today.plusDays(i).getDayOfMonth()));
            dayMonths[i-1].setText(today.plusDays(i).getMonth().toString());
        }

    }
    
    public static WeekViewController getwvc() {
    	return wvc;
    }

}
