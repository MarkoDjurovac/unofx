package com.teamuno.unofx.model;

import java.util.Random;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.utilities.GameLogic;

/**
 * Represents a bot player.
 * @author Armin Saric
 */
public class Bot extends Player
{
    /**
     * Constructor for the bot.
     * Calls the super constructor.
     */
    public Bot()
    {
        super( "Robot" );
    }

    /**
     * Plays a card from the bot's hand.
     * @param game The game the bot is playing in.
     * @return The card that the bot played.
     */
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

    /**
     * Method for randomly choosing a color for a wild card.
     * @return The color that the bot chose.
     */
    public CardConfiguration.COLORS pickColor( )
    {
        Random random = new Random();
        CardConfiguration.COLORS[] colors = CardConfiguration.COLORS.values();

        return colors[ random.nextInt( 0, colors.length - 1 ) ];
    }
}