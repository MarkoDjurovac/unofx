package com.teamuno.unofx.configuration;

public class StdSettings
{
    public enum CARD_COLORS { RED, YELLOW, GREEN, BLUE, NONE };

    public enum CARD_TYPES { NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR };

    public static final int NUMBER_OF_REGULAR_CARDS_PER_SET = 2;

    public static final int NUMBER_OF_WILD_CARDS_PER_SET = 4;

    public static final int NUMBER_OF_SPECIAL_CARDS_PER_SET = 2;

    public static final int NUMBER_OF_ZERO_CARDS_PER_SET = 1;

    public static final int NUMBER_OF_WILD_DRAW_FOUR_CARDS_PER_SET = 4;

    public static final int NUMBER_OF_CARDS_PER_PLAYER = 7;
}
