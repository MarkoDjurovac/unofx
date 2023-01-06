package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.StdSettings;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    Deck deck;
    List<Player> players;
    Player currentPlayer;

    public Game( Player player )
    {
        this.players = new ArrayList<>();
        this.players.add( player );
        this.players.add( new Bot() );
        this.deck = new Deck();
        this.currentPlayer = players.get( 0 );
        this.deck.shuffle();
        this.dealCards();
        this.deck.discardCard( this.deck.drawCard() );
    }

    public List<Player> getPlayers()
    {
        return this.players;
    }

    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public void dealCards()
    {
        for(int i = 0; i < StdSettings.NUMBER_OF_CARDS_PER_PLAYER; i++ )
        {
            for( Player player : this.players )
            {
                player.drawCard( this.deck );
            }
        }
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

        if( card.getType() == StdSettings.CARD_TYPES.WILD )
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

    public Deck getDeck()
    {
        return this.deck;
    }
}
