package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

public class GoldenWaterDrop extends Droplet implements Pool.Poolable {
    private static final Texture dropImage = new Texture(Gdx.files.internal("goldendrop.png"));
    private static final Pool<GoldenWaterDrop> goldenWaterDropPool = new Pool<GoldenWaterDrop>() {
        @Override
        protected GoldenWaterDrop newObject() {
            return new GoldenWaterDrop();
        }
    };
    protected static final int SPAWN_CHANCE = 10;
    protected static final int DROP_SPEED = 600;


    @Override
    public void reset() {
        spawnRainDrop();
    }

    public static void dispose() {
        dropImage.dispose();
    }

    @Override
    public Texture getDropImage() {
        return dropImage;
    }

    @Override
    public int getDropSpeed() {
        return DROP_SPEED;
    }

    public static Pool<GoldenWaterDrop> getPool() {
        return goldenWaterDropPool;
    }
}
