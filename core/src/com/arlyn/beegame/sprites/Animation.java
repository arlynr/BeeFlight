package com.arlyn.beegame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Arlyn on 9/12/2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount; //frames in an animation
    private int frame;

    public Animation (TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount; //this is 16 bits
        for (int i = 0; i < frameCount; i++) { //animates our 3 bee flapping image
            frames.add(new TextureRegion(region, i * frameWidth, 0,frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime/frameCount;
        frame = 0; //starts at frame 0
    }//region = all images in one region

    public void update (float dt) {
        currentFrameTime += dt; //increment frametime by delta time
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0; //go back to the start of png once reach the end
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame); //get current frame we are on
    }
}
