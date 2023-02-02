package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Drop extends Game {
    protected SpriteBatch batch;
    protected BitmapFont font;
    private MainMenuScreen mainMenuScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        mainMenuScreen = new MainMenuScreen(this);
        this.setScreen(mainMenuScreen);
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        mainMenuScreen.dispose(); //Needs to be called explicitly due to Screen API.
    }
}
