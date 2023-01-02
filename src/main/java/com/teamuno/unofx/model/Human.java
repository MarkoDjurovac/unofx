package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.BaseConfiguration;

public class Human extends Player
{
    public Human()
    {
        super();
    }

    public Human( String name )
    {
        super( name );
    }

    public void playCard( Game game , Card card )
    {
        if( game.isValidMove( card ) )
        {
            this.getHand().remove( card );
        }
    }
}
