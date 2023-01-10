package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.StdSettings;

public class Card
{
    private StdSettings.CARD_COLORS color;
    private StdSettings.CARD_TYPES type;
    private int cardNumber;
    private String urlImage;

    public Card( StdSettings.CARD_COLORS color, StdSettings.CARD_TYPES type )
    {
        this.color = color;
        this.type = type;
        this.cardNumber = -1;
        this.urlImage = generateUrl( cardNumber, color, type );
    }

    public Card( StdSettings.CARD_COLORS color, int cardNumber )
    {
        this.color = color;
        this.type = StdSettings.CARD_TYPES.NUMBER;
        this.cardNumber = cardNumber;
        this.urlImage = generateUrl( cardNumber, color, type );
    }

    public Card( StdSettings.CARD_COLORS color, StdSettings.CARD_TYPES type, int cardNumber )
    {
        this.color = color;
        this.type = type;
        this.cardNumber = cardNumber;
        this.urlImage = generateUrl( cardNumber, color, type );
    }

    public String generateUrl( int cardNumber, StdSettings.CARD_COLORS color, StdSettings.CARD_TYPES type )
    {
        String url = "/img/cards/";

        if( type == StdSettings.CARD_TYPES.NUMBER )
        {
            switch( color )
            {
                case RED:
                    url += "r" + cardNumber + ".gif";
                    break;
                case YELLOW:
                    url += "y" + cardNumber + ".gif";
                    break;
                case GREEN:
                    url += "g" + cardNumber + ".gif";
                    break;
                case BLUE:
                    url += "b" + cardNumber + ".gif";
                    break;
            }
        }

        if( type == StdSettings.CARD_TYPES.SKIP )
        {
            switch( color )
            {
                case RED:
                    url += "rs.gif";
                    break;
                case YELLOW:
                    url += "ys.gif";
                    break;
                case GREEN:
                    url += "gs.gif";
                    break;
                case BLUE:
                    url += "bs.gif";
                    break;
            }
        }

        if( type == StdSettings.CARD_TYPES.REVERSE )
        {
            switch( color )
            {
                case RED:
                    url += "rr.gif";
                    break;
                case YELLOW:
                    url += "yr.gif";
                    break;
                case GREEN:
                    url += "gr.gif";
                    break;
                case BLUE:
                    url += "br.gif";
                    break;
            }
        }

        if( type == StdSettings.CARD_TYPES.DRAW_TWO )
        {
            switch( color )
            {
                case RED:
                    url += "rd.gif";
                    break;
                case YELLOW:
                    url += "yd.gif";
                    break;
                case GREEN:
                    url += "gd.gif";
                    break;
                case BLUE:
                    url += "bd.gif";
                    break;
            }
        }

        if( type == StdSettings.CARD_TYPES.WILD )
        {
            url += "wp.gif";
        }
        else if( type == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
        {
            url += "wd.gif";
        }

        return url;
    }

    public StdSettings.CARD_COLORS getColor()
    {
        return this.color;
    }

    public StdSettings.CARD_TYPES getType()
    {
        return this.type;
    }

    public int getNumber()
    {
        return this.cardNumber;
    }

    public String getUrlImage()
    {
        return this.urlImage;
    }
}
