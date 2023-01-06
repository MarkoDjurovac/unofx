package com.teamuno.unofx.model;

import com.teamuno.unofx.factory.DeckFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck
{
    private List<Card> deck;
    private List<Card> discarded;
    private Random random;

    public Deck()
    {
        this.deck = new ArrayList<>();
        this.discarded = new ArrayList<>();
        this.random = new Random();
        DeckFactory.generateCards( this.deck );
        this.shuffle();
    }

    public void shuffle()
    {
        for(int i = 0; i < this.deck.size(); i++ )
        {
            int a = this.random.nextInt( this.deck.size() );
            int b = this.random.nextInt( this.deck.size() );

            Card temp = this.deck.get( a );
            this.deck.set( a, this.deck.get( b ) );
            this.deck.set( b, temp );
        }
    }

    public boolean isEmpty()
    {
        return this.deck.isEmpty();
    }

    public Card drawCard()
    {
        return this.deck.remove( 0 );
    }

    public void discardCard( Card card )
    {
        this.discarded.add( card );
    }

    public List<Card> getDeck()
    {
        return this.deck;
    }

    public Card getTopCard()
    {
        return this.discarded.get( 0 );
    }
}