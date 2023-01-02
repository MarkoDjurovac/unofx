package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.BaseConfiguration;

import java.util.List;

public class Game
{
    Deck deck;
    List<Player> players;
    Player currentPlayer;

    public Game( List<Player> players )
    {
        this.players = players;
        this.deck = new Deck();
        this.currentPlayer = players.get( 0 );
    }

    boolean isValidMove( Card card )
    {
        if( card.getColor() == deck.getTopCard().getColor() )
        {
            return true;
        }

        if( card.getNumber() == deck.getTopCard().getNumber() )
        {
            return true;
        }

        if( card.getType() == BaseConfiguration.CARD_TYPES.WILD )
        {
            return true;
        }

        return false;
    }

    public boolean hasPlayerWon( Player currentPlayer )
    {
        if( currentPlayer.getHand().size() == 0 )
        {
            return true;
        }

        return false;
    }

    public boolean isUno( Player currentPlayer )
    {
        if( currentPlayer.getHand().size() == 1 )
        {
            return true;
        }

        return false;
    }
}
