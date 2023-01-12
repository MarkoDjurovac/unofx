package com.teamuno.unofx.factory;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.model.Card;

import java.util.List;

public class DeckFactory
{
    public static void generateDefaultDeck( List<Card> deck )
    {
        for(int i = 0; i < DefaultGameConfiguration.REGULAR_CARDS_PER_SET; i++ )
        {
            for( int j = 1; j <= 9; j++ )
            {
                deck.add( new Card( CardConfiguration.COLORS.RED, j ) );
                deck.add( new Card( CardConfiguration.COLORS.YELLOW, j ) );
                deck.add( new Card( CardConfiguration.COLORS.GREEN, j ) );
                deck.add( new Card( CardConfiguration.COLORS.BLUE, j ) );
            }
        }

        for( int i = 0; i < DefaultGameConfiguration.ZERO_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.RED, 0 ) );
            deck.add( new Card( CardConfiguration.COLORS.YELLOW, 0 ) );
            deck.add( new Card( CardConfiguration.COLORS.GREEN, 0 ) );
            deck.add( new Card( CardConfiguration.COLORS.BLUE, 0 ) );
        }

        for(int i = 0; i < DefaultGameConfiguration.SPECIAL_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.RED, CardConfiguration.TYPES.SKIP, 10 ) );
            deck.add( new Card( CardConfiguration.COLORS.YELLOW, CardConfiguration.TYPES.SKIP, 10 ) );
            deck.add( new Card( CardConfiguration.COLORS.GREEN, CardConfiguration.TYPES.SKIP, 10  ) );
            deck.add( new Card( CardConfiguration.COLORS.BLUE, CardConfiguration.TYPES.SKIP, 10 ) );

            deck.add( new Card( CardConfiguration.COLORS.RED, CardConfiguration.TYPES.REVERSE, 11 ) );
            deck.add( new Card( CardConfiguration.COLORS.YELLOW, CardConfiguration.TYPES.REVERSE, 11 ) );
            deck.add( new Card( CardConfiguration.COLORS.GREEN, CardConfiguration.TYPES.REVERSE, 11 ) );
            deck.add( new Card( CardConfiguration.COLORS.BLUE, CardConfiguration.TYPES.REVERSE, 11 ) );

            deck.add( new Card( CardConfiguration.COLORS.RED, CardConfiguration.TYPES.DRAW_TWO, 12 ) );
            deck.add( new Card( CardConfiguration.COLORS.YELLOW, CardConfiguration.TYPES.DRAW_TWO, 12 ) );
            deck.add( new Card( CardConfiguration.COLORS.GREEN, CardConfiguration.TYPES.DRAW_TWO, 12 ) );
            deck.add( new Card( CardConfiguration.COLORS.BLUE, CardConfiguration.TYPES.DRAW_TWO, 12 ) );
        }

        for(int i = 0; i < DefaultGameConfiguration.WILD_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.BLACK, CardConfiguration.TYPES.WILD, 13 ) );
        }

        for( int i = 0; i < DefaultGameConfiguration.WILD_DRAW_FOUR_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.BLACK, CardConfiguration.TYPES.WILD_DRAW_FOUR, 14 ) );
        }
    }
}
