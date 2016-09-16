package com.arlyn.beegame.states;

import com.arlyn.beegame.BeeFlight;
import com.arlyn.beegame.sprites.Bee;
import com.arlyn.beegame.sprites.Flower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.Hud;

/**
 * Created by Arlyn on 9/12/2016.
 */
public class PlayState extends State {

    private static final int FLOWER_SPACING = 125;  //distance btwn start of one flower to start of another
    private static final int FLOWER_COUNT = 2; //number of flowers total in screen at any given time
    private static final int GROUND_Y_OFFSET = -50;
    private Bee bee;
    private Texture bg;
    private Flower flower;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    public static int score;
    private Hud hud;


    private Array<Flower> flowers; //flower array (gdx array)

    public PlayState(GameStateManager gsm) {
        super(gsm);
        score = 0; //initialize score to 0
        bee = new Bee(50, 300);
        cam.setToOrtho(false, BeeFlight.WIDTH / 2, BeeFlight.HEIGHT / 2); //set cam to half for bee sprite/img so cam only shows small amount of screen
        bg = new Texture("bg.png"); //background image
        //flower = new Flower(100); //flower will be at x position of 100
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET); //offset by first texture will be underneath it

        flowers = new Array<Flower>();

        for (int i = 0; i < FLOWER_COUNT; i++) { //create 2 new flowers
            flowers.add(new Flower(i * (FLOWER_SPACING + Flower.FLOWER_WIDTH)));//create 2 new flowers
            flowers.get(i).resetCollision(); //set collison to false upon creation
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bee.jump(); //when user touches screen bee jumps
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); //always check input to see if user entered anything
        updateGround();
        bee.update(dt); //update bee with new delta time
        cam.position.x = bee.getPosition().x + 80; //make camera follow the bee

        for (int i = 0; i < flowers.size; i++) {//reposition flower when user already passed it (off screen left)
            //Flower flower = flowers.get(i);
            /* if (cam.position.x - cam.viewportWidth / 2 > flowers.get(i).getPosTopflower().x + flowers.get(i).getTopflower().getWidth()) { //if flower is out of cam - so rightmost part (width length out of sight
                flowers.get(i).reposition(flowers.get(i).getPosTopflower().x + ((flowers.get(i).FLOWER_WIDTH + FLOWER_SPACING) * FLOWER_COUNT));
            } */

            if (cam.position.x - cam.viewportWidth / 2 > flowers.get(i).getPosFlower().x + flowers.get(i).getGroundFlower().getWidth()) { //if flower is out of cam - so rightmost part (width length out of sight
                flowers.get(i).reposition(flowers.get(i).getPosFlower().x + ((flowers.get(i).FLOWER_WIDTH + FLOWER_SPACING) * FLOWER_COUNT));
                flowers.get(i).resetCollision(); //reset the collision to false when reposotion flowers
            }
            //check to see if each one of our flowers is touching our bee (manually in for loop since only have 2 flowers and nothing else bee can collide with
            if (flowers.get(i).collides(bee.getBounds()) && !flowers.get(i).getHasCollided() ) { //if bee collides with flower center and hasCollided == false
                flowers.get(i).firstCollision(); //changes hasCollided to true
                score = score + 10; //increment score when bee collides with flower center
                System.out.println("Got it! Score: " + PlayState.getScoreText());
                //flowers.get(i).getGroundFlower().
                        // switch to a new texture
                     // flowers.get(i).getGroundFlower().


               // image.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
                //bottomflower.setDrawable(new TextureRegionDrawable(your new TextureRegion));
                //gsm.set(new PlayState((gsm))); //start over if collide
            }
        }
        if (bee.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            score = 0; //reset score to 0 before start over
            gsm.set(new PlayState(gsm)); //if bee touches the ground, game starts over

        }
            cam.update();
            Hud.update();


    }
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined); //only renders on screen what our user can actually see
        sb.begin(); //to open up our box
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        for (Flower flower : flowers) {
            //sb.draw(flower.getTopflower(), flower.getPosTopflower().x, flower.getPosTopflower().y); //get the flower texture and its x,y position
            sb.draw(flower.getGroundFlower(), flower.getPosFlower().x, flower.getPosFlower().y); //same as above but for bottom flower
        }
        sb.draw(bee.getTexture(), bee.getPosition().x, bee.getPosition().y); //draw bee at 50,50 position
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
        //hud.stage.draw();


    }

    @Override
    public void dispose() {
        bg.dispose();
        bee.dispose();
        ground.dispose();
        for (Flower flower : flowers) {
            flower.dispose();
        }
        System.out.println("Play State Disposed");

    }

    private void updateGround() { //this checks if camera passed ground png
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    public static String getScoreText() {
        return Integer.toString(score);
    }
}

