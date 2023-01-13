package com.teamuno.unofx.utilities;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.configuration.CustomGameConfiguration;
import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameLogic
{
    public static void dealCards( List<Player> playerList, Deck deck )
    {
        int numberOfCards = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.CARDS_PER_PLAYER : DefaultGameConfiguration.CARDS_PER_PLAYER;

        for( int i = 0; i < numberOfCards; i++ )
        {
            for (Player player : playerList)
            {
                player.getHand().add( deck.draw() );
            }
        }
    }

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

    public static Card playCard( Human human, GameState game, Card card )
    {
        return human.playCard( game, card );
    }

    public static boolean checkForSpecialCard( GameState game, Card card )
    {
        switch( card.getType() )
        {
            case WILD_DRAW_FOUR -> wildDrawFour( game );
            case DRAW_TWO -> drawTwo( game );
            case SKIP -> {
                // skip( game );
            }
            case REVERSE -> {
                // reverse( game );
            }
            default -> {

            }
        }

        return false;
    }

    public static void wildDrawFour( GameState game )
    {
        int index = game.getPlayerList().indexOf( game.getCurrentPlayer() );
        int nextPlayerIndex = ( index + 1 ) % game.getPlayerList().size();

        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
    }

    public static void drawTwo( GameState game )
    {
        int index = game.getPlayerList().indexOf( game.getCurrentPlayer() );
        int nextPlayerIndex = ( index + 1 ) % game.getPlayerList().size();

        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
        game.getPlayerList().get( nextPlayerIndex ).getHand().add( game.getDeck().draw() );
    }

    public static void skip( GameState game )
    {
        int index = game.getPlayerList().indexOf( game.getCurrentPlayer() );

        if( index == game.getPlayerList().size() - 1 )
        {
            game.setCurrentPlayer( game.getPlayerList().get( 0 ) );
        }
        else
        {
            game.setCurrentPlayer( game.getPlayerList().get( index + 1 ) );
        }
    }

    public static void reverse( GameState game )
    {
        List<Player> reversedPlayerList = new ArrayList<>();

        for( int i = game.getPlayerList().size() - 1; i >= 0; i-- )
        {
            reversedPlayerList.add( game.getPlayerList().get( i ) );
        }

        game.setPlayerList( reversedPlayerList );
    }

    public static boolean checkForUno( Player player )
    {
        if( player.getHand().size() == 1 )
        {
            return true;
        }

        return false;
    }

    public static boolean checkForWin( Player player )
    {
        if( player.getHand().size() == 0 )
        {
            return true;
        }

        return false;
    }
}
