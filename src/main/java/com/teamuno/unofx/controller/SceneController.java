package com.teamuno.unofx.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeScene( ActionEvent event, String fxml ) throws IOException
    {
        root = FXMLLoader.load( getClass().getResource( fxml ) );
        stage = ( Stage )( ( Node ) event.getSource() ).getScene().getWindow();
        scene = new Scene( root );
        stage.setScene( scene );
        scene.getStylesheets().add("/style/style.css");
        stage.show();
    }
}