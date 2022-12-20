package com.teamuno.unofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuController
{
    @FXML
    private VBox settingsMenu;

    @FXML
    protected void toggleSettings( ActionEvent event )
    {
        if( !settingsMenu.isVisible() )
        {
            settingsMenu.setVisible( true );
        }
        else
        {
            settingsMenu.setVisible( false );
        }
    }

    @FXML
    protected void startGame( ActionEvent event ) throws IOException
    {
        SceneController sceneController = new SceneController();
        sceneController.changeScene( event, "/view/game.fxml" );
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneController sceneController = new SceneController();
        sceneController.changeScene( event, "/view/main-menu.fxml" );
    }
}