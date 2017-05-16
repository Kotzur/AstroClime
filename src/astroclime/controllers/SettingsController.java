package astroclime.controllers;

    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXToggleButton;
    import javafx.fxml.FXML;
    import javafx.scene.control.Toggle;
    import javafx.scene.control.ToggleGroup;
    import javafx.event.ActionEvent;

public class SettingsController {
    String currentUnitName = "celcius";
    String locationName = "Cambridge";
    String languageName = "English";

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

    @FXML
    void changeUnits(ActionEvent event) {
       currentUnitName = units.getSelectedToggle().toString();
    }


    @FXML
    void changeLocation(ActionEvent event) {
      locationName = location.getValue().toString();
    }

    @FXML
    void changeLanguage(ActionEvent event) {
        languageName = language.getValue().toString();
    }

    public void initialize() {
    }
}


