package astroclime.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.AnchorPane;


public class MapViewController {

    @FXML // this is the main frame object
    private AnchorPane mapFrame;

    @FXML //This is the WebView object that outputs the light map onto the screen
    private WebView lightMapView;

    //this initialize meathod is called when the frame is created; It loads in the webpage to the Webview object
    //so that it displays the website with the light map onto the frame.
    public void initialize() {
        WebEngine webEngine = lightMapView.getEngine();
        webEngine.load("https://markwgraves.carto.com/viz/c82ff74e-5e85-11e6-9e35-0e05a8b3e3d7/embed_map"); //loads the web page into the WebViewer object
    }

}