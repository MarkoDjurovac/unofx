package com.teamuno.unofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start( Stage stage ) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/view/main-menu.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load(), 800, 600 );
        stage.getIcons().add( new Image( getClass().getResourceAsStream("/img/icon_512.png" ) ) );
        scene.getStylesheets().add("/style/style.css");
        stage.setTitle( "UnoFX" );
        stage.setScene( scene );
        stage.setResizable( false );
        stage.show();
    }

    public static void main( String[] args )
    {
        launch( args );
    }
}