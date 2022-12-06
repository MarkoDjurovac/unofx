package com.teamuno.unofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JavaFxTestController
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText( "This is a test for JavaFX!" );
    }
}