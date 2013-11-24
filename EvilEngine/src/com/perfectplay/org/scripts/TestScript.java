package com.perfectplay.org.scripts;

import aurelienribon.tweenengine.Tween;

import com.perfectplay.org.components.Transform;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.tween.TransformAccessor;
import com.perfectplay.org.tween.TweenRegister;

public class TestScript extends Script{

	private boolean isMoving = false;
	float val;
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		Transform transform = components.get(Transform.class);
		if(!isMoving){
			TweenRegister.register("Rotate", Tween.to(transform,TransformAccessor.ROTATION, 1f).target(360));
			TweenRegister.start("Rotate");
			isMoving = !isMoving;
		}
	}

}
