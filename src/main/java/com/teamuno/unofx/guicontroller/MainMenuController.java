package com.teamuno.unofx.guicontroller;

import com.teamuno.unofx.utilities.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuController
{
    @FXML
    private VBox settingsMenu;

    @FXML
    protected void toggleSettings()
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
    protected void quitProgram()
    {
        System.exit( 0 );
    }
}