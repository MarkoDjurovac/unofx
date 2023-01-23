package com.teamuno.unofx.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player in the game.
 * Player is an abstract class that is extended by the Human and Bot classes.
 * @author Armin Saric
 */
public abstract class Player
{
    private String name;

    private List<Card> hand;

    /**
     * Default constructor for the Player class.
     */
    public Player()
    {
        this.name = "Player";
        this.hand = new ArrayList<>();
    }

    /**
     * Constructor for initializing a new player.
     * @param name The name of the player.
     */
    public Player( String name )
    {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Method for drawing a card from the deck.
     * @param deck The deck to draw from.
     * @return The card that was drawn.
     */
    public Card draw( Deck deck )
    {
        Card card = deck.draw();
        this.hand.add( card );

        return card;
    }

    /**
     * Getter for the player's name.
     * @return The player's name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for the player's hand.
     * @return The player's hand.
     */
    public List<Card> getHand()
    {
        return this.hand;
    }

    /**
     * Method for checking if the current player is a bot
     * @return true if the player is a bot, false otherwise.
     */
    public boolean isBot()
    {
        return this instanceof Bot;
    }
}