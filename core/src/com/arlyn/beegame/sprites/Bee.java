package com.arlyn.beegame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.xml.soap.Text;

/**
 * Created by Arlyn on 9/12/2016.
 */
public class Bee {
    //private static final int GRAVITY = -15;
    /*I changed this to make it easier*/ private static final int GRAVITY = -12;
    private static final int MOVEMENT = 100; //move along x-axis forward
    private Vector3 position; //3D vector for bee's positon, will only use x an y
    private Vector3 velocity;
    private Rectangle bounds; //invisible rectangle around our bord for detecting collisions
    private Texture texture;
    private Animation beeanimation;
   // private Sound flap; //took out flapping wings sound effect for now

    public Bee(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0); //starts at not moving
        texture = new Texture("bee.png");
        Texture texture = new Texture("beeanimation.png");
        beeanimation = new Animation(new TextureRegion(texture),3,0.5f); //3 different frames bc 3 images, cycle time 0.5
        bounds = new Rectangle(x,y, texture.getWidth() /3, texture.getHeight());
        //flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        beeanimation.update(dt);
        if (position.y > 0) { //if not out of screen (fallen), then add gravity
            velocity.add(0, GRAVITY, 0); //each time bee is updated, gravity affects its velocity
        }
        velocity.scl(dt); //scales everything by delta time
        position.add(MOVEMENT * dt, velocity.y, 0); //update/set our position. move along x and fall w gravity
        if (position.y < 0) { //if not in screen
            position.y = 0;
        }
        velocity.scl(1/dt); //reverse scale
        bounds.setPosition(position.x, position.y); //update invisible rectangle over bee
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return beeanimation.getFrame();
    }

    public void jump() {
        //velocity.y = 250; //positive - opposite of gravity to move position up
        /*I changed it to make it jump less*/
        velocity.y = 240;
        //flap.play(0.8f); //set sound effect flap to 70% volume
    }

    public Rectangle getBounds() { //returns invisible rectangle bounds
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        //flap.dispose();
    }
}
