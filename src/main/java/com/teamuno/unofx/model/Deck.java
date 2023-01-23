package com.teamuno.unofx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.teamuno.unofx.factory.DeckFactory;

/**
 * Represents the uno deck.
 * @author Armin Saric
 */
public class Deck
{
    private List<Card> deck;

    private List<Card> discardPile;

    private Random random;

    public Deck()
    {
        this.deck = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.random = new Random();
        DeckFactory.generateDeck( this.deck );
        this.shuffle();
    }

    /**
     * Method for shuffling the deck.
     */
    public void shuffle()
    {
        for( int i = 0; i < this.deck.size(); i++ )
        {
            int a = this.random.nextInt( this.deck.size() );
            int b = this.random.nextInt( this.deck.size() );
            Card temp = this.deck.get( a );
            this.deck.set( a, this.deck.get( b ) );
            this.deck.set( b, temp );
        }
    }

    /**
     * Method for drawing a card from the deck.
     * @return the card that has been drawn.
     */
    public Card draw()
    {
        if( this.deck.size() == 0 )
        {
            Card topCard = this.getTopCard();
            this.deck.addAll( this.discardPile );
            this.discardPile.clear();
            this.shuffle();
            this.discardPile.add( topCard );
        }

        return this.deck.remove( 0 );
    }

    /**
     * Method for adding a card to the discard pile.
     * @param card the card that has to be added to the discard pile.
     */
    public void discard( Card card )
    {
        this.discardPile.add( 0, card );
    }

    /**
     * Getter for top card of the discard pile.
     * @return the top card of the discard pile.
     */
    public Card getTopCard()
    {
        return this.discardPile.get( 0 );
    }
}