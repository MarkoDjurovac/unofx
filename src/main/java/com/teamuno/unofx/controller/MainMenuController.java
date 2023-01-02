package com.teamuno.unofx.controller;

import com.teamuno.unofx.configuration.BaseConfiguration;
import com.teamuno.unofx.configuration.CustomConfiguration;
import com.teamuno.unofx.model.Bot;
import com.teamuno.unofx.model.Game;
import com.teamuno.unofx.model.Player;
import com.teamuno.unofx.utilities.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuController
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
        SceneUtils.changeScene( event, getClass().getResource("/view/game_view.fxml") );
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneUtils.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    protected void quitProgram( ActionEvent event ) throws IOException
    {
        System.exit( 0 );
    }
}