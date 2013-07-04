package com.perfectplay.org;

import java.util.ArrayList;


import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.box2dLight.ConeLight;
import com.perfectplay.org.box2dLight.PointLight;
import com.perfectplay.org.box2dLight.RayHandler;
import com.perfectplay.org.components.Physics;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.Sprite;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.SpriteRenderSystem;

public class Anastasius implements ApplicationListener {
	
	
	//artemis entity system stuffs
	private World world;
	private com.artemis.World world2;
	private EntitySystem renderSystem;
	private PhysicsSystem physicsSystem;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//textures
	Texture texture;
	Texture texture2;
	private RayHandler rayHandler;
	private Box2DDebugRenderer render;
	Body circleBody;
	@Override
	public void create() {	
		//Get screen data
		float w = Gdx.graphics.getWidth()/5;
		float h = Gdx.graphics.getHeight()/5;
		
		//Create and place camera
		camera = new OrthographicCamera(w, h);      
        camera.position.set(w / 2, h/ 2, 0);
        camera.update();
		batch = new SpriteBatch();
		
		render = new Box2DDebugRenderer();
		

		/*
		//Create ground
		BodyDef groundDef = new BodyDef();
		groundDef.position.set(0, 3);
		
		Body groundBody = world.createBody(groundDef);
		
		PolygonShape box = new PolygonShape();
		
		//camera.viewportWidth = screenWidth/2 here
		box.setAsBox(camera.viewportWidth * 2, 3.0f);
		
		groundBody.createFixture(box,0f);
		*/
		
		
		
		
		
		
		//load in some textures from the assets/data folder
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture2 = new Texture(Gdx.files.internal("data/test.png"));
		//no idea what this does
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(camera.combined);
		
		new PointLight(rayHandler,1000,Color.WHITE,150,w/2,h/2);
		new PointLight(rayHandler,200,Color.RED,50,w/2-10,h/2+50);
		new ConeLight(rayHandler,1000,Color.BLUE,200,w/2+50,h/2-50,110,40);

		//artemis stuffs
		world2 = new com.artemis.World();
		renderSystem = world2.setSystem(new SpriteRenderSystem(batch), true);
		physicsSystem = world2.setSystem(new PhysicsSystem(new World(new Vector2(0,-9f),false)),true);
		world2.initialize();

		Entity e = world2.createEntity();
		e.addComponent(new Transform(w/2,h/2,0,15,15,90));
		
		
		//Make the circle
		BodyDef circle = new BodyDef();
		
		circle.type = BodyType.DynamicBody;
		//circle.position.set(w/2, h/2-10);
		circleBody = physicsSystem.createBody(circle);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(5f);
		
		//Create a fixture for the circle
		FixtureDef circleFixture = new FixtureDef();
		
		circleFixture.shape = circleShape;
		circleFixture.density = 0.5f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 1f;
		
		//Assign the circle's fixture to it's body
		circleBody.createFixture(circleFixture);
		
		BodyDef rect1 = new BodyDef();
		
		Body groundBody1 = physicsSystem.createBody(rect1);
		PolygonShape box1 = new PolygonShape();
		//camera.viewportWidth = screenWidth/2 here
		box1.setAsBox(1000, 3.0f);
		groundBody1.createFixture(box1,0f);
		
		
		BodyDef rect = new BodyDef();
		rect.type = BodyType.DynamicBody;
		
		Body groundBody = physicsSystem.createBody(rect);
		PolygonShape box = new PolygonShape();
		//camera.viewportWidth = screenWidth/2 here
		
		box.setAsBox(15, 15);
		
		groundBody.createFixture(box,0f);
		groundBody.setAngularVelocity(1f);
		
		e.addComponent(new Physics(groundBody));
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
		rayHandler.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		world2.setDelta(Gdx.graphics.getDeltaTime());
		world2.process();
		
		physicsSystem.process();
		
		batch.begin();
		renderSystem.process();
		batch.end();
		
		render.render(physicsSystem.getWorld(),camera.combined);
		//rayHandler.updateAndRender();
		
		
		
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
