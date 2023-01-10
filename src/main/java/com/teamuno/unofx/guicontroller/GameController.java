package com.teamuno.unofx.guicontroller;

import com.teamuno.unofx.configuration.StdSettings;
import com.teamuno.unofx.model.Bot;
import com.teamuno.unofx.model.Card;
import com.teamuno.unofx.model.Deck;
import com.teamuno.unofx.model.Human;
import com.teamuno.unofx.model.Player;
import com.teamuno.unofx.utilities.SceneManager;
import com.teamuno.unofx.utilities.UnoHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController
{
    @FXML
    HBox botHand;
    @FXML
    HBox playerHand;
    @FXML
    ImageView discarded_pile;

    List<Player> players;
    Player currentPlayer;
    Deck deck;

    @FXML
    public void initialize()
    {
        this.players = new ArrayList<>();
        this.players.add( new Human( "Olaf" ) );
        this.players.add( new Bot() );
        this.currentPlayer = this.players.get( 0 );

        this.deck = new Deck();
        this.dealCards( deck, players );
        this.deck.getDiscarded().add( this.deck.drawCard() );

        discarded_pile.setFitHeight( 79.0 );
        discarded_pile.setFitWidth( 83.5 );
        discarded_pile.setImage( new Image( this.deck.getTopCard().getUrlImage() ) );
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    public void drawCard()
    {
        this.drawCardFx( this.currentPlayer );
        this.nextTurn();
    }

    public void dealCards( Deck deck, List<Player> players )
    {
        for( Player player : players )
        {
            for( int i = 0; i < StdSettings.NUMBER_OF_CARDS_PER_PLAYER; i++ )
            {
                this.drawCardFx( player );
            }
        }
    }

    public void nextTurn()
    {
        int index = this.players.indexOf( this.currentPlayer );

        index++;

        if( index >= this.players.size() )
        {
            index = 0;
        }

        this.currentPlayer = this.players.get( index );

        if( this.currentPlayer instanceof Bot )
        {
            this.botMove();
        }
    }

    public void botMove()
    {
        this.currentPlayer.playCard( this, null );
        this.nextTurn();
    }

    public void wildCard()
    {
        this.nextTurn();
    }

    public void wildDrawFour()
    {
        int index = this.players.indexOf( this.currentPlayer );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        this.nextTurn();
    }

    public void drawTwo()
    {
        int index = this.players.indexOf( this.currentPlayer );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        drawCardFx( this.players.get( ( index + 1 ) % 2 ) );
        this.nextTurn();
    }

    public void skip()
    {
        this.nextTurn();
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


    @FXML
    public void drawCardFx( Player player )
    {
        Card card = deck.drawCard();
        player.getHand().add( card );

        if( player instanceof Human )
        {
           this.drawImageToHand( card );
        }
        else
        {
            this.drawImageToHand( "/img/cards/back.gif" );
        }
    }

    public void playCardFx( Player player, ImageView imageview )
    {
        if( player instanceof Human )
        {
            playerHand.getChildren().remove( imageview );
            discarded_pile.setImage( imageview.getImage() );
        }
    }

    public void botPlayCardFx( Card card )
    {
        botHand.getChildren().remove( 0 );
        discarded_pile.setImage( new Image( card.getUrlImage() ) );
    }


    public void drawImageToHand( Card card )
    {
        ImageView imgview = new ImageView( card.getUrlImage() );
        imgview.setFitHeight( 79.0 );
        imgview.setFitWidth( 83.5 );

        playerHand.getChildren().add( imgview );
        this.makeCardClickable( card, imgview );
    }

    public void drawImageToHand( String url )
    {
        ImageView imgview = new ImageView( url );
        imgview.setFitHeight( 79.0 );
        imgview.setFitWidth( 83.5 );

        botHand.getChildren().add( imgview );
    }

    public void checkForUno()
    {
        System.out.println( this.currentPlayer.getName() + " has " + this.currentPlayer.getHand().size() + " cards." );
        if( this.currentPlayer.getHand().size() == 1 )
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setTitle( "UNO!" );
            alert.setHeaderText( "UNO!" );
            alert.setContentText( this.currentPlayer.getName() + " has UNO!" );
            alert.showAndWait();
        }
    }

    public void checkForWin()
    {
        System.out.println( this.currentPlayer.getName() + " has " + this.currentPlayer.getHand().size() + " cards." );

        if( this.currentPlayer.getHand().size() == 0 )
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setTitle( "Winner!" );
            alert.setHeaderText( "Winner!" );
            alert.setContentText( this.currentPlayer.getName() + " has won!" );
            alert.showAndWait();

            try
            {
                SceneManager.changeScene( new ActionEvent(), getClass().getResource( "/view/main-menu.fxml" ) );
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    public void makeCardClickable( Card card, ImageView imageview )
    {
        imageview.setOnMouseClicked( new EventHandler<>() {
            @Override
            public void handle( MouseEvent event )
            {
                if( UnoHelper.isValidMove( deck.getTopCard(), card ) )
                {
                    currentPlayer.playCard( GameController.this, card );
                    playCardFx( currentPlayer, imageview );
                    checkForUno();
                    checkForWin();
                    nextTurn();
                }
                else
                {
                    Alert alert = new Alert( Alert.AlertType.ERROR );
                    alert.setTitle( "Invalid move!" );
                    alert.setHeaderText( "This card can not be played. Please select another card." );
                    alert.showAndWait();
                }
            }
        });
    }

    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public Deck getDeck()
    {
        return this.deck;
    }
}