package com.teamuno.unofx.model;

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
