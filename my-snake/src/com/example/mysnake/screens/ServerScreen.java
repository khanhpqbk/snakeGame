package com.example.mysnake.screens;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.mysnake.SnakeGame;
import com.example.mysnake.network.Network;

public class ServerScreen implements Screen{

	private SnakeGame mGame;
	private Network mNetwork;
//	protected com.badlogic.gdx.net.ServerSocket serverSocket;
	java.net.Socket socket;
	private ServerSocket serverSocket;
	private Thread mThread;
	private Stage mStage;
	protected int mess;
	private TextField txt;
	private Label label;
	
	public ServerScreen(SnakeGame game) {
		this.mGame = game;
		init();
	}
	
	private void init() {
//		mNetwork = new Network();
//		mNetwork.start();
		
//		mStage = new Stage();
		OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, MainScreen.width, MainScreen.height);
		
		mStage = new Stage();
		Viewport v = new StretchViewport(MainScreen.width, MainScreen.height, cam);
		mStage.setViewport(v);
		
		Gdx.input.setInputProcessor(mStage);
		
		LabelStyle txtStyle = new LabelStyle();
		BitmapFont font = new BitmapFont();
		font.setScale(3);
		txtStyle.font = font;
		label = new Label("here", txtStyle);
		
		
		mStage.addActor(label);
		
//		mThread = 
				new Thread(new Runnable() {
			
			@Override
			public void run() {
//				ServerSocketHints socketHints = new ServerSocketHints();
//				socketHints.acceptTimeout = 0;
//				
//				serverSocket = Gdx.net.newServerSocket(Protocol.TCP	, 7575, socketHints);
				
				try {
					serverSocket = new ServerSocket(7575);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
					
				}
				
				
				try {
					socket = serverSocket.accept();
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//				Socket socket = serverSocket.accept(null);
				while(true) {
					
					DataInputStream dis = null;
					try {
						InputStream is = socket.getInputStream();
						dis = new DataInputStream(is);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						mess = dis.readInt();
						txt.setText("" + mess);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}).start();; // end of NEW thread()
		
		
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		mStage.draw();
//		mThread.start();
//		Gdx.app.log("logstart thread", "start!");
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
