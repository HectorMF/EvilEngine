package com.perfectplay.org.scripts;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.components.Tweens;
import com.perfectplay.org.graphics.SpriteManager;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.tween.accessors.TransformAccessor;

public class TestScript extends Script{

	private static int count = 3;
	/*
	 * Use as a constructor for your Script. 
	 * 
	 */
	private int temp; 
	private float counter;
	@Override
	public void initialize() {
		System.out.println("Initialized TestScript for Entity: " + entity.getId());
		temp = count;
		counter = 0;
		Transform transform = components.get(Transform.class);
		
		//rotates 360 over 3 seconds
		Tween rotate = Tween.to(transform,TransformAccessor.ROTATION, 2f).target(360).repeatYoyo(-1, 0);
		//move to 200,150,10 over 2 seconds
		Tween move1 = Tween.to(transform, TransformAccessor.POSITION, 2f).target(200,150);
		//move to 500,100,0 over 3 seconds
		Tween move2 = Tween.to(transform, TransformAccessor.POSITION, 3f).target(500,100);
		
		Timeline timeline = Timeline.createSequence().beginParallel().push(move1).end().push(move2).repeatYoyo(-1,0);
		
		components.add(new Tweens().add("test1", rotate).add("test",timeline).start("test").start("test1"));
		counter = 0;
	}

	/*
	 * Called once per frame. Use World.getDelta() for DT
	 */
	@Override
	public void onUpdate() {
		//System.out.println(entity.getId() + " : UPDATE");
		if(Gdx.input.isKeyPressed(Keys.A)){
			System.out.println("Working");
			components.get(Transform.class).setPosition(10, 10).setRotation(0);
			components.get(Tweens.class).stop("test");
			components.get(Tweens.class).stop("test1");
			
		}
		int numPerRow = 10;
		counter+= world.delta;
		
		//System.out.println(entity.getId()  + " : " + counter);
		if(count < 100){
				counter = 0;
				count++;
				Entity e = world.createEntity();
				RigidBody body = new RigidBody(e, BodyType.KinematicBody);
				//add a fixture(a shape) to the body. here we add a box.
				body.addFixture(RigidBody.createBoxFixture(189f, 112f, Vector2.Zero, 0f, .5f, .5f, .5f)); 
				//add the component to the entity
				e.addComponent(body); 
				e.addComponent(new Transform().setPosition((count%numPerRow)*180, (count/numPerRow)*100).setDimension(189, 112)); 
				e.addComponent(new Renderable(SpriteManager.get("Finn"), world.getLayer(0)).setWidth(189).setHeight(112)); 
			//	world.getLayer(1).setParallaxSpeedX(count%5);
				e.addComponent(new Scripts().add(TestScript.class));
				e.addToWorld();
				//entity.removeComponent(Scripts.class);
		}else{
			//entity.removeComponent(Scripts.class);
		}
		
	}

}
