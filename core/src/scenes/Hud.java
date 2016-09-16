package scenes;

import com.arlyn.beegame.BeeFlight;
import com.arlyn.beegame.states.PlayState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Arlyn on 9/16/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    private static int level;

    public static Label scoreLabel, levelLabel;

    public Hud(SpriteBatch sb) {
        level = 1;
        viewport = new FitViewport(BeeFlight.WIDTH, BeeFlight.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.setFillParent(true); //table is the sice of our stage

        scoreLabel = new Label(String.format("SCORE \n  %04d", PlayState.score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("LEVEL \n    %02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padBottom(650).left();
        table.add(levelLabel).expandX().padBottom(650).right();
        stage.addActor(table); //add table to stage
    }

    public static void update(){
        scoreLabel.setText(String.format("SCORE \n  %04d", PlayState.score));
        levelLabel.setText(String.format("LEVEL \n    %02d", level));
    }
}
