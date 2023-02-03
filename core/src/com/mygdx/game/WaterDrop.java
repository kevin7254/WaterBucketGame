package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

public class WaterDrop extends Droplet implements Pool.Poolable {
    private static final Texture dropImage = new Texture(Gdx.files.internal("droplet.png"));
    protected static final int DROP_SPEED = 200;


    private static final Pool<WaterDrop> waterDropPool = new Pool<WaterDrop>() {
        @Override
        protected WaterDrop newObject() {
            return new WaterDrop();
        }
    };

    public WaterDrop() {
    }

    @Override
    public void reset() {
        spawnRainDrop();
    }

    public static void dispose() {
        dropImage.dispose();
    }

    public Texture getDropImage() {
        return dropImage;
    }

    public static Pool<WaterDrop> getPool() {
        return waterDropPool;
    }

    @Override
    public int getDropSpeed() {
        return DROP_SPEED;
    }
}
