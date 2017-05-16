package astroclime.controllers;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TestController {
    //Variables
    @FXML
    Button enterButton;

    @FXML
    JFXHamburger h1;
    @FXML
    HamburgerSlideCloseTransition burgerTask;

    @FXML
    TextField field;
    @FXML
    TextField validationField;
    @FXML
    RequiredFieldValidator validator;

    Parent root;
    Stage stage;

    public void handleKeyPressed(KeyEvent event) throws IOException{
        if(event.getSource() == validationField){
            if(!(event.getText().isEmpty())) validator.validate();
        }
    }

    public void handleMouseClick(MouseEvent event) throws IOException{
        if(event.getSource() == h1){
            burgerTask.setRate(burgerTask.getRate()*-1);
            burgerTask.play();
        }
    }

    public void handleTest1Click(ActionEvent event) throws IOException {
        if(event.getSource() == enterButton){
            stage = (Stage) enterButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../scenes/test2.fxml"));
        }

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();

    }

    public void handleTest2Click(ActionEvent event) throws IOException {
        if(event.getSource() == enterButton){
            stage = (Stage) enterButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../scenes/test.fxml"));
        }
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
