package com.teamuno.unofx.model;

import java.util.Random;

public class Bot extends Player
{
    public Bot()
    {
        super( "Robot");
    }

    public void playCard( Game game , Card card )
    {
        while( !game.isValidMove( card ) )
        {
            card = this.getHand().get( (int)( new Random().nextInt() * this.getHand().size() ) );
        }

        this.getHand().remove( card );
    }
}
