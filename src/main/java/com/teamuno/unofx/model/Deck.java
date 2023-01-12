package com.teamuno.unofx.model;

import com.teamuno.unofx.factory.DeckFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        DeckFactory.generateDefaultDeck( this.deck );

        this.shuffle();
    }

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

    public Card draw()
    {
        return this.deck.remove( 0 );
    }

    public void discard( Card card )
    {
        this.discardPile.add( 0, card );
    }

    public Card getTopCard()
    {
        return this.discardPile.get( 0 );
    }

    public boolean isEmpty()
    {
        return this.deck.isEmpty();
    }
}
