package com.teamuno.unofx.utilities;

import com.teamuno.unofx.configuration.BaseConfiguration;
import com.teamuno.unofx.configuration.CustomConfiguration;

public class GameFactory
{
    public static boolean isCustomGame()
    {
        if( BaseConfiguration.NUMBER_OF_REGULAR_CARDS_PER_SET == CustomConfiguration.NUMBER_OF_REGULAR_CARDS_PER_SET
            || BaseConfiguration.NUMBER_OF_SPECIAL_CARDS_PER_SET == CustomConfiguration.NUMBER_OF_SPECIAL_CARDS_PER_SET
            || BaseConfiguration.NUMBER_OF_WILD_CARDS_PER_SET == CustomConfiguration.NUMBER_OF_WILD_CARDS_PER_SET
            || BaseConfiguration.NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET == CustomConfiguration.NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET
            || BaseConfiguration.NUMBER_OF_CARDS_PER_PLAYER == CustomConfiguration.NUMBER_OF_CARDS_PER_PLAYER )
        {
            return false;
        }
        return true;
    }
}
