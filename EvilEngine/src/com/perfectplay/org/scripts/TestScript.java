package com.perfectplay.org.scripts;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.AnimatedSprite;
import com.perfectplay.org.graphics.SpriteManager;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.systems.TweenSystem;
import com.perfectplay.org.tween.accessors.TransformAccessor;

public class TestScript extends Script{

	private AnimatedSprite test;
	private static int count = 0;
	/*
	 * Use as a constructor for your Script. 
	 * 
	 */
	@Override
	public void initialize() {
		System.out.println("Initialized TestScript for Entity: " + entity.getId());
		
		Transform transform = components.get(Transform.class);
		
		//rotates 360 over 3 seconds
		Tween rotate = Tween.to(transform,TransformAccessor.ROTATION, 3f).target(360);
		//move to 200,150,10 over 2 seconds
		Tween move1 = Tween.to(transform, TransformAccessor.POSITION, 2f).target(200,150,10);
		//move to 500,100,0 over 3 seconds
		Tween move2 = Tween.to(transform, TransformAccessor.POSITION, 3f).target(500,100,0);
		
		Timeline timeline = Timeline.createSequence().beginParallel().push(rotate).push(move1).end().push(move2).repeatYoyo(-1,0);
		
		TweenSystem.start(timeline);
		test = (AnimatedSprite) SpriteManager.sprites.toArray()[0];
		System.out.println(test);
	}

	/*
	 * Called once per frame. Use World.getDelta() for DT
	 */
	@Override
	public void onUpdate() {
		
		//need to limit count to less that 64 because of limitations with collisions. 
		//since we are spawning so many on top of one another, the system freaks out.
		
		if(count < 100){
			count++;
			Entity e = world.createEntity();
			RigidBody body = new RigidBody(e, BodyType.KinematicBody);
			//add a fixture(a shape) to the body. here we add a box.
			body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f)); 
			//add the component to the entity
			e.addComponent(body); 
			e.addComponent(new Transform().setPosition((count%10)*50, (count/10)*50, 10).setDimension(100, 100, 10)); 
			e.addComponent(new Renderable(test,world.getLayer(0)).setScaleX(1).setScaleY(1)); 
			e.addComponent(new Scripting().addScript(TestScript.class));
			e.addToWorld();
		}
		
	}

}
