package com.arlyn.beegame.states;

import com.arlyn.beegame.BeeFlight;
import com.arlyn.beegame.sprites.Bee;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import scenes.Hud;

/**
 * Created by Arlyn on 9/16/2016.
 */
public class PlayScreen implements Screen {
    private BeeFlight game;
    private Hud hud;

    public PlayScreen(BeeFlight game) {
        this.game = game;
        hud = new Hud(game.batch);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BeeFlight.gsm.update(Gdx.graphics.getDeltaTime());
        BeeFlight.gsm.render(game.batch);
        game.batch.begin();
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end(); */
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

    }
}
