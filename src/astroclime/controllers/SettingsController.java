package astroclime.controllers;

    import astroclime.backend.WeatherData;
    import com.jfoenix.controls.JFXButton;
    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXRadioButton;
    import javafx.beans.value.ChangeListener;
    import javafx.beans.value.ObservableValue;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.EventHandler;
    import javafx.fxml.FXML;
    import javafx.scene.control.ToggleGroup;
    import javafx.event.ActionEvent;

public class SettingsController {

        @FXML
        private JFXComboBox<String> locationBox;

        @FXML
        private JFXComboBox<String> languageBox;

        @FXML
        private ToggleGroup units;

        @FXML
        private JFXRadioButton celcius;

        @FXML
        private JFXRadioButton fahrenheit;


        @FXML
        private JFXRadioButton kelvin;

        @FXML
        private JFXButton changeLocationButton;

        private ObservableList<String> locationOptions;
        private ObservableList<String> languageOptions;


        public void initialize() {
                celcius.setSelected(WeatherData.CELCIUS);

                locationOptions = FXCollections.observableArrayList(WeatherData.PREVIOUS_LOCATIONS);
                locationOptions.add(WeatherData.CITY_NAME);
                FXCollections.reverse(locationOptions);
                locationBox.setItems(locationOptions);
                locationBox.getSelectionModel().selectFirst();
                locationBox.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        //TODO: add to front of list
                        if(!WeatherData.checkValidCity(newValue)){
                            locationBox.getSelectionModel().select(oldValue);
                        } else {
                            locationOptions.add(oldValue);
                        }
                }});

                languageOptions = FXCollections.observableArrayList(WeatherData.LANGUAGES);
                languageBox.setItems(languageOptions);
                languageBox.getSelectionModel().select(WeatherData.LANGUAGE);
                //:
                changeLocationButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        locationBox.getSelectionModel().clearSelection();
                        locationBox.setEditable(true);
                    }
                });

        }


        //:TODO link exit button with this fuction
        private void onExit() {
            WeatherData.CELCIUS = celcius.isSelected();
            WeatherData.CITY_NAME = locationBox.getValue().toString();
        }


    }