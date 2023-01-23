package com.teamuno.unofx.utilities;

import com.teamuno.unofx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

/**
 * Utility class for scene switching.
 * @author Marko Djurovac
 */
public class SceneManager
{
    /**
     * Changes the scene to the given scene.
     * @param event The event that triggered the method.
     * @param fxml The fxml file of the scene.
     * @throws IOException when the scene could not be changed.
     */
    public static void changeScene( ActionEvent event, URL fxml ) throws IOException
    {
        Parent root = FXMLLoader.load( fxml );
        Stage stage = ( Stage )( ( Node ) event.getSource() ).getScene().getWindow();
        Scene scene = new Scene( root );
        stage.setScene( scene );
        scene.getStylesheets().add( Main.class.getResource( "/style/style.css" ).toString() );
        stage.show();
    }

    /**
     * Ends the game and returns to the main menu
     * @param fxml The fxml file of the scene.
     * @param currentStage The current stage.
     * @throws IOException when the scene could not be changed.
     */
    public static void endGame( URL fxml, Stage currentStage ) throws IOException
    {
        Parent root = FXMLLoader.load( fxml );
        Stage stage = currentStage;
        Scene scene = new Scene( root );
        stage.setScene( scene );
        scene.getStylesheets().add( Main.class.getResource( "/style/style.css").toString() );
        stage.show();
    }
}