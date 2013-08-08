package com.perfectplay.org.graphics;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.utils.Spatial;

public class UnsortedSpriteLayer extends SpriteLayer {
	ArrayList<RenderableNode> nodes;

	public UnsortedSpriteLayer() {
		super();
		nodes = new ArrayList<RenderableNode>();
	}

	public UnsortedSpriteLayer(int id) {
		super(id);
		nodes = new ArrayList<RenderableNode>();
	}

	@Override
	public void render(SpriteBatch batch) {
		Renderable r;
		Spatial s;
		for (RenderableNode rn : nodes) {
			r = rn.getRenderable();
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
			nodes.get(i).getRenderable().getSprite().update(dt * 1000);
		}
	}

	@Override
	public void add(Renderable renderable, SpatialComponent spatialComponent) {
		nodes.add(new RenderableNode(renderable, spatialComponent));
	}

	@Override
	public void remove(Renderable renderable, SpatialComponent spatialComponent) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getRenderable().equals(renderable))
				nodes.remove(i);
		}
	}
}
