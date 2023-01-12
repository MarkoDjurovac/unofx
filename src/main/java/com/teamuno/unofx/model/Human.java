package com.teamuno.unofx.model;

import com.teamuno.unofx.utilities.GameLogic;

public class Human extends Player
{
    public Human( String name )
    {
        super( name );
    }

    public Card playCard( GameState game, Card card )
    {
        if( GameLogic.checkForSpecialCard( game, card ) )
        {
            game.getDeck().discard( card );
            this.getHand().remove( card );
            return card;
        }
        else
        {
            game.getDeck().discard( card );
            this.getHand().remove( card );
            return null;
        }
    }
}
