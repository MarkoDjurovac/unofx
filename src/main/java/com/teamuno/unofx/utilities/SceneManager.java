package com.teamuno.unofx.utilities;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager
{
    public static void changeScene( ActionEvent event, URL fxml ) throws IOException
    {
        Parent root = FXMLLoader.load( fxml );
        Stage stage = ( Stage )( ( Node ) event.getSource() ).getScene().getWindow();
        Scene scene = new Scene( root );
        stage.setScene( scene );
        scene.getStylesheets().add( "/style/style.css" );
        stage.show();
    }
}
