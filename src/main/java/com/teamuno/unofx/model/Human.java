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

    public void playCard( Game game, Card card )
    {
        if( game.isValidMove( card ) )
        {
            switch( card.getType() )
            {
                case WILD:
                    System.out.println("Wild card played!");
                    game.wildColor();
                    break;
                case WILD_DRAW_FOUR:
                    System.out.println("Draw 4 card played!");
                    game.drawFour();
                case DRAW_TWO:
                    System.out.println("Draw 2 card played!");
                    game.drawTwo();
                    break;
                case SKIP:
                    System.out.println("Skip card played!");
                    game.skip();
                    break;
                case REVERSE:
                    System.out.println("Reverse card played!");
                    game.reverse();
                    break;
                default:
                    break;
            }

            game.getDeck().discardCard( card );
            this.getHand().remove( card );
        }
    }
}
