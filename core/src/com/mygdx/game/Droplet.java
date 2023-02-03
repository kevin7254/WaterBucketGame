package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public abstract class Droplet extends Rectangle implements Pool.Poolable {

    public Droplet() {
    }

    protected abstract void spawnRainDrop();

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public Rectangle setX(float x) {
        return super.setX(x);
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public Rectangle setY(float y) {
        return super.setY(y);
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }

    @Override
    public Rectangle setWidth(float width) {
        return super.setWidth(width);
    }

    @Override
    public float getHeight() {
        return super.getHeight();
    }

    @Override
    public Rectangle setHeight(float height) {
        return super.setHeight(height);
    }

    @Override
    public boolean overlaps(Rectangle r) {
        return super.overlaps(r);
    }

    public abstract Texture getDropImage();
}
