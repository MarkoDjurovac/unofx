package com.teamuno.unofx.model;

import com.teamuno.unofx.utilities.GameLogic;

/**
 * Represents the human player.
 * This class extends the Player class.
 * @author Armin Saric
 */
public class Human extends Player
{
    /**
     * Constructor for the human player.
     * Calls the super constructor.
     * @param name The name of the human player.
     */
    public Human( String name )
    {
        super( name );
    }

    /**
     * Method for playing a card from the human player's hand.
     * @param game The game the human player is playing in.
     * @param card The card the human player wants to play.
     * @return The card that the human player played.
     */
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