package com.arlyn.beegame.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Arlyn on 9/12/2016.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }
    public void push(State state) {
        states.push(state);
    }
    public void pop() {
        states.pop().dispose(); //dispose of any item we dispose
    }

    public void set(State state) {
        states.pop().dispose(); 
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt); //peek looks at top of stack
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
