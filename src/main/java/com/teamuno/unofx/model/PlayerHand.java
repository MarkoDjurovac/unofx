package com.teamuno.unofx.model;

import java.util.ArrayList;

public class PlayerHand
{
    private ArrayList<Card> playerHand;

    public PlayerHand()
    {
        this.playerHand = new ArrayList<>();
    }

    private void addCard( Card card )
    {
        this.playerHand.add( card );
    }

    public int getNumberOfCards()
    {
        return this.playerHand.size();
    }

    public boolean isEmpty()
    {
        return this.playerHand.isEmpty();
    }
}