package com.teamuno.unofx.factory;

import java.util.List;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.configuration.CustomGameConfiguration;
import com.teamuno.unofx.configuration.DefaultGameConfiguration;
import com.teamuno.unofx.model.Card;
import com.teamuno.unofx.utilities.CustomGameChecker;

/**
 * DeckFactory class is responsible for creating a deck of cards
 * @author Marc Göring
 */
public class DeckFactory
{
    /**
     * Creates a deck of cards depending on the game configuration
     * @param deck The deck to be filled with cards
     */
    public static void generateDeck( List<Card> deck )
    {
        int regularCardsPerSet = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.REGULAR_CARDS_PER_SET : DefaultGameConfiguration.REGULAR_CARDS_PER_SET;

        for( int i = 0; i < regularCardsPerSet; i++ )
        {
            for( int j = 0; j <= 9; j++ )
            {
                deck.add( new Card( CardConfiguration.COLORS.RED, j ) );
                deck.add( new Card( CardConfiguration.COLORS.YELLOW, j ) );
                deck.add( new Card( CardConfiguration.COLORS.GREEN, j ) );
                deck.add( new Card( CardConfiguration.COLORS.BLUE, j ) );
            }
        }

        int specialCardsPerSet = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.SPECIAL_CARDS_PER_SET : DefaultGameConfiguration.SPECIAL_CARDS_PER_SET;

        for(int i = 0; i < specialCardsPerSet; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.RED, CardConfiguration.TYPES.DRAW_TWO, 10 ) );
            deck.add( new Card( CardConfiguration.COLORS.YELLOW, CardConfiguration.TYPES.DRAW_TWO, 10 ) );
            deck.add( new Card( CardConfiguration.COLORS.GREEN, CardConfiguration.TYPES.DRAW_TWO, 10 ) );
            deck.add( new Card( CardConfiguration.COLORS.BLUE, CardConfiguration.TYPES.DRAW_TWO, 10 ) );
        }

        int wildCardsPerSet = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.WILD_CARDS_PER_SET : DefaultGameConfiguration.WILD_CARDS_PER_SET;

        for(int i = 0; i < wildCardsPerSet; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.BLACK, CardConfiguration.TYPES.WILD, 11 ) );
        }

        int wildDrawFourCardsPerSet = CustomGameChecker.isCustomGame() ? CustomGameConfiguration.WILD_DRAW_FOUR_CARDS_PER_SET : DefaultGameConfiguration.WILD_DRAW_FOUR_CARDS_PER_SET;

        for( int i = 0; i < wildDrawFourCardsPerSet; i++ )
        {
            deck.add( new Card( CardConfiguration.COLORS.BLACK, CardConfiguration.TYPES.WILD_DRAW_FOUR, 12 ) );
        }
    }
}