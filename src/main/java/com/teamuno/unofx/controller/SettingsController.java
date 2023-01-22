package com.teamuno.unofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.utilities.CustomGameChecker;
import com.teamuno.unofx.utilities.SceneManager;
public class SettingsController {
    @FXML
    private VBox settingsMenu;

    @FXML
    private TextField playerName;
    @FXML
    private Spinner<Integer> regularCards;

    @FXML
    private Spinner<Integer> specialCards;

    @FXML
    private Spinner<Integer> wildCards;

    @FXML
    private Spinner<Integer> wildDrawFours;

    @FXML
    private Spinner<Integer> cardsPerPlayer;

    @FXML
    protected void startInSettings(ActionEvent event) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource("/view/game.fxml" ) );
    }

    @FXML
    protected void backToMenu(ActionEvent event) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    public void initialize()
    {
        this.playerName.setText(DefaultGameConfiguration.PLAYER_NAME );
        this.settingsMenu.setVisible( true );
        CustomGameChecker.checkForCustomSettings( this.generateCustomGameMap() );

    }

    protected Map<String, Object > generateCustomGameMap()
    {
        Map<String, Object> customGameMap = new HashMap<>();

        customGameMap.put( "playerName", this.playerName );
        customGameMap.put( "regularCards", this.regularCards );
        customGameMap.put( "specialCards", this.specialCards );
        customGameMap.put( "wildCards", this.wildCards );
        customGameMap.put( "wildDrawFours", this.wildDrawFours );
        customGameMap.put( "cardsPerPlayer", this.cardsPerPlayer );

        return customGameMap;
    }

    @FXML
    protected void quitProgram()
    {
        System.exit( 0 );
    }

}
