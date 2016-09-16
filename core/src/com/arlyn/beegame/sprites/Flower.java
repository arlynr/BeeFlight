package com.arlyn.beegame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Arlyn on 9/12/2016.
 */
public class Flower {
    public static final int FLOWER_WIDTH = 52; //width of flower image is 52 px
    private static final int MAX_flower = 100; //maximum flower length
    private static final int FLOWER_GAP = 100; //max flower gap btwn top of screen and flower top - higher the harder (~140)
    private static final int LOWEST_OPENING = 120 ; //lowest height of a flower - cannot be below screen
    private Texture  groundFlower;
    private Vector2 posFlower; //position vector for flower
    private Rectangle boundsFlower; //invisible rectangle around each flower to calculate if bee collides
    private Random rand; //random flower position
    private int rand_y; //random y position for flower based on rand
    private boolean hasCollided;  //to track if a flower has already been collided so will not continuously increment score

    public Flower(float x) { //takes in the coordinate of x-axis where flower will start

        groundFlower = new Texture("sunflower.png");
        rand = new Random();
        rand_y = rand.nextInt(MAX_flower) + FLOWER_GAP + LOWEST_OPENING;
        posFlower = new Vector2(x, rand_y - FLOWER_GAP - groundFlower.getHeight()); //needs to be at least FLOWER_GAP away from top of screen
        boundsFlower = new Rectangle(posFlower.x, posFlower.y, groundFlower.getWidth(), groundFlower.getHeight());
        //boundsCircle = new Rectangle (posFlower.x + 16, posFlower.y + groundFlower.getHeight()- 32, 16, 16); //define our circle of the flower as a small rectangle height, width 16
    }

    public Texture getGroundFlower() {
        return groundFlower;
    }

    public Vector2 getPosFlower() {
        return posFlower;
    }


    public void firstCollision() {
        hasCollided = true;
    }
    public void resetCollision() {
        hasCollided = false;
    }

    public boolean getHasCollided() {
        return hasCollided;
    }

    public void reposition(float x) { //reposition/reusing the flowers that are out of screen user already passed (left)
       posFlower.set(x, rand_y - FLOWER_GAP - groundFlower.getHeight());

        // boundsTop.setPosition(posTopflower.x, posTopflower.y); //reposotion invisile rectangles too
        boundsFlower.setPosition(posFlower.x, posFlower.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsFlower); //returns true if player is overlapping invisible flower rectangle

    }

    public void dispose() {
        groundFlower.dispose();

    }
}
