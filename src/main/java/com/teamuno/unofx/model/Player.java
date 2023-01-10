package com.teamuno.unofx.model;

import com.teamuno.unofx.guicontroller.GameController;

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

    public Card drawCard( Deck deck )
    {
        Card card = deck.drawCard();
        this.hand.add( card );

        return card;
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

    public abstract void playCard(GameController gc, Card card );
}
