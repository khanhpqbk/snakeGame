package com.example.mysnake.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.example.mysnake.model.Block;
import com.example.mysnake.model.Coord;
import com.example.mysnake.model.Item;
import com.example.mysnake.model.ItemApple;
import com.example.mysnake.model.ItemInvi;
import com.example.mysnake.model.ItemReverse;
import com.example.mysnake.model.Snake;
import com.example.mysnake.model.World;

public class WorldRenderer {
	public static final float CAMERA_WIDTH = 20;
	public static final float CAMERA_HEIGHT = 40;
	
	private World mWorld;
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	
//	private boolean debug = false;
	
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	
//	private Texture mBlockTexture;
//	private Texture mSnakeHeadTexture;
//	private Texture mSnakeTrunkTexture;

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}
	
	public WorldRenderer(World world) {
		this.mWorld = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
//		loadTextures();
	}
	
//	private void loadTextures() {
//		mBlockTexture = new Texture(Gdx.files.internal("data/images/block.png"));
//		mSnakeHeadTexture = new Texture(Gdx.files.internal("data/images/snake_head.jpg"));
//		mSnakeTrunkTexture = new Texture(Gdx.files.internal("data/images/snake_trunk.png"));
//	}

	public void render() {
		spriteBatch.begin();
		
		drawBlocks();
		drawSnake();
		
		drawItems();
		
		spriteBatch.end();
	}

	private void drawSnake() {
		Snake snake = mWorld.getmSnake();
		Array<Coord> array = snake.getmSnakeTrails();
		spriteBatch.draw(snake.getmHeadTexture(), array.get(0).getmPosition().x * ppuX,
				array.get(0).getmPosition().y * ppuY,  ppuX, ppuY);
		for(int i=1; i<array.size; i++) {
			spriteBatch.draw(snake.getmTrunkTexture(), array.get(i).getmPosition().x * ppuX,
					array.get(i).getmPosition().y * ppuY,   ppuX, ppuY);
		}
	}

	private void drawBlocks() {
		for(Block item: mWorld.getmBlocks()) {
			spriteBatch.draw(Block.getmTexture(), item.getmPosition().x * ppuX,
					item.getmPosition().y * ppuY,  ppuX, ppuY);
		}
	}
	
	private void drawItems() {
		ItemApple apple = null;
		ItemInvi invi  = null;
		ItemReverse rev = null;
		
		for(Item item: mWorld.getmItems()) {
			if(item instanceof ItemApple) {
				apple = (ItemApple) item;
				spriteBatch.draw(apple.getmTexture(), apple.getmPosition().x * ppuX, apple.getmPosition().y * ppuY,
						ppuX, ppuY);
			} else if (item instanceof ItemInvi) {
				invi = (ItemInvi) item;
				spriteBatch.draw(invi.getmTexture(), invi.getmPosition().x * ppuX,  invi.getmPosition().y * ppuY,
						ppuX, ppuY);
			} else if(item instanceof ItemReverse) {
				rev = (ItemReverse) item;
				spriteBatch.draw(rev.getmTexture(), rev.getmPosition().x * ppuX,  rev.getmPosition().y * ppuY,
						ppuX, ppuY);
			}
		}
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}
	
	
}
