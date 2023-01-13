package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.utilities.GameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
