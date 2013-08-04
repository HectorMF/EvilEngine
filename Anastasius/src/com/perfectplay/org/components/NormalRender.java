package com.perfectplay.org.components;

import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;

public class NormalRender extends Renderable {

	public NormalRender(ISprite sprite) {
		super(sprite,null);
	}
	
	public NormalRender(ISprite sprite, Vector2 offset) {
		super(sprite, offset,null);
	}
	
}