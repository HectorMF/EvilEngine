package com.perfectplay.org;

import java.util.ArrayList;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.systems.SpriteRenderSystem;

public class Anastasius implements ApplicationListener {
	
	
	//artemis entity system stuffs
	private World world;
	private EntitySystem renderSystem;
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//textures
	Texture texture;
	Texture texture2;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);      
        camera.position.set(w / 2, h/ 2, 0);
        
		batch = new SpriteBatch();
		
		//load in some textures from the assets/data folder
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture2 = new Texture(Gdx.files.internal("data/test.png"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
 
		
		//artemis stuffs
		world = new World();
		renderSystem = world.setSystem(new SpriteRenderSystem(batch), true);
		world.initialize();

		Entity e = world.createEntity();
		e.addComponent(new Transform(0,0,0,512,275,0));
		
		//make an animated sprite
		ArrayList<Sprite> frames = new ArrayList<Sprite>();
		frames.add(new Sprite(texture,0,0,512,275));
		frames.add(new Sprite(texture2,0,0,64,64));
		AnimatedSprite aSprite = new AnimatedSprite(frames,1000);
		
		e.addComponent(new SpriteRender(aSprite));
		e.addToWorld();

		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		texture2.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
		
		batch.begin();
		renderSystem.process();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		//pause
	}

	@Override
	public void resume() {
	}
}
