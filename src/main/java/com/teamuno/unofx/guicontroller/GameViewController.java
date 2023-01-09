package com.teamuno.unofx.guicontroller;

import com.teamuno.unofx.model.*;

import com.teamuno.unofx.utilities.SceneUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GameViewController
{
    private Game game;

    @FXML
    HBox botHand;

    @FXML
    HBox playerHand;

    @FXML
    ImageView discarded_pile;

    @FXML
    Text turnText;

    @FXML
    Button unoButton;

    @FXML
    ScrollPane scrollPane;

    @FXML
    public void initialize()
    {
        this.game = new Game( new Human( "Marko" ) );

        for( Player player : this.game.getPlayers() )
        {
            this.dealCards( player );
        }

        discarded_pile.setFitHeight( 79.0 );
        discarded_pile.setFitWidth( 83.5 );
        discarded_pile.setImage( new Image( game.getDeck().getTopCard().getUrlImage() ) );

        this.turnText.setText( game.getCurrentPlayer().getName() + "'s turn" );
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneUtils.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    protected void drawCard()
    {
        Card card = this.game.getCurrentPlayer().drawCard( this.game.getDeck() );

        if( this.game.getCurrentPlayer() instanceof Human )
        {
            this.drawImage( card );
        }
        else
        {
            this.drawImage( "/img/cards/back.gif" );
        }
    }

    @FXML
    protected void announceUno()
    {
        if( game.isUno( game.getCurrentPlayer() ) )
        {
            this.unoButton.setDisable( false );
        }
    }

    @FXML
    protected void nextTurn()
    {
        game.nextPlayer();
        this.turnText.setText( game.getCurrentPlayer().getName() + "'s turn" );
    }

    public void dealCards( Player player )
    {
        if( player instanceof Human )
        {
            for( Card card : this.game.getPlayers().get( 0 ).getHand() )
            {
                this.drawImage( card );
            }
        }
        else
        {
            for( Card card : this.game.getPlayers().get( 1 ).getHand() )
            {
                this.drawImage("/img/cards/back.gif" );
            }
        }
    }

    public void drawImage( Card card )
    {
        ImageView imgview = new ImageView( card.getUrlImage() );
        imgview.setFitHeight( 79.0 );
        imgview.setFitWidth( 83.5 );

        playerHand.getChildren().add( imgview );
        this.makeCardClickable( card, imgview );
    }

    public void drawImage( String url )
    {
        ImageView imgview = new ImageView( url );
        imgview.setFitHeight( 79.0 );
        imgview.setFitWidth( 83.5 );

        botHand.getChildren().add( imgview );
    }

    protected void hasPlayerWon()
    {
        if( game.hasPlayerWon( game.getCurrentPlayer() ) )
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setTitle( "Game Over" );
            alert.setHeaderText( "Player " + game.getCurrentPlayer().getName() + " has won!" );
            alert.setContentText( "Congratulations!" );
            alert.showAndWait();
        }
    }

    protected void makeCardClickable( Card card,  ImageView imgview )
    {
        imgview.setOnMouseClicked( new EventHandler<>() {
            @Override
            public void handle( MouseEvent event )
            {
                if ( game.isValidMove( card ) )
                {
                    game.getCurrentPlayer().playCard( game, card );
                    playerHand.getChildren().remove( imgview );
                    discarded_pile.setImage( imgview.getImage() );
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR );
                    alert.setTitle( "Invalid move!" );
                    alert.setHeaderText( "This card can not be played. Please select another card." );
                    alert.showAndWait();
                }
            }
        });
    }
}