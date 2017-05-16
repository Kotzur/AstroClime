package astroclime.controllers;

import astroclime.backend.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.aksingh.owmjapis.CurrentWeather;

import java.io.IOException;
import java.time.ZonedDateTime;

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
    private Label day1_percipitation;

    @FXML
    private Label day1_humidity;

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
    private Label day2_percipitation;

    @FXML
    private Label day2_humidity;

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
    private Label day3_percipitation;

    @FXML
    private Label day3_humidity;

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
    private Label day4_percipitation;

    @FXML
    private Label day4_humidity;

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
    private Label day5_percipitation;

    @FXML
    private Label day5_humidity;

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
    private Label day6_percipitation;

    @FXML
    private Label day6_humidity;

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
    private Label day7_percipitation;

    @FXML
    private Label day7_humidity;

    @FXML
    private ImageView day7_img;


    @FXML
    public void initialize() throws IOException{
        Label [] dayNames = new Label[]{day1_name, day2_name, day3_name, day4_name, day5_name, day6_name, day7_name};
        Label [] dayDates = new Label[]{day1_date, day2_date, day3_date, day4_date, day5_date, day6_date, day7_date};
        Label [] dayMonths = new Label[]{day1_month, day2_month, day3_month, day4_month, day5_month, day6_month, day7_month};


        CurrentWeather cwd = WeatherData.getCurrentWeather(WeatherData.CITY_NAME,WeatherData.COUNTRY_CODE);

        ZonedDateTime today = ZonedDateTime.now();
        for(int i = 0; i < 7; i++){
            dayNames[i].setText(today.plusDays(i).getDayOfWeek().toString());
            dayDates[i].setText(Integer.toString(today.plusDays(i).getDayOfMonth()));
            dayMonths[i].setText(today.plusDays(i).getMonth().toString());
        }

    }

}
