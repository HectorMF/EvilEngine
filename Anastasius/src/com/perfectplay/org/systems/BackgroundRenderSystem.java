package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.BackgroundRender;
import com.perfectplay.org.components.Transform;

public class BackgroundRenderSystem extends EntityProcessingSystem{

	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<BackgroundRender> sprites;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public BackgroundRenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(Transform.class, BackgroundRender.class));
		this.batch = batch;
	}

	@Override
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		BackgroundRender s = sprites.get(e);
		
		s.getSprite().update(world.delta*1000);

		s.getSprite().draw(batch, t.getScreenX() + s.getOffset().x, t.getScreenY() + s.getOffset().y, t.getOriginX(),t.getOriginY(), t.getWidth(), 
							t.getHeight(), t.getScaleX(), t.getScaleY(), t.getRotation(), t.getHorizontalFlip(), t.getVerticalFlip());
	}
}
