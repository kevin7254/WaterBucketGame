package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.*;

import java.util.Iterator;


public class GameScreen implements Screen {
    private final Drop game;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;

    private OrthographicCamera camera;
    private long lastDropTime;
    private int score;

    private Rectangle bucket;
    private final Vector3 touchPos = new Vector3(); //To stop GC from running frequently

    private final Array<Droplet> waterDrops = new Array<>();

    public GameScreen(final Drop game) {
        this.game = game;

        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }


    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(bucketImage, bucket.x, bucket.y);

        for (Droplet waterdrop : waterDrops) {
            game.batch.draw(waterdrop.getDropImage(), waterdrop.getX(), waterdrop.getY());
        }

        game.font.draw(game.batch, "SCORE: " + score, 700, 450);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bucket.x += 200 * Gdx.graphics.getDeltaTime();
        }

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            Droplet waterDrop = WaterDrop.getPool().obtain();
            waterDrop.spawnRainDrop();
            waterDrops.add(waterDrop);
            lastDropTime = TimeUtils.nanoTime();
        }

        for (Iterator<Droplet> iter = waterDrops.iterator(); iter.hasNext(); ) {
            Droplet waterDrop = iter.next();
            waterDrop.setY(waterDrop.getY() - 200 * Gdx.graphics.getDeltaTime());
            if (waterDrop.getY() + 64 < 0) {
                iter.remove();
                WaterDrop.getPool().free(waterDrop);
            }
            if (waterDrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
                WaterDrop.getPool().free(waterDrop);
                score++;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        WaterDrop.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
