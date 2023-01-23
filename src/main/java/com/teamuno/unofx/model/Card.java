package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.CardConfiguration;

/**
 * Represents a uno card.
 * @author Marc Göring
 */
public class Card
{
    private CardConfiguration.COLORS color;

    private CardConfiguration.TYPES type;

    private int number;

    private String imageUrl;

    /**
     * Constructor for a new card.
     * @param color The color of the card.
     * @param number The type of the card.
     */
    public Card( CardConfiguration.COLORS color, int number )
    {
        this.color = color;
        this.type = CardConfiguration.TYPES.NUMBER;
        this.number = number;
        this.imageUrl = generateUrl( color, type, number );
    }

    /**
     * Constructor for a new card.
     * @param color The color of the card.
     * @param number The type of the card.
     * @param type The type of the card.
     */
    public Card( CardConfiguration.COLORS color, CardConfiguration.TYPES type, int number )
    {
        this.color = color;
        this.type = type;
        this.number = number;
        this.imageUrl = generateUrl( color, type, number );
    }

    /**
     * Generates the url for the image of the card.
     * @param color The color of the card.
     * @param number The type of the card.
     * @return The url of the image.
     */
    public String generateUrl( CardConfiguration.COLORS color, CardConfiguration.TYPES type, int number )
    {
        String url = "/img/cards/";

        if( type == CardConfiguration.TYPES.NUMBER )
        {
            switch( color )
            {
                case RED -> url += "r" + number + ".gif";
                case YELLOW -> url += "y" + number + ".gif";
                case GREEN -> url += "g" + number + ".gif";
                case BLUE -> url += "b" + number + ".gif";
            }
        }

        if( type == CardConfiguration.TYPES.DRAW_TWO )
        {
            switch( color )
            {
                case RED -> url += "rd.gif";
                case YELLOW -> url += "yd.gif";
                case GREEN -> url += "gd.gif";
                case BLUE -> url += "bd.gif";
            }
        }

        if( type == CardConfiguration.TYPES.WILD )
        {
            url += "wp.gif";
        }

        else if( type == CardConfiguration.TYPES.WILD_DRAW_FOUR )
        {
            url += "wd.gif";
        }

        return url;
    }

    /**
     * Getter for the color of the card.
     * @return The color of the card.
     */
    public CardConfiguration.COLORS getColor()
    {
        return this.color;
    }

    /**
     * Setter for the color of the card.
     */
    public void setColor( CardConfiguration.COLORS color )
    {
        this.color = color;
    }

    /**
     * Getter for the type of the card.
     * @return The type of the card.
     */
    public CardConfiguration.TYPES getType()
    {
        return this.type;
    }

    /**
     * Setter for the type of the card.
     */
    public void setType( CardConfiguration.TYPES type )
    {
        this.type = type;
    }

    /**
     * Getter for the number of the card.
     * @return The number of the card.
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * Getter for the image url of the card.
     * @return The image url of the card.
     */
    public String getImageUrl()
    {
        return this.imageUrl;
    }

    /**
     * Checks if the card is a special card
     * @return true if the card is a special card, false if not.
     */
    public boolean isSpecial()
    {
        return switch( this.type )
        {
            case WILD, WILD_DRAW_FOUR, DRAW_TWO -> true;
            default -> false;
        };
    }
}