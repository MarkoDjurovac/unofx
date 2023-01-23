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

    /**
     * Constructor for the GameState class.
     */
    public GameState()
    {
        this.playerList = new ArrayList<>();

        this.startGame( CustomGameChecker.isCustomGame() ? new Human( CustomGameConfiguration.PLAYER_NAME ) : new Human( DefaultGameConfiguration.PLAYER_NAME ) );
    }

    /**
     * Starts a new game.
     * @param player The player to add to the game.
     */
    public void startGame( Player player )
    {
        this.deck = new Deck();
        this.playerList.add( player );
        this.playerList.add( new Bot() );
        this.currentPlayer = this.playerList.get( 0 );
        GameLogic.dealCards( this.playerList, this.deck );
        this.deck.discard( this.deck.draw() );
    }

    /**
     * Getter for the player list.
     * @return playerList The list of players.
     */
    public List<Player> getPlayerList()
    {
        return this.playerList;
    }

    /**
     * Getter for the current player.
     * @return currentPlayer The current player.
     */
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * Getter for the next player
     * @return The next player.
     */
    public Player getNextPlayer()
    {
        int index = this.playerList.indexOf( this.currentPlayer );
        int nextPlayerIndex = ( index + 1 ) % playerList.size();

        return this.playerList.get( nextPlayerIndex );
    }

    /**
     * Setter for the current player.
     */
    public void setCurrentPlayer( Player player )
    {
        this.currentPlayer = player;
    }

    /**
     * Getter for the deck.
     * @return deck The deck.
     */
    public Deck getDeck()
    {
        return this.deck;
    }
}