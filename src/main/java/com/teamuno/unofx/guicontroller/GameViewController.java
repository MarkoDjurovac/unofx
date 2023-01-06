package com.teamuno.unofx.guicontroller;


import com.teamuno.unofx.model.*;

import com.teamuno.unofx.utilities.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

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
    public void initialize()
    {
        this.game = new Game( new Human( "Marko" ) );

        for( Card card : game.getPlayers().get( 0 ).getHand() )
        {
            Image img = new Image( "/img/cards/back.gif" );
            ImageView imgview = new ImageView( card.getUrlImage() );
            imgview.setFitHeight( 79.0 );
            imgview.setFitWidth( 83.5 );
            playerHand.getChildren().add( imgview );
        }

        for( Card card : game.getPlayers().get( 1 ).getHand() )
        {
            Image img = new Image( "/img/cards/back.gif" );
            ImageView imgview = new ImageView( img );
            imgview.setFitHeight( 79.0 );
            imgview.setFitWidth( 83.5 );
            botHand.getChildren().add( imgview );
        }

        discarded_pile.setFitHeight( 79.0 );
        discarded_pile.setFitWidth( 83.5 );
        discarded_pile.setImage( new Image( game.getDeck().getTopCard().getUrlImage() ) );
    }

    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneUtils.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    @FXML
    protected void drawCard()
    {
        game.getCurrentPlayer().drawCard( game.getDeck() );

        if( game.getCurrentPlayer() instanceof Human )
        {
            System.out.println( game.getCurrentPlayer().getHand().get( game.getCurrentPlayer().getHand().size() - 1 ).getUrlImage() );
            Image img = new Image( game.getCurrentPlayer().getHand().get( game.getCurrentPlayer().getHand().size() - 1 ).getUrlImage() );
            ImageView imgview = new ImageView( img );
            imgview.setFitHeight( 79.0 );
            imgview.setFitWidth( 83.5 );
            playerHand.getChildren().add( imgview );
        }

        if( game.getCurrentPlayer() instanceof Bot )
        {
            Image img = new Image( game.getCurrentPlayer().getHand().get( game.getCurrentPlayer().getHand().size() - 1 ).getUrlImage() );
            ImageView imgview = new ImageView( "/img/cards/back.gif" );
            imgview.setFitHeight( 79.0 );
            imgview.setFitWidth( 83.5 );
            botHand.getChildren().add( imgview );
        }
    }
}