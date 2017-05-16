package astroclime.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.AnchorPane;


public class MapViewController {

    @FXML
    private AnchorPane mapFrame;

    @FXML
    private WebView lightMapView;

    public void initialize() {
        WebEngine webEngine = lightMapView.getEngine();
        webEngine.load("https://markwgraves.carto.com/viz/c82ff74e-5e85-11e6-9e35-0e05a8b3e3d7/embed_map");
    }

}