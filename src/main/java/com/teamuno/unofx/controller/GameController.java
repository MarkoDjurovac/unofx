package com.teamuno.unofx.controller;

import com.teamuno.unofx.model.*;
import com.teamuno.unofx.utilities.GameLogic;
import com.teamuno.unofx.utilities.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController
{
    GameState game;

    @FXML
    HBox botHand;

    @FXML
    HBox playerHand;

    @FXML
    ImageView discarded_pile;

    @FXML
    ScrollPane botScrollPane;

    @FXML
    ScrollPane playerScrollPane;

    @FXML
    public void initialize()
    {
        botScrollPane.vbarPolicyProperty().set( ScrollPane.ScrollBarPolicy.NEVER );
        playerScrollPane.vbarPolicyProperty().set( ScrollPane.ScrollBarPolicy.NEVER );

        this.game = new GameState( new Human( "Olaf" ) );

        for( Player player : this.game.getPlayerList() )
        {
            this.dealCardsUi( player );
        }

        this.drawDiscardPile();
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    protected void drawCardUi()
    {
        Card card = this.game.getCurrentPlayer().draw( this.game.getDeck() );

        this.renderImage( card );

        GameController.endTurn( this, this.game );
    }

    protected void dealCardsUi( Player player )
    {
        for( Card card : player.getHand() )
        {
            if( player.isBot() )
            {
                this.renderImage( );
            }
            else
            {
                this.renderImage( card );
            }
        }
    }

    protected void drawDiscardPile()
    {
        this.discarded_pile.setFitHeight( 113.0 );
        this.discarded_pile.setFitWidth( 79.0 );
        discarded_pile.setImage( new Image( game.getDeck().getTopCard().getImageUrl() ) );
    }

    protected void renderImage()
    {
        ImageView imageView = new ImageView( "/img/cards/back.gif" );
        imageView.setFitHeight( 113.0 );
        imageView.setFitWidth( 79.0 );
        botHand.getChildren().add( imageView );
    }

    protected void renderImage( Card card )
    {
        ImageView imageView = new ImageView( card.getImageUrl() );
        imageView.setFitHeight( 113.0 );
        imageView.setFitWidth( 79.5 );
        playerHand.getChildren().add( imageView );
        this.setMouseClickEvent( imageView, card );
    }

    protected void setMouseClickEvent( ImageView imageView, Card card )
    {
        imageView.setOnMouseClicked( event ->
        {
            if( !game.getCurrentPlayer().isBot() )
            {
                if( GameLogic.isValidMove( game.getDeck().getTopCard(), card ) )
                {
                    GameLogic.playCard( ( Human ) this.game.getCurrentPlayer(), game, card );

                    if( card.isSpecial() )
                    {
                        this.specialCardUi( card );
                    }

                    this.playCardUi( imageView );

                    if( GameLogic.checkForUno( this.game.getPlayerList().get( 0 ) ) )
                    {
                        this.showUnoAlert( this.game );
                    }

                    if( GameLogic.checkForWin( this.game.getPlayerList().get( 0 ) ) )
                    {
                        this.showWinnerAlert( this.game );
                    }

                    GameController.endTurn( this, this.game );

                    if( game.getCurrentPlayer().isBot() )
                    {
                        this.botTurn( this.game );
                    }
                }
                else
                {
                    this.showInvalidMoveAlert();
                }
            }
        });
    }

    protected void playCardUi( ImageView imageView )
    {
        this.playerHand.getChildren().remove( imageView );
        this.discarded_pile.setImage( imageView.getImage() );
    }

    protected void botTurn( GameState game )
    {
        if( game.getCurrentPlayer().isBot() )
        {
            this.updateHand( game.getCurrentPlayer() );
            Card card = ( ( Bot ) this.game.getCurrentPlayer() ).playCard( this.game );

            if( card == null )
            {
                this.renderImage();
            }
            else
            {
                this.botPlayCardUi( card );

                if( card.isSpecial() )
                {
                    this.specialCardUi( card );
                }
            }


            if( GameLogic.checkForUno( this.game.getPlayerList().get( 1 ) ) )
            {
                this.showUnoAlert( this.game );
            }

            if( GameLogic.checkForWin( this.game.getPlayerList().get( 1 ) ) )
            {
                this.showWinnerAlert( this.game );
            }
        }

        GameController.endTurn( this, this.game );
    }

    protected void botPlayCardUi( Card card )
    {
        this.botHand.getChildren().remove( 0 );
        this.discarded_pile.setImage( new Image( card.getImageUrl() ) );
    }

    protected void showInvalidMoveAlert()
    {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( "/img/icon_512.png" ) );
        alert.setTitle( "Invalid move!" );
        alert.setHeaderText( "This card can not be played. Please select another card." );
        alert.showAndWait();
    }

    public void showUnoAlert( GameState game )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( "/img/icon_512.png" ) );
        alert.setTitle( "UNO!" );
        alert.setHeaderText( "UNO!" );
        alert.setContentText( game.getCurrentPlayer().getName() + " has UNO!" );
        alert.showAndWait();
    }

    public void showWinnerAlert( GameState game )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( "/img/icon_512.png" ) );
        alert.setTitle( "Winner!" );
        alert.setHeaderText( "Winner!" );
        alert.setContentText( game.getCurrentPlayer().getName() + " has won!" );
        alert.showAndWait();

        this.endGame();
    }

    public static void endTurn( GameController gc, GameState game )
    {
        int index = game.getPlayerList().indexOf( game.getCurrentPlayer() );
        int nextPlayerIndex = ( index + 1 ) % game.getPlayerList().size();

        game.setCurrentPlayer( game.getPlayerList().get( nextPlayerIndex ) );

        if( game.getCurrentPlayer().isBot() )
        {
            gc.botTurn( game);
        }
    }

    protected void endGame()
    {
        try
        {
            SceneManager.endGame( getClass().getResource( "/view/main-menu.fxml" ), ( Stage ) this.playerHand.getScene().getWindow() );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    protected void specialCardUi( Card card )
    {
        switch( card.getType() )
        {
            case DRAW_TWO, WILD_DRAW_FOUR -> this.updateHand(this.game.getNextPlayer());
            default -> {
            }
        }
    }

    protected void updateHand( Player player )
    {
        if( player.isBot() )
        {
            this.botHand.getChildren().clear();
        }
        else
        {
            this.playerHand.getChildren().clear();
        }

        this.dealCardsUi( player );
    }
}