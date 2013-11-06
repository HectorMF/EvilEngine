package com.perfectplay.org.level;

import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.utils.Spatial;

public class DepthSortedSpriteLayer extends SpriteLayer {

	public DepthSortedSpriteLayer(float speedX, float speedY) {
		super(speedX, speedY);
	}

	public DepthSortedSpriteLayer() {
		super();
	}

	@Override
	public void render(SpriteBatch batch) {
		Renderable r;
		Spatial s;

		for (LayerNode rn : nodes) {
			r = rn.getRenderableComponent();
			s = rn.getSpatialComponent().getSpatial();
			
			r.getSprite().draw(batch, s.getPosition().x,
					s.getPosition().y, r.getOrigin().x, r.getOrigin().y,
					s.getWidth(), s.getHeight(), 1f, 1f, s.getRotation(),
					r.getHorizontalFlip(), r.getVerticalFlip(), r.getColor(),
					r.getAlpha());
		}
	}

	@Override
	public void update(float dt) {
		Collections.sort(nodes);
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).getRenderableComponent().getSprite().update(dt);
		}
	}
}
