package com.perfectplay.org.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.utils.Spatial;

public class UnsortedSpriteLayer extends SpriteLayer {

	public UnsortedSpriteLayer(float speedX, float speedY) {
		super(speedX, speedY);
	}
	
	public UnsortedSpriteLayer() {
		super();
	}

	@Override
	public void render(SpriteBatch batch) {
		Renderable r;
		Spatial s;
		for (LayerNode rn : nodes) {
			r = rn.getRenderableComponent();
			s = rn.getSpatialComponent();
			if(r.getWidth() > 0 && r.getHeight() > 0){
				r.getSprite().draw(batch, s.getPosition().x, s.getPosition().y, r.getWidth()/2,r.getHeight()/2, r.getWidth(), r.getHeight(), r.getScaleX(), r.getScaleY(), s.getRotation(),
						r.getHorizontalFlip(), r.getVerticalFlip(), r.getColor(),
						r.getAlpha());
			}else{
				r.getSprite().draw(batch, s.getPosition().x,
						s.getPosition().y, r.getScaleX(), r.getScaleY(), s.getRotation(),
						r.getHorizontalFlip(), r.getVerticalFlip(), r.getColor(),
						r.getAlpha());
			}
		}
	}

	@Override
	public void update(float dt) {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).getRenderableComponent().getSprite().update(dt);
		}
	}
}
