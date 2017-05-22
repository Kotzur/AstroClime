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
    import javafx.scene.control.Label;
    import javafx.scene.control.ToggleGroup;
    import javafx.event.ActionEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.paint.Color;
    import org.controlsfx.control.textfield.TextFields;

    import java.io.BufferedReader;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
import java.io.IOException;
import java.util.List;
    import java.util.stream.Collectors;

public class SettingsController {

        @FXML
        private AnchorPane settingsPane;

        @FXML
        private Label locationText;

        @FXML
        private JFXComboBox<String> locationBox;

        @FXML
        private JFXComboBox<String> languageBox;

        @FXML
        private Label invalidLocation;

        @FXML
        private ToggleGroup units;

        @FXML
        private JFXRadioButton celcius;

        @FXML
        private JFXRadioButton fahrenheit;

        @FXML
        private JFXButton backButton;

        @FXML
        private JFXRadioButton kelvin;

        @FXML
        private JFXButton changeLocationButton;

        private ObservableList<String> locationOptions;
        private ObservableList<String> languageOptions;
        private List<String> locationList;

    public SettingsController() {
    }


        private AnchorPane mainPane;

        public void initialize() throws IOException {
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



            backButton.setOnAction(event -> onExit());

            //get current location and display in location box, and add previous locations to list for drop down
            locationOptions = FXCollections.observableArrayList(locationList);
            locationBox.setItems(locationOptions);
            locationBox.getSelectionModel().select(WeatherData.CITY_NAME);
            //add autocompletion to location combo box
            TextFields.bindAutoCompletion(locationBox.getEditor(),locationBox.getItems());
            //TODO: fix so location combo box uneditable when drop down option selected
            locationBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    //check if valid city
                    if(newValue !=null) {
                        if (locationOptions.contains(newValue)) {
                            //if city valid set combobox uneditable
                            locationBox.setEditable(false);
                            locationBox.setValue(newValue);
                            //hide invalidLocation label
                            invalidLocation.setVisible(false);
                            //set text back to black
                            locationText.setTextFill(Color.BLACK);
                            locationBox.setFocusColor(Color.BLACK);
                            locationBox.setUnFocusColor(Color.BLACK);
                        } else {
                        }
                    }
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
                    locationBox.setValue("");
                    locationBox.setEditable(true);
                    locationBox.requestFocus();
                }
            });

        }



    private void onExit() {
        //check if valid city
        if (!locationBox.isEditable()) { //valid city
            //checks which unit is selected and changed variable in WeatherData
            if (celcius.isSelected()) {
                WeatherData.UNIT = Unit.C;
            } else if (fahrenheit.isSelected()) {
                WeatherData.UNIT = Unit.F;
            } else {
                WeatherData.UNIT = Unit.K;
            }
            WeatherData.CITY_NAME = locationBox.getValue().toString();


            settingsPane.getScene().setRoot(MainWindowController.getInstance().getMainPane());
            try {
				MainWindowController.getInstance().refresh();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } else
        {    //invalid city
            //make location text blue
            locationText.setTextFill(Color.BLUE);
            locationBox.setFocusColor(Color.BLUE);
            locationBox.setUnFocusColor(Color.BLUE);
            //make location label visible
            invalidLocation.setVisible(true);
        }

    }

}