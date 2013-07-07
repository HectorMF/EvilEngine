package com.perfectplay.org;

import java.util.ArrayList;


import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
//import com.perfectplay.org.box2dLight.RayHandler;
import com.perfectplay.org.components.BackgroundRender;
import com.perfectplay.org.components.EventRegion;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.events.CollisionEvent;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.systems.BackgroundRenderSystem;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.RegionSystem;
import com.perfectplay.org.systems.SpriteRenderSystem;
import com.perfectplay.org.utils.BodyRemover;

public class Anastasius implements ApplicationListener {
	
	//artemis entity system stuffs
	private com.artemis.World world;
	private EntitySystem renderSystem;
	private PhysicsSystem physicsSystem;
	private BackgroundRenderSystem bgRender;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//textures
	Texture texture;
	Texture texture2;
	//private RayHandler rayHandler;
	private Box2DDebugRenderer render;
	Body circleBody;
	Entity e;
	Transform t = new Transform(680,510,-70,110,60,80);
	RigidBody b;
	@Override
	public void create() {	
		//Get screen data
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
        camera.position.set(w / 2, h/ 2, 0);
        camera.update();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
 
		
		texture = new Texture(Gdx.files.internal("data/castlea.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/test.png"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//artemis stuffs
		world = new com.artemis.World();
		renderSystem = world.setSystem(new SpriteRenderSystem(batch), true);
		physicsSystem = world.setSystem(new PhysicsSystem(new World(new Vector2(0,-1f),false)),true);
		bgRender = world.setSystem(new BackgroundRenderSystem(batch), true);
		world.setSystem(new RegionSystem());
		world.initialize();
		BodyRemover.getInstance().setWorld(PhysicsSystem.getWorld());
		
		//make an animated sprite
		ArrayList<Sprite> frames = new ArrayList<Sprite>();
		frames.add(new Sprite(texture,0,0,512,275));
		frames.add(new Sprite(texture2,0,0,64,64));
		AnimatedSprite aSprite = new AnimatedSprite(frames,1000);
		
	    e = world.createEntity();
		e.addComponent(new Transform(540,300,-40,50,50,60));
		RigidBody body = new RigidBody(e, BodyType.DynamicBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f));
		e.addComponent(body);
		e.addComponent(new SpriteRender(aSprite));
		e.addToWorld();
		
		e = world.createEntity();
		e.addComponent(t);
		b= new RigidBody(e, BodyType.DynamicBody);
		b.addFixture(RigidBody.createBoxFixture(110f, 60f, Vector2.Zero, 0f,  .4f, .6f, .9f));
		
		EventRegion region = new EventRegion(e);
		region.addRegion(EventRegion.createCircleRegion(1f, Vector2.Zero, (short)-1, (short)-1, (short)-1), new CollisionEvent());
		
		e.addComponent(b);
		e.addComponent(region);
		e.addComponent(new SpriteRender(aSprite.clone()));

		e.addToWorld();
			
		e = world.createEntity();
		Transform test = new Transform(300,100,0,550,309,0);
		test.setDepth(130);
		e.addComponent(test);
		body = new RigidBody(e,BodyType.StaticBody);
		FixtureDef def = RigidBody.createBoxFixture(Gdx.graphics.getWidth()/2, 16f, Vector2.Zero, 0f,  .5f, 0f, .5f);
		body.addFixture(def);
	
		e.addComponent(body);
		e.addComponent(new BackgroundRender(new Sprite(texture, 0, 0, 550,309), new Vector2(0,150)));
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
		
		int speed = 60;
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
			t.setZ(t.getZ() - Gdx.graphics.getDeltaTime() * speed);
			
		//PhysicsSystem.getWorld().re
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
			t.setZ(t.getZ() + Gdx.graphics.getDeltaTime() * speed);
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
			b.getBody().setLinearVelocity(-1, 0);
			
		//PhysicsSystem.getWorld().re
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
			b.getBody().setLinearVelocity(1, 0);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			b.getBody().setLinearVelocity(0,1);
		
		//System.out.println(t.getZ());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
		
		physicsSystem.process();
		
		batch.begin();
		bgRender.process();
		renderSystem.process();
		batch.end();
		//render.render(PhysicsSystem.getWorld(),camera.combined.cpy().scl(100f));
		render.render(PhysicsSystem.getWorld(),camera.combined.cpy().scl(20f));
		//render.
		//if(!PhysicsSystem.getWorld().isLocked())
		
		//render.
		//e.getComponent(Physics.class).getBody().setTransform(Pixel.toMeter(new Vector2(Gdx.input.getX(),
		//		Gdx.graphics.getHeight() - Gdx.input.getY())),0);
		//rayHandler.setCombinedMatrix(camera.combined.cpy().scl(1f));
	//	rayHandler.updateAndRender();
		
		
		
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
