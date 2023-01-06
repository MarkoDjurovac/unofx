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

    public void drawCard( Deck deck )
    {
        this.hand.add( deck.drawCard() );
    }

    public List<Card> getHand()
    {
        return this.hand;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName( String value )
    {
        if( value != this.name )
        {
            this.name = value;
        }
    }

    public abstract void playCard( Game game, Card card);
}
