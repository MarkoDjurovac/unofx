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
                @SuppressWarnings( "unchecked" )
                Spinner<Integer> spinner = ( Spinner<Integer> ) property;

                spinner.valueProperty().addListener( ( observable, oldValue, newValue ) ->
                {
                    switch( spinner.getId() )
                    {
                        case "regularCards" -> CustomGameConfiguration.REGULAR_CARDS_PER_SET = newValue;
                        case "specialCards" -> CustomGameConfiguration.SPECIAL_CARDS_PER_SET = newValue;
                        case "wildCards" -> CustomGameConfiguration.WILD_CARDS_PER_SET = newValue;
                        case "wildDrawFours" -> CustomGameConfiguration.WILD_DRAW_FOUR_CARDS_PER_SET = newValue;
                        case "cardsPerPlayer" -> CustomGameConfiguration.CARDS_PER_PLAYER = newValue;
                    }
                    isCustomGame = true;
                });
            }
            else if( property instanceof TextField textField )
            {

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