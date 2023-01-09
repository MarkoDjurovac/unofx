package com.teamuno.unofx.model;

import com.teamuno.unofx.configuration.StdSettings;
import com.teamuno.unofx.guicontroller.GameViewController;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    Deck deck;
    List<Player> players;
    Player currentPlayer;

    public Game( Player player )
    {
        this.players = new ArrayList<>();
        this.players.add( player );
        this.players.add( new Bot() );
        this.deck = new Deck();
        this.currentPlayer = players.get( 0 );
        this.deck.shuffle();
        this.dealCards();
        this.deck.discardCard( this.deck.drawCard() );
    }


    public void dealCards()
    {
        for( int i = 0; i < StdSettings.NUMBER_OF_CARDS_PER_PLAYER; i++ )
        {
            for( Player player : this.players )
            {
                player.drawCard( this.deck );
            }
        }
    }

    public boolean isValidMove( Card card )
    {
        if( card.getType() == StdSettings.CARD_TYPES.WILD || card.getType() == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
        {
            return true;
        }

        if( this.deck.getTopCard().getType() == StdSettings.CARD_TYPES.SKIP || this.deck.getTopCard().getType() == StdSettings.CARD_TYPES.REVERSE || this.deck.getTopCard().getType() == StdSettings.CARD_TYPES.DRAW_TWO )
        {
            if( this.deck.getTopCard().getColor() == card.getColor() )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        if( this.getTopCard().getType() == StdSettings.CARD_TYPES.WILD || this.getTopCard().getType() == StdSettings.CARD_TYPES.WILD_DRAW_FOUR )
        {
            return true;
        }

        if( card.getColor() == this.getTopCard().getColor()  )
        {
            return true;
        }

        if( card.getNumber() == this.getTopCard().getNumber() )
        {
            return true;
        }


        return false;
    }

    public void drawTwo()
    {
        this.currentPlayer.drawCard( this.deck );
        this.currentPlayer.drawCard( this.deck );

        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                // TODO: Update the GUI
            }
        });
    }

    public void drawFour()
    {
        this.drawTwo();
        this.drawTwo();
    }

    public void skip()
    {
        this.nextPlayer();
    }

    public void reverse()
    {
        List<Player> reversedPlayers = new ArrayList<>();
        for( int i = this.players.size() - 1; i >= 0; i-- )
        {
            reversedPlayers.add( this.players.get( i ) );
        }
        this.players = reversedPlayers;
    }

    public void wildColor()
    {

    }

    public void nextPlayer()
    {
        int index = this.players.indexOf( this.currentPlayer );

        index++;

        if( index >= this.players.size() )
        {
            index = 0;
        }

        this.currentPlayer = this.players.get( index );
    }

    public boolean hasPlayerWon( Player currentPlayer )
    {
        if( currentPlayer.getHand().size() == 0 )
        {
            return true;
        }

        return false;
    }

    public boolean isUno( Player currentPlayer )
    {
        if( currentPlayer.getHand().size() == 1 )
        {
            return true;
        }

        return false;
    }

    public Card getTopCard()
    {
        return this.deck.getTopCard();
    }

    public Deck getDeck()
    {
        return this.deck;
    }

    public List<Player> getPlayers()
    {
        return this.players;
    }

    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }
}
