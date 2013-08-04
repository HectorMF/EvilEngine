package com.perfectplay.org.graphics;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.utils.Spatial;

public class DepthSortedSpriteLayer extends SpriteLayer{
	ArrayList<RenderableNode> nodes;
	
	public DepthSortedSpriteLayer(){
		nodes = new ArrayList<RenderableNode>();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Renderable r;
		Spatial s;
		for(RenderableNode rn : nodes){
			r = rn.getRenderable();
			s = rn.getSpatialComponent().getSpatial();
			r.getSprite().draw(batch, s.getScreenPosition().x, s.getScreenPosition().y, r.getOriginX(), r.getOriginY(), s.getWidth(), s.getHeight(), r.getScaleX(), r.getScaleY(), s.getRotation(), r.getHorizontalFlip(), r.getVerticalFlip());
		}
	}

	@Override
	public void update(float dt) {
		Collections.sort(nodes);
		for(int i = 0; i < nodes.size(); i++){
			nodes.get(i).getRenderable().getSprite().update(dt * 1000);
		}
	}

	@Override
	public void add(Renderable renderable, SpatialComponent spatialComponent) {
		nodes.add(new RenderableNode(renderable,spatialComponent));
	}

	@Override
	public void remove(Renderable renderable, SpatialComponent spatialComponent) {
		for(int i = 0; i < nodes.size(); i ++){
			if(nodes.get(i).getRenderable().equals(renderable))
				nodes.remove(i);
		}
	}

}
