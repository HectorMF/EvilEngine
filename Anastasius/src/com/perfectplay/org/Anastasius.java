package com.perfectplay.org;

import java.util.ArrayList;


import com.artemis.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.perfectplay.org.components.EventRegion;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Scriptable;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.events.CollisionEvent;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.DepthSortedSpriteLayer;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.scripting.TestScript;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.utils.RigidBodySpatial;

public class Anastasius implements ApplicationListener {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//textures
	Texture texture;
	Texture texture2;
	//private RayHandler rayHandler;
	private Box2DDebugRenderer render;
	
	Level level;
	
	
	Body circleBody;
	Entity e;
	//Transform t = new Transform(600,500,0,100,100,10,00);
	RigidBodySpatial s;
	RigidBody b;
	ShapeRenderer debug;
	FPSLogger log = new FPSLogger();
	
	@Override
	public void create() {	
		
		//set up camera
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
        camera.position.set(100+(w / 2),100+(h/ 2), 0);
        camera.update();
        
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		debug = new ShapeRenderer();
 
		level = new Level(4000,4000,100, batch, new Vector2(0,-2f),false);
		
		
		texture = new Texture(Gdx.files.internal("data/forest.png"));
		texture2 = new Texture(Gdx.files.internal("data/fire.png.bmp"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		

		level.initialize();
		
		//make an animated sprite
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
		
		AnimatedSprite aSprite = new AnimatedSprite(frames,90);
		Scriptable testScript = new Scriptable();
		testScript.addScript(new TestScript());
		DepthSortedSpriteLayer layer = new DepthSortedSpriteLayer();
		level.getRenderSystem().addLayer(layer);
	    e = level.createEntity();
		
		RigidBody body = new RigidBody(e, BodyType.DynamicBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f));
		e.addComponent(body);
		RigidBodySpatial sp = new RigidBodySpatial(body.getBody());
		sp.setPosition(100, 200,2);
		sp.setSize(100,100, 10);
		e.addComponent(new SpatialComponent(sp));
		e.addComponent(new Renderable(aSprite,layer));
		e.addComponent(testScript);
		e.addToWorld();
		
		e = level.createEntity();
		b= new RigidBody(e, BodyType.DynamicBody);
		b.addFixture(RigidBody.createBoxFixture(110f, 60f, Vector2.Zero, 0f,  .4f, .6f, .9f));
		b.getBody().setFixedRotation(true);
		e.addComponent(b);
		EventRegion region = new EventRegion(e);
		region.addRegion(EventRegion.createCircleRegion(1f, new Vector2(0,0), (short)-1, (short)-1, (short)-1), new CollisionEvent());
		e.addComponent(region);
		sp = new RigidBodySpatial(b.getBody());
		sp.setPosition(100, 700,10);
		sp.setSize(100,100, 10);
		e.addComponent(new SpatialComponent(sp));
		e.addComponent(new Renderable(aSprite,layer));
		e.addComponent(testScript);
		e.addToWorld();

		e = level.createEntity();
		body = new RigidBody(e,BodyType.StaticBody);
		FixtureDef def = RigidBody.createBoxFixture(Gdx.graphics.getWidth()/2, 16f, Vector2.Zero, 0f,  .5f, 0f, .5f);
		body.addFixture(def);
		sp = new RigidBodySpatial(body.getBody());
		sp.setPosition(100, 100,1);
		sp.setSize(100,100, 100);
		e.addComponent(body);
		e.addComponent(new SpatialComponent(sp));
		e.addComponent(new Renderable(new Sprite(texture), new Vector2(0,0),layer));
		e.addToWorld();
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		texture2.dispose();
		//rayHandler.dispose();
	}

	@Override
	public void render() {	
		//log.log();
		//int speed = 60;
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
			camera.position.y += 3;
		
			//t.setZ(t.getZ() - Gdx.graphics.getDeltaTime() * speed);
			
		//PhysicsSystem.getWorld().re
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
			camera.position.y -= 3;
			//t.setZ(t.getZ() + Gdx.graphics.getDeltaTime() * speed);
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
			camera.position.x -= 3;
		//	b.getBody().setLinearVelocity(-1, b.getVelocity().y);
			
		//PhysicsSystem.getWorld().re
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
			camera.position.x += 3;
		//	b.getBody().setLinearVelocity(1, b.getVelocity().y);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		//	b.getBody().setLinearVelocity(b.getVelocity().x, 1);
		      
		//System.out.println(t.getZ());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debug.setProjectionMatrix(camera.combined);
		level.setCamera(camera);
		level.setDelta(Gdx.graphics.getDeltaTime());
		level.process();
		
		batch.begin();
		level.render();
		batch.end();
		
		
		//level.getSpatialGrid().debugRender(debug);
		//render.render(PhysicsSystem.getWorld(),camera.combined.cpy().scl(100f));
		render.render(PhysicsSystem.getWorld(),camera.combined.cpy().scl(20f));
		
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
