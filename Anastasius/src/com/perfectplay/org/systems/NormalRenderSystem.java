package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.NormalRender;
import com.perfectplay.org.components.Transform;

public class NormalRenderSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<NormalRender> sprites;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public NormalRenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(Transform.class, NormalRender.class));
		this.batch = batch;
	}

	@Override
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		NormalRender s = sprites.get(e);
		s.getSprite().update(world.delta*1000);

		s.getSprite().draw(batch, t.getX(), t.getY(), t.getOriginX(),t.getOriginY(), t.getWidth(), 
							t.getHeight(), t.getScaleX(), t.getScaleY(), t.getRotation(), t.getHorizontalFlip(), t.getVerticalFlip());
	}
}
