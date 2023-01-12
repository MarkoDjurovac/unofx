package com.teamuno.unofx.model;

import com.teamuno.unofx.utilities.GameLogic;

public class Bot extends Player
{
    public Bot()
    {
        super( "Robot" );
    }

    public Card playCard( GameState game )
    {
        for( Card card : this.getHand() )
        {
            if( GameLogic.isValidMove( game.getDeck().getTopCard(), card ) )
            {
                if( GameLogic.checkForSpecialCard( game, card ) )
                {
                    game.getDeck().discard( card );
                    this.getHand().remove( card );
                    return card;
                }

                game.getDeck().discard( card );
                this.getHand().remove( card );
                return card;
            }
        }

        this.draw( game.getDeck() );

        return null;
    }
}
