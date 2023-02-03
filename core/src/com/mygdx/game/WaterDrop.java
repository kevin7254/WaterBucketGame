package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

public class WaterDrop extends Droplet implements Pool.Poolable {
    private static final Texture dropImage = new Texture(Gdx.files.internal("droplet.png"));
    private static final int DROP_SPEED = 200;

    private static final Pool<Droplet> waterDropPool = new Pool<Droplet>() {
        @Override
        protected Droplet newObject() {
            return new WaterDrop();
        }
    };

    public WaterDrop() {
    }

    @Override
    public void reset() {
        spawnRainDrop();
    }

    protected void spawnRainDrop() {
        setX(MathUtils.random(0, 800 - 64));
        setY(480);
        setWidth(64);
        setHeight(64);
    }

    public static void dispose() {
        dropImage.dispose();
    }

    public Texture getDropImage() {
        return dropImage;
    }

    public static Pool<Droplet> getPool() {
        return waterDropPool;
    }
}
