package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.BackgroundRender;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.utils.Spatial;

public class BackgroundRenderSystem extends EntityProcessingSystem{

	@Mapper ComponentMapper<SpatialComponent> spatials;
	@Mapper ComponentMapper<BackgroundRender> sprites;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public BackgroundRenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(SpatialComponent.class, BackgroundRender.class));
		this.batch = batch;
	}

	@Override
	protected void process(Entity e) {
		Spatial t = spatials.get(e).getSpatial();
		BackgroundRender s = sprites.get(e);
		
		s.getSprite().update(world.delta*1000);

		s.getSprite().draw(batch, t.getScreenPosition().x + s.getOffset().x, t.getScreenPosition().y + s.getOffset().y, s.getOriginX(),s.getOriginY(), t.getWidth(), 
							t.getHeight(), s.getScaleX(), s.getScaleY(), t.getRotation(), s.getHorizontalFlip(), s.getVerticalFlip());
	}
}
