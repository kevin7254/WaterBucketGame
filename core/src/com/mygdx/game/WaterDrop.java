package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class WaterDrop implements Pool.Poolable {
    protected final Rectangle raindrop;

    public WaterDrop() {
        raindrop = new Rectangle();
    }

    @Override
    public void reset() {
        spawnRainDrop();
    }

    protected void spawnRainDrop() {
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
    }

    public void setY(float y) {
        raindrop.y -= y;
    }

    public float getX() {
        return raindrop.x;
    }

    public float getY() {
        return raindrop.y;
    }
}
