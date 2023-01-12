package com.teamuno.unofx.model;

import com.teamuno.unofx.utilities.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class GameState
{
    Deck deck;
    private List<Player> playerList;
    private Player currentPlayer;

    public GameState( Player player )
    {
        this.playerList = new ArrayList<>();
        this.startGame( player );
    }

    public void startGame( Player player )
    {
        this.deck = new Deck();
        this.playerList.add( player );
        this.playerList.add( new Bot() );
        this.currentPlayer = this.playerList.get( 0 );
        GameLogic.dealCards( this.playerList, this.deck );
        this.deck.discard( this.deck.draw() );
    }

    public List<Player> getPlayerList()
    {
        return this.playerList;
    }

    public void setPlayerList( List<Player> value )
    {
        this.playerList = value;
    }

    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public Player getNextPlayer()
    {
        int index = this.playerList.indexOf( this.currentPlayer );
        int nextPlayerIndex = ( index + 1 ) % playerList.size();

        return this.playerList.get( nextPlayerIndex );
    }

    public void setCurrentPlayer( Player player )
    {
        this.currentPlayer = player;
    }

    public Deck getDeck()
    {
        return this.deck;
    }
}
