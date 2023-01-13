package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.CardConfiguration;

public class Card
{
    private CardConfiguration.COLORS color;

    private CardConfiguration.TYPES type;

    private int number;

    private String imageUrl;

    public Card( CardConfiguration.COLORS color, int number )
    {
        this.color = color;
        this.type = CardConfiguration.TYPES.NUMBER;
        this.number = number;
        this.imageUrl = generateUrl( color, type, number );
    }

    public Card( CardConfiguration.COLORS color, CardConfiguration.TYPES type, int number )
    {
        this.color = color;
        this.type = type;
        this.number = number;
        this.imageUrl = generateUrl( color, type, number );
    }

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

        if( type == CardConfiguration.TYPES.SKIP )
        {
            switch( color )
            {
                case RED -> url += "rs.gif";
                case YELLOW -> url += "ys.gif";
                case GREEN -> url += "gs.gif";
                case BLUE -> url += "bs.gif";
            }
        }

        if( type == CardConfiguration.TYPES.REVERSE )
        {
            switch( color )
            {
                case RED -> url += "rr.gif";
                case YELLOW -> url += "yr.gif";
                case GREEN -> url += "gr.gif";
                case BLUE -> url += "br.gif";
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

    public CardConfiguration.COLORS getColor()
    {
        return this.color;
    }

    public void setColor( CardConfiguration.COLORS color )
    {
        this.color = color;
    }

    public CardConfiguration.TYPES getType()
    {
        return this.type;
    }

    public void setType( CardConfiguration.TYPES type )
    {
        this.type = type;
    }

    public int getNumber()
    {
        return this.number;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public boolean isSpecial()
    {
        return switch( this.type )
        {
            case WILD, WILD_DRAW_FOUR, SKIP, REVERSE, DRAW_TWO -> true;
            default -> false;
        };
    }
}