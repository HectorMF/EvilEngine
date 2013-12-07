package com.perfectplay.org.tween;

import java.util.HashMap;

import com.perfectplay.org.components.Transform;
import com.perfectplay.org.tween.accessors.TransformAccessor;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class TweenRegister{
	private static HashMap<String, Tween> tweens = new HashMap<String, Tween>();
	private static TweenManager manager = new TweenManager();
	private static boolean hasBeenRegistered = false;
	
	private static void registerAccessors(){
		Tween.registerAccessor(Transform.class, new TransformAccessor());
		hasBeenRegistered = true;
	}
	
	public static void register(String tag, Tween tween){
		tweens.put(tag, tween);
	}
	
	public static Tween get(String tag){
		return tweens.get(tag);
	}
	
	public static TweenManager manager(){
		if(!hasBeenRegistered)
			registerAccessors();
		return manager;
	}
	public static void update(float dt){
		manager().update(dt);
	}
	
	public static void start(String tag){
		get(tag).start(manager);
	}
	
	public static void start(Tween tween){
		tween.start(manager);
	}
	
	public static void start(Timeline timeline){
		timeline.start(manager);
	}
}
