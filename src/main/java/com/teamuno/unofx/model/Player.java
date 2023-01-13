package com.teamuno.unofx.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player
{
    private String name;

    private List<Card> hand;

    public Player()
    {
        this.name = "Player";
        this.hand = new ArrayList<>();
    }

    public Player( String name )
    {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public Card draw( Deck deck )
    {
        Card card = deck.draw();
        this.hand.add( card );

        return card;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Card> getHand()
    {
        return this.hand;
    }

    public boolean isBot()
    {
        return this instanceof Bot;
    }
}
