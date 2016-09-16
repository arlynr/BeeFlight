package com.arlyn.beegame.states;

import com.arlyn.beegame.BeeFlight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Arlyn on 8/21/2016.
 */
public class MenuState extends State{

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, BeeFlight.WIDTH / 2, BeeFlight.HEIGHT / 2); //set cam to half for bee sprite/img so cam only shows small amount of screen
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");

    }



    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {//if user touches screen in anyway
            gsm.set(new PlayState(gsm));
            //dispose(); //disposing only when we pop item off - dispose any unused textures - saves mem space and prevents mem leaks
        }
    }

    @Override
    public void update(float dt) {
       handleInput(); //always check input to see if user entered anything
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
