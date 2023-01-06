package com.teamuno.unofx.factory;

import com.teamuno.unofx.configuration.StdSettings;
import com.teamuno.unofx.model.Card;

import java.util.List;

public class DeckFactory
{
    public static void generateCards( List<Card> deck )
    {
        for(int i = 0; i < StdSettings.NUMBER_OF_REGULAR_CARDS_PER_SET; i++ )
        {
            for( int j = 1; j <= 9; j++ )
            {
                deck.add( new Card( StdSettings.CARD_COLORS.RED, j ) );
                deck.add( new Card( StdSettings.CARD_COLORS.YELLOW, j ) );
                deck.add( new Card( StdSettings.CARD_COLORS.GREEN, j ) );
                deck.add( new Card( StdSettings.CARD_COLORS.BLUE, j ) );
            }
        }

        for(int i = 0; i < StdSettings.NUMBER_OF_ZERO_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( StdSettings.CARD_COLORS.RED, 0 ) );
            deck.add( new Card( StdSettings.CARD_COLORS.YELLOW, 0 ) );
            deck.add( new Card( StdSettings.CARD_COLORS.GREEN, 0 ) );
            deck.add( new Card( StdSettings.CARD_COLORS.BLUE, 0 ) );
        }

        for(int i = 0; i < StdSettings.NUMBER_OF_SPECIAL_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( StdSettings.CARD_COLORS.RED, StdSettings.CARD_TYPES.SKIP ) );
            deck.add( new Card( StdSettings.CARD_COLORS.YELLOW, StdSettings.CARD_TYPES.SKIP ) );
            deck.add( new Card( StdSettings.CARD_COLORS.GREEN, StdSettings.CARD_TYPES.SKIP ) );
            deck.add( new Card( StdSettings.CARD_COLORS.BLUE, StdSettings.CARD_TYPES.SKIP ) );

            deck.add( new Card( StdSettings.CARD_COLORS.RED, StdSettings.CARD_TYPES.REVERSE ) );
            deck.add( new Card( StdSettings.CARD_COLORS.YELLOW, StdSettings.CARD_TYPES.REVERSE ) );
            deck.add( new Card( StdSettings.CARD_COLORS.GREEN, StdSettings.CARD_TYPES.REVERSE ) );
            deck.add( new Card( StdSettings.CARD_COLORS.BLUE, StdSettings.CARD_TYPES.REVERSE ) );

            deck.add( new Card( StdSettings.CARD_COLORS.RED, StdSettings.CARD_TYPES.DRAW_TWO ) );
            deck.add( new Card( StdSettings.CARD_COLORS.YELLOW, StdSettings.CARD_TYPES.DRAW_TWO ) );
            deck.add( new Card( StdSettings.CARD_COLORS.GREEN, StdSettings.CARD_TYPES.DRAW_TWO ) );
            deck.add( new Card( StdSettings.CARD_COLORS.BLUE, StdSettings.CARD_TYPES.DRAW_TWO ) );
        }

        for(int i = 0; i < StdSettings.NUMBER_OF_WILD_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( StdSettings.CARD_COLORS.NONE, StdSettings.CARD_TYPES.WILD) );
        }

        for(int i = 0; i < StdSettings.NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET; i++ )
        {
            deck.add( new Card( StdSettings.CARD_COLORS.NONE, StdSettings.CARD_TYPES.WILD_DRAW_FOUR ) );
        }
    }
}
