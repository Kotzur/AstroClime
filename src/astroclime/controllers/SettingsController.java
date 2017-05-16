package astroclime.controllers;

    import astroclime.backend.WeatherData;
    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXToggleButton;
    import javafx.fxml.FXML;
    import javafx.scene.control.ToggleGroup;
    import javafx.event.ActionEvent;

public class SettingsController {

    @FXML
    private JFXToggleButton kelvin;

    @FXML
    private JFXComboBox<?> location;

    @FXML
    private JFXComboBox<?> language;

    @FXML
    private ToggleGroup units;

    @FXML
    private JFXToggleButton celcius;

    @FXML
    private JFXToggleButton fahrenheit;

    //TODO: add logic for Kelvin
    @FXML
    void changeUnits(ActionEvent event) {
       if(celcius.isSelected()){
           WeatherData.CELCIUS = true;
       } else WeatherData.CELCIUS = false;
    }


    @FXML
    void changeLocation(ActionEvent event) {
        WeatherData.CITY_NAME = location.getValue().toString();
    }

    //TODO: add language logic
    @FXML
    void changeLanguage(ActionEvent event) {
       // languageName = language.getValue().toString();
    }

    public void initialize() {
    }


}


