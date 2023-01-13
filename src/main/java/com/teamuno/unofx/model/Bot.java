package com.teamuno.unofx.model;

import java.util.Random;

import com.teamuno.unofx.configuration.CardConfiguration;
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

    public CardConfiguration.COLORS pickColor( )
    {
        Random random = new Random();
        CardConfiguration.COLORS[] colors = CardConfiguration.COLORS.values();

        return colors[ random.nextInt( 0, colors.length - 1 ) ];
    }
}
