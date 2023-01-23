package com.teamuno.unofx.model;

import java.util.ArrayList;
import java.util.List;

import com.teamuno.unofx.configuration.CustomGameConfiguration;
import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.utilities.CustomGameChecker;
import com.teamuno.unofx.utilities.GameLogic;

/**
 * GameState class is responsible for storing the current state of the game
 * @author Armin Saric
 */
public class GameState
{
    Deck deck;

    private List<Player> playerList;

    private Player currentPlayer;

    public GameState()
    {
        this.playerList = new ArrayList<>();

        this.startGame( CustomGameChecker.isCustomGame() ? new Human( CustomGameConfiguration.PLAYER_NAME ) : new Human( DefaultGameConfiguration.PLAYER_NAME ) );
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