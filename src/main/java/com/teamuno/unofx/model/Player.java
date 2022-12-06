package com.teamuno.unofx.model;

public abstract class Player
{
    private String name;
    private int score;

    public Player()
    {
        this.name = "Player";
        this.score = 0;
    }

    public Player( String name )
    {
        this.name = name;
        this.score = 0;
    }
}
