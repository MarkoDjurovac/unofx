package com.teamuno.unofx.utilities;

import com.teamuno.unofx.configuration.StdSettings;
import com.teamuno.unofx.guicontroller.GameController;
import com.teamuno.unofx.model.Card;

public class UnoHelper
{
    public static boolean isValidMove( Card topCard, Card card )
    {
        if( card == null )
        {
            return false;
        }
        else
        {
            if( topCard.getType() == StdSettings.CARD_TYPES.WILD || topCard.getType() == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
            {
                return true;
            }

            if( card.getType() == StdSettings.CARD_TYPES.WILD || card.getType() == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
            {
                return true;
            }

            if( card.getColor() == topCard.getColor()  )
            {
                return true;
            }

            if( card.getNumber() == topCard.getNumber() )
            {
                return true;
            }
        }

        return false;
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
