package com.perfectplay.org.systems;

import java.util.HashMap;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.tween.accessors.TransformAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class TweenSystem extends EntitySystem{
	private static HashMap<String, BaseTween<?>> tweens = new HashMap<String, BaseTween<?>>();
	private static TweenManager manager = new TweenManager();

	public TweenSystem() {
		super(Aspect.getEmpty());
		Tween.registerAccessor(Transform.class, new TransformAccessor());
	}

	public static void register(String tag, BaseTween<?> tween){
		tweens.put(tag, tween);
	}
	
	public static BaseTween<?> get(String tag){
		return tweens.get(tag);
	}
	
	public static void start(String tag){
		get(tag).start(manager);
	}
	
	public static void start(BaseTween<?> tween){
		tween.start(manager);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		manager.update(world.getDelta());
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}