package com.teamuno.unofx.model;

import com.teamuno.unofx.guicontroller.GameController;
import com.teamuno.unofx.utilities.UnoHelper;

import java.util.Random;

public class Bot extends Player
{
    public Bot()
    {
        super( "Robot");
    }

    public void playCard( GameController gc, Card card )
    {
        Card randomCard = this.getHand().get( new Random().nextInt(0, this.getHand().size() ) );

        if( UnoHelper.isValidMove( gc.getDeck().getTopCard(), randomCard ) )
        {
            this.getHand().remove( randomCard );
            gc.getDeck().discardCard( randomCard );
            gc.botPlayCardFx( randomCard );
            UnoHelper.checkForSpecialCards( gc, randomCard );
            gc.checkForUno();
            gc.checkForWin();
        }
        else
        {
            gc.drawCardFx( this );
        }
    }
}
