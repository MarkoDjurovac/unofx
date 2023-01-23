package com.teamuno.unofx.utilities;

import java.util.List;
import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.configuration.CustomGameConfiguration;
import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.model.*;

/**
 * GameLogic class is responsible for handling the game logic.
 * @author Armin Saric
 */
public class GameLogic
{
    /**
     * Method for dealing cards to the players.
     * @param playerList The list of players.
     * @param deck The deck to draw from.
     */
    public static void dealCards( List<Player> playerList, Deck deck )
    {
        int numberOfCards = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.CARDS_PER_PLAYER : DefaultGameConfiguration.CARDS_PER_PLAYER;

        for( int i = 0; i < numberOfCards; i++ )
        {
            for( Player player : playerList)
            {
                player.getHand().add( deck.draw() );
            }
        }
    }

    /**
     * Method for checking if the player can play a card.
     * @param topCard the top card on the discard pile.
     * @param card The card to check.
     * @return True if the player can play the card, false otherwise.
     */
    public static boolean isValidMove( Card topCard, Card card )
    {
        if( card.getColor() == topCard.getColor() )
        {
            return true;
        }

        if( card.getType() == CardConfiguration.TYPES.WILD || card.getType() == CardConfiguration.TYPES.WILD_DRAW_FOUR )
        {
            return true;
        }

        if( topCard.getType() == CardConfiguration.TYPES.WILD || topCard.getType() == CardConfiguration.TYPES.WILD_DRAW_FOUR )
        {
            return true;
        }

        if( card.getNumber() == topCard.getNumber() )
        {
            return true;
        }

        return false;
    }

    /**
     * Method for checking if card is a special card.
     * @param game The game the player is playing in.
     * @param card The card to check.
     * @return True if it is a special card, false otherwise.
     */
    public static boolean checkForSpecialCard( GameState game, Card card )
    {
        switch( card.getType() )
        {
            case WILD_DRAW_FOUR -> wildDrawFour( game );
            case DRAW_TWO -> drawTwo( game );
            default -> {
            }
        }

        return false;
    }

    /**
     * Method for handling the wild draw four card.
     * Makes the next player draw four cards.
     * @param game The game the player is playing in.
     */
    public static void wildDrawFour( GameState game )
    {
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
    }

    /**
     * Method for handling the draw two card.
     * Makes the next player draw two cards.
     * @param game The game the player is playing in.
     */
    public static void drawTwo( GameState game )
    {
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
        game.getNextPlayer().getHand().add( game.getDeck().draw() );
    }

    /**
     * Method for checking if the player has uno.
     * @param player The player to check.
     * @return True if the player has uno, false otherwise.
     */
    public static boolean checkForUno( Player player )
    {
        if( player.getHand().size() == 1 )
        {
            return true;
        }

        return false;
    }

    /**
     * Method for checking if the player has won.
     * @param player The player to check.
     * @return True if the player has won, false otherwise.
     */
    public static boolean checkForWin( Player player )
    {
        if( player.getHand().size() == 0 )
        {
            return true;
        }

        return false;
    }
}