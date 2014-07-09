package com.example.mysnake.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.mysnake.SnakeGame;
import com.example.mysnake.network.Network;
import com.example.mysnake.network.Network.OnInitListener;

public class MultiplayerScreen implements Screen{

	private  SnakeGame game;
	private Stage stage;
	private TextButton buttonCreate;
	private TextButton buttonShow;
	
	public static final String IP = "192.168.3.157";
	
	public MultiplayerScreen(SnakeGame game) {
		init();
		this.game = game;
		
	}
	private void init() {
//		stage = new Stage();
		OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, MainScreen.width, MainScreen.height);
		
		stage = new Stage();
		Viewport v = new StretchViewport(MainScreen.width, MainScreen.height, cam);
		stage.setViewport(v);
//		stage.setViewport(new StretchViewport(MainScreen.SCREEN_WIDTH, MainScreen.SCREEN_HEIGHT));
//		stage.clear();  
		Gdx.input.setInputProcessor(stage);
		
		BitmapFont buttonSingleFont = new BitmapFont();
	       buttonSingleFont.setScale(3, 3);
	        TextButtonStyle buttonSingleStyle = new TextButtonStyle(); //** Button properties **//
	        buttonSingleStyle.font = buttonSingleFont;
	        
	        buttonCreate = new TextButton("CREATE NEW GAME", buttonSingleStyle); //** Button text and style **//
//	        buttonSingle.setPosition(100, 800); //** Button location **//
	        buttonCreate.setSize(100, 100);
	        
	        buttonCreate.addListener(new ClickListener() {
	        	@Override
	        	public void clicked(InputEvent event, float x, float y) {
	        		// TODO Auto-generated method stub
	        		super.clicked(event, x, y);
	        		game.setScreen(new ServerScreen(game));
					Gdx.app.log("log", "bam");
	        	}
	        });
	        
	        TextButtonStyle buttonMultiStyle = new TextButtonStyle();
	        BitmapFont buttonMultiFont = new BitmapFont();
	        buttonMultiFont.setScale(3);
	        buttonMultiStyle.font = buttonMultiFont;
	        
	        buttonShow = new TextButton("SHOW GAMES", buttonMultiStyle);
//	        buttonMulti.setPosition(100, 700);
	        buttonShow.setSize(100, 100);
	        
	        VerticalGroup vg = new VerticalGroup().space(3).pad(5).fill();
	        vg.setBounds(0, 0, MainScreen.SCREEN_WIDTH, MainScreen.SCREEN_HEIGHT);
	        
	        //////////////////
	        final Network mNetwork = new Network(IP);
	        
	        mNetwork.start();
	        mNetwork.setOnInitListener(new OnInitListener() {
				
				@Override
				public void onInit() {
					mNetwork.send(100);
	        		Gdx.app.log("logmulti", "send!");
				}
			});
//	        while(!mNetwork.isInit());
//	        Gdx.app.log("LOGCAT", "INIT!");
	        
	        TextButton buttonTest = new TextButton("send", buttonMultiStyle);
	        buttonTest.setSize(100, 100);
	        buttonTest.addListener(new ClickListener() {
	        	@Override
	        	public void clicked(InputEvent event, float x, float y) {
	        		super.clicked(event, x, y);
	        		
	        		
	    	        
//	        		mNetwork.send(100);
//	        		Gdx.app.log("logmulti", "send!");
	        	}
	        });
	        
	        Skin skin = new Skin();
//	        skin.ad
	       
	        //////////////////////
	        TextFieldStyle ipStyle = new TextFieldStyle();
	        
	        ipStyle.font = buttonSingleFont;
	        TextArea ipTextArea = new TextArea("", ipStyle);
	        
	        vg.addActor(buttonCreate);
	        vg.addActor(buttonShow);
	        vg.addActor(ipTextArea);
	        
	        ////////////
	        vg.addActor(buttonTest);
	        ////////////
		
	        stage.addActor(vg);
	        
//	        Table table = new Table();
//	        table.setPosition(400, 800);
//	        table.add(buttonCreate);
//	        table.row();
//	        table.add(buttonShow);
//	        
//		
//	        stage.addActor(table);
//	        stage.addActor(buttonSingle);
//	        stage.addActor(buttonMulti);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
//		stage.draw();
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
