package astroclime.controllers;

    import astroclime.backend.Unit;
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
    import javafx.scene.text.TextBuilder;
    import org.controlsfx.control.textfield.TextFields;

    import java.io.BufferedReader;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.util.Collections;
    import java.util.List;
    import java.util.stream.Collectors;

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
        private List<String> locationList;


        public void initialize() {
            //initialize unit button based on value in WeatherData
            if(WeatherData.UNIT == Unit.C) {
                celcius.setSelected(true);
            } else if(WeatherData.UNIT == Unit.F) {
                fahrenheit.setSelected(true);
            } else {
                kelvin.setSelected(true);
            }
            //read in list of cities and store in locationList
            try {
                BufferedReader br = new BufferedReader(new FileReader("cityList.txt"));
                locationList = br.lines().collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //get current location and display in location box, and add previous locations to list for drop down
            locationBox.setItems(FXCollections.observableArrayList(locationList));
            locationBox.getSelectionModel().select(WeatherData.CITY_NAME);
            //add autocompletion to location combo box
            TextFields.bindAutoCompletion(locationBox.getEditor(),locationBox.getItems());
            //TODO: fix so location combo box uneditable when drop down option selected
            locationBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    locationBox.setEditable(false);
                }
            });

            //load list of language options into the language combobox and set current language
            languageOptions = FXCollections.observableArrayList(WeatherData.LANGUAGES);
            languageBox.setItems(languageOptions);
            languageBox.getSelectionModel().select(WeatherData.LANGUAGE);

            //add listener to button to clear and change location to right of location combobox
            changeLocationButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    locationBox.setEditable(true);
                    locationBox.getSelectionModel().clearSelection();
                }
            });

        }


        //:TODO link exit button with this fuction
        private void onExit() {
            //checks which unit is selected and changed variable in WeatherData
            if(celcius.isSelected()) {
                WeatherData.UNIT = Unit.C;
            } else if(fahrenheit.isSelected()) {
                WeatherData.UNIT = Unit.F;
            } else {
                WeatherData.UNIT = Unit.K;
            }
            WeatherData.CITY_NAME = locationBox.getValue().toString();
        }

}