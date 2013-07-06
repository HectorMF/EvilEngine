package com.perfectplay.org;

import java.util.ArrayList;


import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
//import com.perfectplay.org.box2dLight.RayHandler;
import com.perfectplay.org.components.EventRegion;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.events.CollisionEvent;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.RegionSystem;
import com.perfectplay.org.systems.SpriteRenderSystem;
import com.perfectplay.org.utils.BodyRemover;

public class Anastasius implements ApplicationListener {
	
	//artemis entity system stuffs
	private com.artemis.World world;
	private EntitySystem renderSystem;
	private PhysicsSystem physicsSystem;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//textures
	Texture texture;
	Texture texture2;
	//private RayHandler rayHandler;
	private Box2DDebugRenderer render;
	Body circleBody;
	Entity e;
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
 
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture2 = new Texture(Gdx.files.internal("data/test.png"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//artemis stuffs
		world = new com.artemis.World();
		renderSystem = world.setSystem(new SpriteRenderSystem(batch), true);
		physicsSystem = world.setSystem(new PhysicsSystem(new World(new Vector2(0,-1f),false)),true);
		world.setSystem(new RegionSystem());
		world.initialize();
		BodyRemover.getInstance().setWorld(PhysicsSystem.getWorld());
		
		//make an animated sprite
		ArrayList<Sprite> frames = new ArrayList<Sprite>();
		frames.add(new Sprite(texture,0,0,512,275));
		frames.add(new Sprite(texture2,0,0,64,64));
		AnimatedSprite aSprite = new AnimatedSprite(frames,1000);
		
	    e = world.createEntity();
		e.addComponent(new Transform(480,300,0,50,50,60));
		RigidBody body = new RigidBody(e,BodyType.DynamicBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f));
		e.addComponent(body);
		e.addComponent(new SpriteRender(aSprite));
		e.addToWorld();
		
		e = world.createEntity();
		e.addComponent(new Transform(550,510,0,50,50,60));
		body = new RigidBody(e,BodyType.DynamicBody);
		body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f,  .4f, .3f, .9f));
		
		EventRegion region = new EventRegion(e);
		region.addRegion(EventRegion.createCircleRegion(1f, Vector2.Zero, (short)-1, (short)-1, (short)-1), new CollisionEvent());
		
		e.addComponent(body);
		e.addComponent(region);
		e.addComponent(new SpriteRender(aSprite.clone()));

		e.addToWorld();
			
		e = world.createEntity();
		e.addComponent(new Transform(300,100,0,Gdx.graphics.getWidth()/2,16,0));
		body = new RigidBody(e,BodyType.StaticBody);
		body.addFixture(RigidBody.createBoxFixture(Gdx.graphics.getWidth()/2, 16f, Vector2.Zero, 0f,  .5f, 0f, .5f));
	
		e.addComponent(body);
		e.addComponent(new SpriteRender(aSprite.clone()));
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
		
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
		
		physicsSystem.process();
		
		batch.begin();
		renderSystem.process();
		batch.end();
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
