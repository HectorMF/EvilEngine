package com.perfectplay.org.scripts;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.perfectplay.org.components.Transform;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.tween.TweenRegister;
import com.perfectplay.org.tween.accessors.TransformAccessor;

public class TestScript extends Script{

	/*
	 * Use as a constructor for your Script. 
	 * 
	 */
	@Override
	public void initialize() {
		System.out.println("Initialized TestScript for Entity: " + entity.getId());
		//initialize some tweens for the entity;
		Transform transform = components.get(Transform.class);
		TweenRegister.register("Rotate", Tween.to(transform,TransformAccessor.ROTATION, 3f).target(360));
		TweenRegister.register("Move1", Tween.to(transform, TransformAccessor.POSITION, 2f).target(200,150,10));
		TweenRegister.register("Move2", Tween.to(transform, TransformAccessor.POSITION, 3f).target(500,100,0));
		
		Timeline t0 = Timeline.createParallel().push(TweenRegister.get("Rotate")).push(TweenRegister.get("Move1"));
		Timeline t1 = Timeline.createSequence().push(Tween.set(transform,TransformAccessor.POSITION).target(22,0,0)).push(t0).push(TweenRegister.get("Move2")).repeatYoyo(-1,0);
		TweenRegister.start(t1);
	}

	/*
	 * Called once per frame. Use World.getDelta() for DT
	 */
	@Override
	public void onUpdate() {
		
	}

}
