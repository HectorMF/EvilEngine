package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.components.Tweens;
import com.perfectplay.org.tween.accessors.TransformAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;

public class TweenSystem extends EntitySystem{
	@Mapper
	ComponentMapper<Tweens> tweenComponent;

	@SuppressWarnings("unchecked")
	public TweenSystem() {
		super(Aspect.getAspectForAll(Tweens.class));
		Tween.registerAccessor(Transform.class, new TransformAccessor());
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(int i = 0; i < entities.size(); i++){
			for(BaseTween<?> tween : tweenComponent.get(entities.get(i)).getActiveTweens()){
				tween.update(world.getDelta());
			}
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
