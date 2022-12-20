package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.BaseConfiguration;

public class Card
{
    private BaseConfiguration.CARD_COLORS color;
    private BaseConfiguration.CARD_TYPES type;
    private int cardNumber;

    public Card( BaseConfiguration.CARD_COLORS color, BaseConfiguration.CARD_TYPES type )
    {
        this.color = color;
        this.type = type;
        this.cardNumber = -1;
    }

    public Card( BaseConfiguration.CARD_COLORS color, int cardNumber )
    {
        this.color = color;
        this.type = BaseConfiguration.CARD_TYPES.NUMBER;
        this.cardNumber = cardNumber;
    }

    public BaseConfiguration.CARD_COLORS getColor()
    {
        return this.color;
    }

    public BaseConfiguration.CARD_TYPES getType()
    {
        return this.type;
    }

    public int getNumber()
    {
        return this.cardNumber;
    }
}
