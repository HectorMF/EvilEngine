package com.perfectplay.org.level;

import java.util.ArrayList;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.utils.Spatial;

public class UnsortedSpriteLayer extends SpriteLayer {
	ArrayList<LayerNode> nodes;

	public UnsortedSpriteLayer() {
		super();
		nodes = new ArrayList<LayerNode>();
	}

	public UnsortedSpriteLayer(float parallaxSpeedX, float parallaxSpeedY) {
		super(parallaxSpeedX, parallaxSpeedY);
		nodes = new ArrayList<LayerNode>();
	}

	@Override
	public void render(SpriteBatch batch) {
		Renderable r;
		Spatial s;
		for (LayerNode rn : nodes) {
			r = rn.getRenderableComponent();
			s = rn.getSpatialComponent().getSpatial();
			r.getSprite().draw(batch, s.getScreenPosition().x,
					s.getScreenPosition().y, r.getOrigin().x, r.getOrigin().y,
					s.getWidth(), s.getHeight(), 1f, 1f, s.getRotation(),
					r.getHorizontalFlip(), r.getVerticalFlip(), r.getColor(),
					r.getAlpha());
		}
	}

	@Override
	public void update(float dt) {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).getRenderableComponent().getSprite().update(dt);
		}
	}

	@Override
	public void add(Entity entity) {
		nodes.add(new LayerNode(entity));
	}

	@Override
	public void remove(Entity entity) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getEntity() == entity)
				nodes.remove(i);
		}
	}
}
