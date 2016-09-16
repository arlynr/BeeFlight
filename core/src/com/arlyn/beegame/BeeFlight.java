package com.arlyn.beegame;

import com.arlyn.beegame.states.PlayScreen;
import com.arlyn.beegame.states.GameStateManager;
import com.arlyn.beegame.states.MenuState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BeeFlight extends Game {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Bee Flight";

	public static GameStateManager gsm;
	public static SpriteBatch batch; //changed to public so other screens have access
	private Music music;
	public BitmapFont scoreBox;

	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true); //loop music
		music.setVolume(0.05f);  //0.1 is 10% volume
		music.play(); //starts when game starts
		Gdx.gl.glClearColor(1, 0, 0, 1); //wipes scree clean
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		music.dispose();
	}

}
