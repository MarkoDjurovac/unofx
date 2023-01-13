package com.teamuno.unofx.utilities;

import java.util.Map;

import com.teamuno.unofx.configuration.CustomGameConfiguration;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class CustomGameChecker
{
    private static boolean isCustomGame = false;

    public static void checkForCustomSettings( Map<String, Object> customGameMap )
    {
        for( Object property : customGameMap.values() )
        {
            if( property instanceof Spinner )
            {
                Spinner spinner = ( Spinner ) property;

                spinner.valueProperty().addListener( ( observable, oldValue, newValue ) ->
                {
                    switch( spinner.getId() )
                    {
                        case "regularCards" -> CustomGameConfiguration.REGULAR_CARDS_PER_SET = ( int ) newValue;
                        case "specialCards" -> CustomGameConfiguration.SPECIAL_CARDS_PER_SET = ( int ) newValue;
                        case "wildCards" -> CustomGameConfiguration.WILD_CARDS_PER_SET = ( int ) newValue;
                        case "wildDrawFours" -> CustomGameConfiguration.WILD_DRAW_FOUR_CARDS_PER_SET = ( int ) newValue;
                        case "cardsPerPlayer" -> CustomGameConfiguration.CARDS_PER_PLAYER = ( int ) newValue;
                    }
                    isCustomGame = true;
                });
            }
            else if( property instanceof TextField )
            {
                TextField textField = ( TextField ) property;

                textField.textProperty().addListener( ( observable, oldValue, newValue ) ->
                {
                    CustomGameConfiguration.PLAYER_NAME = newValue;
                    isCustomGame = true;
                });
            }
        }
    }

    public static boolean isCustomGame()
    {
        return isCustomGame;
    }
}