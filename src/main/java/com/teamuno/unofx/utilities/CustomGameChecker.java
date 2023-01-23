package com.teamuno.unofx.utilities;

import java.util.Map;

import com.teamuno.unofx.configuration.CustomGameConfiguration;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * Utility class that checks if the game is a custom game or not.
 * @author Marko Djurovac
 */
public class CustomGameChecker
{
    private static boolean isCustomGame = false;

    /**
     * Adds a listener to the configuration fields and checks for changes to determine if the game is a custom game or not.
     * @param customGameMap A map containing the configuration fields.
     */
    public static void checkForCustomSettings( Map<String, Object> customGameMap )
    {
        for( Object property : customGameMap.values() )
        {
            if( property instanceof Spinner )
            {
                // Suppressing unchecked cast warning because property is of type Object and Spinner is a generic class.
                // The cast is safe because the Spinner class is the only class that implements the SpinnerValueFactory interface.
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

    /**
     * Returns whether the game is a custom game or not.
     * @return true if the game is a custom game, false otherwise.
     */
    public static boolean isCustomGame()
    {
        return isCustomGame;
    }
}