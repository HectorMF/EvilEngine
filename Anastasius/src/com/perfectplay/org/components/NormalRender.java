package com.perfectplay.org.components;

import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;

public class NormalRender extends SpriteRender {

	public NormalRender(ISprite sprite) {
		super(sprite);
	}
	
	public NormalRender(ISprite sprite, Vector2 offset) {
		super(sprite, offset);
	}
	
}