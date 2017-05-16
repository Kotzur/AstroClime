package astroclime.controllers;

    import astroclime.backend.WeatherData;
    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXRadioButton;
    import javafx.fxml.FXML;
    import javafx.scene.control.ToggleGroup;
    import javafx.event.ActionEvent;

    public class SettingsController {

        @FXML
        private JFXComboBox<?> location;

        @FXML
        private JFXComboBox<?> language;

        @FXML
        private ToggleGroup units;

        @FXML
        private JFXRadioButton celcius;

        @FXML
        private JFXRadioButton fahrenheit;


        @FXML
        private JFXRadioButton kelvin;

        //TODO: add logic to check location is valid
        @FXML
        void changeLocation(ActionEvent event) {

        }

        private void onExit() {
            WeatherData.CELCIUS = celcius.isSelected();
            WeatherData.CITY_NAME = location.getValue().toString();
        }


    }