package com.teamuno.unofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class of the application.
 * @author Marko Djurovac
 */
public class Main extends Application
{
    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException when the scene could not be changed.
     */
    @Override
    public void start( Stage stage ) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "/view/main-menu.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load(), 800, 600 );
        stage.getIcons().add( new Image( getClass().getResourceAsStream( "/img/icon_512.png" ) ) );
        scene.getStylesheets().add( getClass().getResource( "/style/style.css" ).toString() );
        stage.setTitle( "UnoFX" );
        stage.setScene( scene );
        stage.setResizable( false );
        stage.show();
    }

    /**
     * Entry point of the application.
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        launch( args );
    }
}