package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.BaseConfiguration;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
    private ArrayList<Card> deck_game;
    private ArrayList<Card> deck_discarded;
    private Random random;

    public Deck()
    {
        this.deck_game = new ArrayList<>();
        this.deck_discarded = new ArrayList<>();
        this.random = new Random();
        this.populateDeck();
    }

    public void populateDeck()
    {
        for( int i = 1; i <= 9; i++ )
        {
            for(int j = 0; j < BaseConfiguration.NUMBER_OF_REGULAR_CARDS_PER_SET; j++ )
            {
                this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.RED, i ) );
                this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, i ) );
                this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, i ) );
                this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, i ) );
            }
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_ZERO_CARDS_PER_SET; i++ )
        {
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.RED, 0 ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, 0 ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, 0 ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, 0 ) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_SPECIAL_CARDS_PER_SET; i++ )
        {
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.SKIP ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.SKIP ) );

            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.REVERSE ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.REVERSE ) );

            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.RED, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.YELLOW, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.GREEN, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.BLUE, BaseConfiguration.CARD_TYPES.DRAW_TWO ) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_WILD_CARDS_PER_SET; i++ )
        {
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.NONE, BaseConfiguration.CARD_TYPES.WILD ) );
        }

        for( int i = 0; i < BaseConfiguration.NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET; i++ )
        {
            this.deck_game.add( new Card( BaseConfiguration.CARD_COLORS.NONE, BaseConfiguration.CARD_TYPES.WILD_DRAW_FOUR ) );
        }
    }

    public void shuffleDeck()
    {
        for( int i = 0; i < this.deck_game.size(); i++ )
        {
            int a = this.random.nextInt( this.deck_game.size() );
            int b = this.random.nextInt( this.deck_game.size() );

            Card temp = this.deck_game.get( a );
            this.deck_game.set( a, this.deck_game.get( b ) );
            this.deck_game.set( b, temp );
        }
    }

    public boolean isEmpty()
    {
        return this.deck_game.isEmpty();
    }

    public Card drawCard()
    {
        return this.deck_game.remove( 0 );
    }

    public void discardCard( Card card )
    {
        this.deck_discarded.add( card );
    }
}