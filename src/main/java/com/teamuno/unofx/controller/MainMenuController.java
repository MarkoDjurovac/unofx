package com.teamuno.unofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import com.teamuno.unofx.utilities.SceneManager;

public class MainMenuController
{
    @FXML
    protected void toggleSettings(ActionEvent event) throws IOException {
        SceneManager.changeScene( event, getClass().getResource("/view/settings.fxml" ));
    }

    @FXML
    protected void startGame( ActionEvent event ) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource("/view/game.fxml" ) );
    }

    @FXML
    protected void quitProgram()
    {
        System.exit( 0 );
    }

}