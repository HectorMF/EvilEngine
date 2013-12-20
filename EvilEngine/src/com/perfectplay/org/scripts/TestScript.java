package com.perfectplay.org.scripts;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.artemis.Entity;
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
		Tween rotate = Tween.to(transform,TransformAccessor.ROTATION, 2f).target(360).repeatYoyo(-1, 0);
		//move to 200,150,10 over 2 seconds
		Tween move1 = Tween.to(transform, TransformAccessor.POSITION, 2f).target(200,150,10);
		//move to 500,100,0 over 3 seconds
		Tween move2 = Tween.to(transform, TransformAccessor.POSITION, 3f).target(500,100,0);
		
		Timeline timeline = Timeline.createSequence().beginParallel().push(move1).end().push(move2).repeatYoyo(-1,0);
		
		components.add(new Tweens().add("test1", rotate).add("test",timeline).start("test").start("test1"));
	}

	/*
	 * Called once per frame. Use World.getDelta() for DT
	 */
	@Override
	public void onUpdate() {
		
		//need to limit count to less that 64 because of limitations with collisions. 
		//since we are spawning so many on top of one another, the system freaks out.
		int numPerRow = 30;
		if(count < 300){
			count++;
			Entity e = world.createEntity();
			RigidBody body = new RigidBody(e, BodyType.KinematicBody);
			//add a fixture(a shape) to the body. here we add a box.
			body.addFixture(RigidBody.createBoxFixture(50f, 50f, Vector2.Zero, 0f, .5f, .5f, .5f)); 
			//add the component to the entity
			e.addComponent(body); 
			e.addComponent(new Transform().setPosition((count%numPerRow)*50, (count/numPerRow)*50, 10).setDimension(100, 100, 10)); 
			e.addComponent(new Renderable(SpriteManager.getSprites()[0], world.getLayer(0))); 
			e.addComponent(new Scripts().add(TestScript.class));
			e.addToWorld();
		}
		
	}

}
