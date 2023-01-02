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
    }

    public void drawCard( Deck deck )
    {
        this.hand.add( deck.drawCard() );
    }

    public List<Card> getHand()
    {
        return this.hand;
    }

    public abstract void playCard( Game game, Card card);
}
