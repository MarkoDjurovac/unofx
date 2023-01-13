package com.teamuno.unofx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.teamuno.unofx.factory.DeckFactory;

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
        if( this.deck.size() == 0 )
        {
            Card topCard = this.discardPile.get( 0 );
            this.deck = this.discardPile;
            this.discardPile = new ArrayList<>();
            this.discardPile.add( topCard );
            this.shuffle();

            return this.deck.remove( 0 );
        }

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
}