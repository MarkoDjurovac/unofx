package com.teamuno.unofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

import com.teamuno.unofx.configuration.CardConfiguration;
import com.teamuno.unofx.model.*;
import com.teamuno.unofx.utilities.GameLogic;
import com.teamuno.unofx.utilities.SceneManager;

/**
 * Controller for the game scene.
 * It handles the graphics and the logic of the game which is separated in the GameLogic class.
 * @author Marko Djurovac
 */
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

    /**
     * Initializes the game.
     */
    public void initialize( )
    {
        botScrollPane.vbarPolicyProperty().set( ScrollPane.ScrollBarPolicy.NEVER );
        playerScrollPane.vbarPolicyProperty().set( ScrollPane.ScrollBarPolicy.NEVER );

        if( this.game == null )
        {
            this.game = new GameState();
        }

        for( Player player : this.game.getPlayerList() )
        {
            this.dealCardsUi( player );
        }

        this.drawDiscardPile();
    }

    /**
     * Quits the game and returns to the main menu.
     * @param event The event that triggered the method.
     */
    @FXML
    protected void quitGame( ActionEvent event ) throws IOException
    {
        SceneManager.changeScene( event, getClass().getResource( "/view/main-menu.fxml" ) );
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     */
    @FXML
    protected void drawCardUi()
    {
        Card card = this.game.getCurrentPlayer().draw( this.game.getDeck() );

        this.renderImage( card );

        this.endTurn( this.game );
    }

    /**
     * Deals the cards to the players
     * @param player The player to deal the cards to.
     */
    protected void dealCardsUi( Player player )
    {
        for( Card card : player.getHand() )
        {
            if( player.isBot() )
            {
                this.renderImage();
            }
            else
            {
                this.renderImage( card );
            }
        }
    }

    /**
     * Draws the image on the discard pile.
     */
    protected void drawDiscardPile()
    {
        this.discarded_pile.setFitHeight( 113.0 );
        this.discarded_pile.setFitWidth( 79.0 );
        discarded_pile.setImage( new Image( game.getDeck().getTopCard().getImageUrl() ) );
    }

    /**
     * Renders the back image for the bots hand.
     */
    protected void renderImage()
    {
        ImageView imageView = new ImageView( getClass().getResource( "/img/cards/back.gif" ).toString() );
        imageView.setFitHeight( 113.0 );
        imageView.setFitWidth( 79.0 );
        botHand.getChildren().add( imageView );
    }

    /**
     * Renders the image of the card to the player's hand.
     * @param card The card to render.
     */
    protected void renderImage( Card card )
    {
        ImageView imageView = new ImageView( card.getImageUrl() );
        imageView.setFitHeight( 113.0 );
        imageView.setFitWidth( 79.5 );
        playerHand.getChildren().add( imageView );
        this.setMouseClickEvent( imageView, card );
    }

    /**
     * Sets the mouse click event for the human player cards.
     * @param imageView The image view of the card.
     * @param card The card to set the event for.
     */
    protected void setMouseClickEvent( ImageView imageView, Card card )
    {
        imageView.setOnMouseClicked( event ->
        {
            if( !game.getCurrentPlayer().isBot() )
            {
                if( GameLogic.isValidMove( game.getDeck().getTopCard(), card ) )
                {
                    ( ( Human ) game.getCurrentPlayer() ).playCard( this.game, card );

                    if( card.isSpecial() )
                    {
                        this.specialCardUi( card );
                    }

                    this.playCardUi( imageView );

                    if( GameLogic.checkForUno( this.game.getCurrentPlayer() ) )
                    {
                        this.showUnoAlert( this.game );
                        this.endTurn( this.game );
                    }
                    else if( GameLogic.checkForWin( this.game.getCurrentPlayer() ) )
                    {
                        this.showWinnerAlert( this.game );
                    }
                    else
                    {
                        this.endTurn( this.game );
                    }
                }
                else
                {
                    this.showInvalidMoveAlert();
                }
            }
        });
    }

    /**
     * Plays the card on the discard pile.
     * @param imageView The image view of the card.
     */
    protected void playCardUi( ImageView imageView )
    {
        this.playerHand.getChildren().remove( imageView );
        this.discarded_pile.setImage( imageView.getImage() );
    }

    /**
     * Method for handling the bots turn.
     *
     * @param game The current game.
     */
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

            if( GameLogic.checkForUno( this.game.getCurrentPlayer() ) )
            {
                this.showUnoAlert( this.game );
                this.endTurn( this.game );
            }
            else if( GameLogic.checkForWin( this.game.getCurrentPlayer() ) )
            {
                this.showWinnerAlert( this.game );
            }
            else
            {
                this.endTurn( this.game );
            }
        }
    }

    /**
     * Updates the bots hand after a card is played.
     * @param card the card to update the hand with.
     */
    protected void botPlayCardUi( Card card )
    {
        this.botHand.getChildren().remove( 0 );
        this.discarded_pile.setImage( new Image( card.getImageUrl() ) );
    }

    /**
     * Method for ending the current player's turn and starting the next player's turn.
     * @param game The current game.
     */
    public void endTurn( GameState game )
    {
        int index = game.getPlayerList().indexOf( game.getCurrentPlayer() );
        int nextPlayerIndex = ( index + 1 ) % game.getPlayerList().size();

        game.setCurrentPlayer( game.getPlayerList().get( nextPlayerIndex ) );

        if( game.getCurrentPlayer().isBot() )
        {
            this.botTurn( game );
        }
    }

    /**
     * Method for handling the special cards.
     * If the card is a draw two or wild draw four, the players hand is updated accordingly.
     * If the card is a wild or wild draw four, the color picker alert is shown.
     * @param card The card that has been played
     */
    protected void specialCardUi( Card card )
    {
        switch( card.getType() )
        {
            case DRAW_TWO -> this.updateHand( this.game.getNextPlayer() );
            case WILD ->
            {
                this.choseColor();
            }
            case WILD_DRAW_FOUR ->
            {
                this.choseColor();
                this.updateHand( this.game.getNextPlayer() );
            }
            default -> {
            }
        }
    }

    /**
     * Method for updating the hand of the player.
     * @param player The player to update the hand for.
     */
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

    /**
     * Method for handling the color choosing.
     */
    protected void choseColor()
    {
        if( this.game.getCurrentPlayer().isBot() )
        {
            this.game.getDeck().getTopCard().setColor( ( ( Bot ) this.game.getCurrentPlayer() ).pickColor() );
            this.game.getDeck().getTopCard().setType( CardConfiguration.TYPES.NUMBER );
            this.showWildCardBotAlert( this.game.getCurrentPlayer() );
        }
        else
        {
           this.showWildCardAlert();
        }
    }

    /**
     * Method for ending the game and returning to the main menu.
     */
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

    /**
     * Method for displaying an alert if the card is not allowed to be played.
     */
    protected void showInvalidMoveAlert()
    {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( getClass().getResource( "/img/icon_512.png" ).toString() ) );
        alert.setTitle( "UnoFX" );
        alert.setHeaderText( "This card cannot be played! Please select another card." );
        alert.showAndWait();
    }

    /**
     * Method for displaying an alert if one player has uno.
     * @param game The current game.
     */
    public void showUnoAlert( GameState game )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( getClass().getResource( "/img/icon_512.png" ).toString() ) );
        alert.setTitle( "UnoFX" );
        alert.setHeaderText( game.getCurrentPlayer().getName() + " has UNO!" );
        alert.showAndWait();
    }

    /**
     * Method for displaying an alert one player has won the game.
     * @param game The current game.
     */
    public void showWinnerAlert( GameState game )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( getClass().getResource( "/img/icon_512.png" ).toString() ) );
        alert.setTitle( "UnoFX" );
        alert.setHeaderText( game.getCurrentPlayer().getName() + " has won!" );
        alert.showAndWait();

        this.endGame();
    }

    /**
     * Method for displaying an alert if the player has played a wild card.
     */
    protected void showWildCardAlert()
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( getClass().getResource( "/img/icon_512.png" ).toString() ) );
        alert.setTitle( "Color Picker" );
        alert.setHeaderText( "Please choose a color!" );

        ButtonType red = new ButtonType( "RED" );
        ButtonType green = new ButtonType( "GREEN" );
        ButtonType blue = new ButtonType( "BLUE" );
        ButtonType yellow = new ButtonType( "YELLOW" );

        alert.getButtonTypes().setAll( red, green, blue, yellow );

        Optional<ButtonType> result = alert.showAndWait();

        if( result.get() == red )
        {
            this.game.getDeck().getTopCard().setColor( CardConfiguration.COLORS.RED );
        }
        else if( result.get() == green )
        {
            this.game.getDeck().getTopCard().setColor( CardConfiguration.COLORS.GREEN );
        }
        else if( result.get() == blue )
        {
            this.game.getDeck().getTopCard().setColor( CardConfiguration.COLORS.BLUE );
        }
        else if( result.get() == yellow )
        {
            this.game.getDeck().getTopCard().setColor( CardConfiguration.COLORS.YELLOW );
        }

        this.game.getDeck().getTopCard().setType( CardConfiguration.TYPES.NUMBER );
    }

    /**
     * Method for displaying an alert if the bot has played a wild card.
     * @param player The player that has played the card ( bot )
     */
    protected void showWildCardBotAlert( Player player )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        Stage stage = ( Stage ) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( getClass().getResource( "/img/icon_512.png" ).toString() ) );
        alert.setTitle( "Wild Card" );
        alert.setHeaderText( player.getName() + " has chosen " + this.game.getDeck().getTopCard().getColor().toString()  + " as the new color." );
        alert.showAndWait();
    }
}