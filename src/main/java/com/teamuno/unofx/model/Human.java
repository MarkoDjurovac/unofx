package com.teamuno.unofx.model;

import com.teamuno.unofx.guicontroller.GameController;
import com.teamuno.unofx.utilities.UnoHelper;

public class Human extends Player
{
    public Human()
    {
        super();
    }

    public Human( String name )
    {
        super( name );
    }

    public void playCard( GameController gc, Card card )
    {
        this.getHand().remove( card );
        gc.getDeck().discardCard( card );
        UnoHelper.checkForSpecialCards( gc, card );
    }
}