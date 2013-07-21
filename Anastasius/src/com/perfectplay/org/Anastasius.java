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
//import com.perfectplay.org.box2dLight.RayHandler;
import com.perfectplay.org.components.BackgroundRender;
import com.perfectplay.org.components.EventRegion;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Script;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.events.CollisionEvent;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.scripting.TestDelegate;
import com.perfectplay.org.systems.PhysicsSystem;

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
	Transform t = new Transform(600,500,0,110,60,10,00);
	RigidBody b;
	ShapeRenderer debug;
	FPSLogger log = new FPSLogger();
	
	@Override
	public void create() {	
		
		//set up camera
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
        camera.position.set(400+(w / 2), 400+(h/ 2), 0);
        camera.update();
        
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		debug = new ShapeRenderer();
 
		level = new Level(4000,4000,100, batch, new Vector2(1f,-2f),false);
		
		
		texture = new Texture(Gdx.files.internal("data/castlea.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/test.png"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		

		level.initialize();
		
		//make an animated sprite
		ArrayList<Sprite> frames = new ArrayList<Sprite>();
		frames.add(new Sprite(texture,0,0,512,275));
		frames.add(new Sprite(texture2,0,0,64,64));
		AnimatedSprite aSprite = new AnimatedSprite(frames,1000);
		Script testScript = new Script();
		testScript.addDelegate(new TestDelegate());
		
	    e = level.createEntity();
		//e.addComponent(new Transform(400,590,00,50,50,50,0));
		RigidBody body = new RigidBody(e, BodyType.DynamicBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f));
		//e.addComponent(body);
		//e.addComponent(new SpriteRender(aSprite));
		e.addComponent(testScript);
		e.addToWorld();
		
		e = level.createEntity();
		e.addComponent(t);
		b= new RigidBody(e, BodyType.DynamicBody);
		b.addFixture(RigidBody.createBoxFixture(110f, 60f, Vector2.Zero, 0f,  .4f, .6f, .9f));
		b.getBody().setFixedRotation(true);
		EventRegion region = new EventRegion(e);
		region.addRegion(EventRegion.createCircleRegion(1f, Vector2.Zero, (short)-1, (short)-1, (short)-1), new CollisionEvent());
		
		e.addComponent(b);
		e.addComponent(region);
		e.addComponent(new SpriteRender(aSprite.clone()));
		e.addComponent(testScript);
		e.addToWorld();

		e = level.createEntity();
		Transform test = new Transform(300,100,0,550,290,130,0);
	
		e.addComponent(test);
		body = new RigidBody(e,BodyType.StaticBody);
		FixtureDef def = RigidBody.createBoxFixture(Gdx.graphics.getWidth()/2, 16f, Vector2.Zero, 0f,  .5f, 0f, .5f);
		body.addFixture(def);
		
		e.addComponent(body);
		e.addComponent(new BackgroundRender(new Sprite(texture, 0, 0, 550,309), new Vector2(0,0)));
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
