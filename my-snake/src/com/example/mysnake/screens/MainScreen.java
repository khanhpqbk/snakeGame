package com.example.mysnake.screens;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.mysnake.SnakeGame;

public class MainScreen implements Screen {

	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 480;
	
	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	
	public static int width = Gdx.graphics.getWidth();
	public static int height = Gdx.graphics.getHeight();
	
//	 private OrthographicCamera camera;
	    private BitmapFont font; //** same as that used in Tut 7 **//
	    private TextureAtlas buttonsAtlas; //** image of buttons **//
	    private Skin buttonSkin; //** images are used as skins of the button **//
	    private TextButton button; //** the button - the only actor in program **//

	SnakeGame g;
	private OrthographicCamera cam;
	private Table table;
	private TextButton buttonMulti;
	private TextButton buttonSingle;

	public MainScreen(SnakeGame g) {
		init();
		this.g = g;
	}

	public MainScreen() {
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, width, height);
		
		stage = new Stage();
		Viewport v = new StretchViewport(width, height, cam);
		stage.setViewport(v);
//		stage.clear();  
		Gdx.input.setInputProcessor(stage);
		
//		cam = new OrthographicCamera();
//		
//		 cam.setToOrtho(false, 800, 480); //** w/h ratio = 1.66 **//
		  
		 
	       BitmapFont buttonSingleFont = new BitmapFont();
	       buttonSingleFont.setScale(3, 3);
	        TextButtonStyle buttonSingleStyle = new TextButtonStyle(); //** Button properties **//
	        buttonSingleStyle.font = buttonSingleFont;
	        
	        buttonSingle = new TextButton("SINGLE PLAYER", buttonSingleStyle); //** Button text and style **//
//	        buttonSingle.setPosition(100, 800); //** Button location **//
	        buttonSingle.setSize(100, 100);
	        
	        buttonSingle.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
				
				@Override
				public void changed(
						com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event,
						Actor actor) {
					g.setScreen(new GameScreen(g));
					Gdx.app.log("log", "bam");
				}
			});
	        
	        TextButtonStyle buttonMultiStyle = new TextButtonStyle();
	        BitmapFont buttonMultiFont = new BitmapFont();
	        buttonMultiFont.setScale(3);
	        buttonMultiStyle.font = buttonMultiFont;
	        
	        buttonMulti = new TextButton("MULTIPLAYER", buttonMultiStyle);
//	        buttonMulti.setPosition(100, 700);
	        buttonMulti.setSize(100, 100);
	        
	        buttonMulti.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
				
				@Override
				public void changed(
						com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event,
						Actor actor) {
					g.setScreen(new MultiplayerScreen(g));
				}
			});
	        
//	        Table table = new Table();
//	        table.setPosition(400, 800);
//	        table.add(buttonSingle);
//	        table.row();
//	        table.add(buttonMulti);
	        VerticalGroup vg = new VerticalGroup().space(3).pad(5).fill();
	        vg.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	        
	        vg.addActor(buttonSingle);
	        vg.addActor(buttonMulti);
		
	        stage.addActor(vg);
//	        stage.addActor(table);
//	        stage.addActor(buttonSingle);
//	        stage.addActor(buttonMulti);

	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		
//		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
//		final float buttonX = ( width - BUTTON_WIDTH ) / 2;
//        float currentY = 280f;
// 
//        // label "welcome"
//        Label welcomeLabel = new Label( "Welcome to Tyrian for Android!", getSkin() );
//        welcomeLabel.setX(( ( width - welcomeLabel.getWidth() ) / 2 ));
//        welcomeLabel.setY(( currentY + 100 ));
//        stage.addActor( welcomeLabel );
// 
//        // button "start game"
//        TextButton startGameButton = new TextButton( "Start game", getSkin() );
//        startGameButton.setX(buttonX);
//        startGameButton.setY(currentY);
//        startGameButton.setWidth(BUTTON_WIDTH);
//        startGameButton.setHeight(BUTTON_HEIGHT);
//        stage.addActor( startGameButton );
// 
//        // button "options"
//        TextButton optionsButton = new TextButton( "Options", getSkin() );
//        optionsButton.setX(buttonX);
//        optionsButton.setY(( currentY -= BUTTON_HEIGHT + BUTTON_SPACING ));
//        optionsButton.setWidth(BUTTON_WIDTH);
//        optionsButton.setHeight(BUTTON_HEIGHT);
//        stage.addActor( optionsButton );
// 
//        // button "hall of fame"
//        TextButton hallOfFameButton = new TextButton( "Hall of Fame", getSkin() );
//        hallOfFameButton.setX(buttonX);
//        hallOfFameButton.setY(( currentY -= BUTTON_HEIGHT + BUTTON_SPACING ));
//        hallOfFameButton.setWidth(BUTTON_WIDTH);
//        hallOfFameButton.setHeight(BUTTON_HEIGHT);
//        stage.addActor( hallOfFameButton );
	}
	

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
