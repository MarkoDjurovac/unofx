package com.teamuno.unofx.utilities;

import com.teamuno.unofx.configuration.StdSettings;
import com.teamuno.unofx.guicontroller.GameController;
import com.teamuno.unofx.model.Card;

public class UnoHelper
{
    public static boolean isValidMove( Card topCard, Card card )
    {
        if( topCard.getColor() == card.getColor() || topCard.getType() == card.getType() )
        {
            return true;
        }
        else if( card.getType() == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
        {
            return true;
        }
        else if( card.getType() == StdSettings.CARD_TYPES.WILD )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void checkForSpecialCards(GameController gc, Card card )
    {
        switch( card.getType() )
        {
            case WILD:
                gc.wildCard();
                break;
            case WILD_DRAW_FOUR:
                gc.wildDrawFour();
                break;
            case DRAW_TWO:
                gc.drawTwo();
                break;
            case SKIP:
                gc.skip();
                break;
            case REVERSE:
                gc.reverse();
                break;
            default:
                break;
        }
    }
}
