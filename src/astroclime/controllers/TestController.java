package astroclime.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by kocag on 11/05/2017.
 */
public class TestController {
    //Variables
    @FXML
    Button enterButton;
    Parent root;
    Stage stage;


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
