package com.perfectplay.org.components;

import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;

public class BackgroundRender extends Renderable{
	public BackgroundRender(ISprite sprite) {
		super(sprite,null);
	}//
	
	public BackgroundRender(ISprite sprite, Vector2 offset) {
		super(sprite, offset,null);
	}
}
