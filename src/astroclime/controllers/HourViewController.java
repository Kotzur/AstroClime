package astroclime.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HourViewController {
    @FXML
    private Label clouds1;

    @FXML
    private Label rain1;

    @FXML
    private Label temp1;

    @FXML
    private ImageView img1;

    @FXML
    private Label clouds2;

    @FXML
    private Label rain2;

    @FXML
    private Label temp2;

    @FXML
    private ImageView img2;

    @FXML
    private Label clouds3;

    @FXML
    private Label rain3;

    @FXML
    private Label temp3;

    @FXML
    private ImageView img3;

    @FXML
    private Label clouds4;

    @FXML
    private Label rain4;

    @FXML
    private Label temp4;

    @FXML
    private ImageView img4;

    @FXML
    private Label clouds5;

    @FXML
    private Label rain5;

    @FXML
    private Label temp5;

    @FXML
    private ImageView img5;

    @FXML
    private Label clouds6;

    @FXML
    private Label rain6;

    @FXML
    private Label temp6;

    @FXML
    private ImageView img6;

    @FXML
    private Label clouds7;

    @FXML
    private Label rain7;

    @FXML
    private Label temp7;

    @FXML
    private ImageView img7;

    @FXML
    private Label clouds8;

    @FXML
    private Label rain8;

    @FXML
    private Label temp8;

    @FXML
    private ImageView img8;

    @FXML
    public void initialize() throws IOException {
        Label [] clouds = new Label[]{clouds1, clouds2, clouds3, clouds4, clouds5, clouds6, clouds7, clouds8};
        Label [] rains = new Label[]{rain1, rain2, rain3, rain4, rain5, rain6, rain7, rain8};
        Label [] temps = new Label[]{temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8};
        ImageView [] imgs = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8};


    }
}
