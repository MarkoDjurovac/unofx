package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.BaseConfiguration;

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
        this.generateCards();
    }

    public void generateCards()
    {
        for( int i = 0; i < BaseConfiguration.NUMBER_OF_REGULAR_CARDS_PER_SET; i++ )
        {
            for( int j = 1; j <= 9; j++ )
            {
                this.deck.add( new Card( BaseConfiguration.CARD_COLORS.RED, j ) );
                this.deck.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, j ) );
                this.deck.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, j ) );
                this.deck.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, j ) );
            }
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_ZERO_CARDS_PER_SET; i++ )
        {
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.RED, 0 ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, 0 ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, 0 ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, 0 ) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_SPECIAL_CARDS_PER_SET; i++ )
        {
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.SKIP ) );

            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.REVERSE ) );

            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_WILD_CARDS_PER_SET; i++ )
        {
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.NONE, BaseConfiguration.CARD_TYPES.WILD) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET; i++ )
        {
            this.deck.add( new Card( BaseConfiguration.CARD_COLORS.NONE, BaseConfiguration.CARD_TYPES.WILD_DRAW_FOUR ) );
        }
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