 package com.perfectplay.org;

import java.util.ArrayList;

import com.artemis.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.graphics.Texture2D;
import com.perfectplay.org.level.DepthSortedSpriteLayer;
import com.perfectplay.org.level.Level;
import com.perfectplay.org.scripts.TestScript;
import com.perfectplay.org.serialization.LevelSerializer;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.utils.ParallaxCamera;

public class EvilEngine implements ApplicationListener {
	//camera and batches
	private ParallaxCamera camera;
	private SpriteBatch batch;
	private Box2DDebugRenderer render;
	private ShapeRenderer debug;
	
	//the level object
	private Level level;

	@Override
	public void create() {
		// set up camera
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new ParallaxCamera(w, h);
		camera.position.set(-200 + (w / 2),  -200 +(h / 2), 0);
		camera.update();
		
		//intialize the renderers
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		debug = new ShapeRenderer();
		
		/*//create the level
		level = new Level(4000,4000,100, new Vector2(0,-2f),false);
		level.setSpriteBatch(batch);
		level.setCamera(camera);
		//create a DSSL and add it to the level
		DepthSortedSpriteLayer layer = new DepthSortedSpriteLayer(); 
		//a pass-through method should be made so that level.addLayer(layer) can be called
		level.getRenderSystem().addLayer(layer); 
		level.initialize();
		
		//Load in some textures
		Texture2D texture = new Texture2D(Gdx.files.internal("data/forest.png"));
		Texture2D texture2 = new Texture2D(Gdx.files.internal("data/fire.png.bmp"));
		
		
		 * Set the filters for the textures to determine how they scale
		 * scaling should depend on the content of the image, as choosing different filters
		 * could result in unwanted blur/sharpness.
		 * TextureFilter.Nearest looks at neighboring pixels to determine how to scale
		 * TextureFilter.Linear scales linearly
		 
		texture.setFilter(TextureFilter.Linear,TextureFilter.Nearest);
		texture2.setFilter(TextureFilter.Linear,TextureFilter.Nearest);

		//make an animated sprite using the loaded in textures
		//create a list of Sprites to act as frames of the animation
		ArrayList<Sprite> frames = new ArrayList<Sprite>(); 
		frames.add(new Sprite(texture2,0,0,100,100));
		frames.add(new Sprite(texture2,100,0,100,100)); 
		frames.add(new Sprite(texture2,200,0,100,100)); 
		frames.add(new Sprite(texture2,300,0,100,100)); 
		frames.add(new Sprite(texture2,400,0,100,100)); 
		frames.add(new Sprite(texture2,500,0,100,100)); 
		frames.add(new Sprite(texture2,600,0,100,100)); 
		frames.add(new Sprite(texture2,700,0,100,100)); 
		frames.add(new Sprite(texture2,800,0,100,100)); 
		frames.add(new Sprite(texture2,900,0,100,100)); 
		frames.add(new Sprite(texture2,1000,0,100,100)); 
		frames.add(new Sprite(texture2,1100,0,100,100)); 
		frames.add(new Sprite(texture2,1200,0,100,100)); 
		frames.add(new Sprite(texture2,1300,0,100,100));
		
		//create the animated Sprite with the frames and a 90 ms delay
		AnimatedSprite animatedSprite = new AnimatedSprite(frames,90); 
		
		//Create a new Scripting component and a script to it
		Scripting tweenScripts = new Scripting(); 
		tweenScripts.addScript(TestScript.class);
		
		
		//create an entity
		Entity e = level.createEntity();
		//create a rigid body component, BodyType Dynamic.
		//kinematic bodies should be used for bodies that will be controlled, and static
		//should be used for bodies that won't move
		RigidBody body = new RigidBody(e, BodyType.DynamicBody);
		//add a fixture(a shape) to the body. here we add a box.
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f)); 
		//add the component to the entity
		e.addComponent(body); 
		e.addComponent(new Transform().setPosition(150, 500, 2).setDimension(100, 100, 10)); 
		e.addComponent(new Renderable(animatedSprite,layer)); 
		e.addToWorld();
		 
		
		e = level.createEntity();
		e.addComponent(new Transform().setPosition(0, 0, 10).setDimension(40, 40, 40));
		body = new RigidBody(e, BodyType.StaticBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero,0f, .5f, .5f, .5f));
		e.addComponent(body);
		e.addComponent(new Renderable(animatedSprite,layer).setWidth(50).setHeight(50)); 
		e.addComponent(tweenScripts);
		e.addToWorld();
		  
		
		e = level.createEntity(); 
		body = new RigidBody(e, BodyType.DynamicBody);
		body.addFixture( RigidBody.createCircleFixture(.5f,Vector2.Zero,0f,.5f,.5f));
		body.addFixture(RigidBody.createBoxFixture(110f, 60f, Vector2.Zero, 0f, .4f, .6f, .9f)); 
		body.getBody().setFixedRotation(true);
		e.addComponent(body);
		e.addComponent(new Transform().setPosition(100, 200,10).setDimension(100,100,10)); 
		e.addComponent(new Renderable(animatedSprite,layer));
		e.addToWorld();
		  
		
		e = level.createEntity(); 
		body = new RigidBody(e, BodyType.StaticBody);
		FixtureDef def2 = RigidBody.createBoxFixture(Gdx.graphics.getWidth()/2, 16f,Vector2.Zero, 0f, .5f, 0f, .5f);
		body.addFixture(def2); 
		e.addComponent(body); 
		e.addComponent(new Transform().setPosition(100, 100,1).setDimension(100,100,100));
		e.addComponent(new Renderable(new Sprite(texture),new Vector2(0,0),Vector2.Zero, Color.RED, 1,1, 1f,layer)
						.setWidth(Gdx.graphics.getWidth()/2).setHeight(16));
		e.addComponent(tweenScripts);
		e.addToWorld();
		
		//serialize the level. Level.process is called so that all entities are added to world etc.
		LevelSerializer s = new LevelSerializer(); 
		level.process();
		s.WriteLevel(level, "test.bin");*/
		
		LevelSerializer ls = new LevelSerializer();
		level = ls.ReadLevel("test.bin");
		level.setCamera(camera);
		level.setSpriteBatch(batch);
		level.initialize();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {
		//handle camera movement
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP))
			camera.position.y += 3;
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
			camera.position.y -= 3;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
			camera.position.x -= 3;
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
			camera.position.x += 3;
		
		//clear screen and update cameras
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		debug.setProjectionMatrix(camera.combined);
		
		//update and render level
		level.setDelta(Gdx.graphics.getDeltaTime());
		level.process();
		level.render();

		//debug rendering
		render.render(PhysicsSystem.getWorld(), camera.combined.cpy().scl(100f));
		level.spatialGridSystem.getSpatialGrid().debugRender(debug);
	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }
}
