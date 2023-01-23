package com.teamuno.unofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import com.teamuno.unofx.utilities.SceneManager;

/**
 * Controller for the main menu scene.
 * @author Marc Göring
 */
public class MainMenuController
{
    /**
     * Changes the scene to the settings scene.
     * @param event The event that triggered the method.
     * @throws IOException when the scene could not be changed.
     */
    @FXML
    protected void toggleSettings(ActionEvent event) throws IOException {
        SceneManager.changeScene( event, getClass().getResource("/view/settings.fxml" ));
    }

    /**
     * Changes the scene to the game scene and starts a new game.
     * @param event The event that triggered the method.
     * @throws IOException when the scene could not be changed.
     */
    @FXML
    protected void startGame( ActionEvent event ) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource("/view/game.fxml" ) );
    }

    /**
     * Quits the program.
     */
    @FXML
    protected void quitProgram()
    {
        System.exit( 0 );
    }
}