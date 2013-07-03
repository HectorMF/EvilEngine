package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;

public class SpriteRender extends Component {
	private ISprite sprite;
	private Vector2 offset;
	public SpriteRender(ISprite sprite) {
		this(sprite, new Vector2(0,0));
	}
	public SpriteRender(ISprite sprite, Vector2 offset){
		this.sprite = sprite;
		this.offset = offset;
	}
	
	public ISprite getSprite() {
		return sprite;
	}
	
	public Vector2 getOffset(){
		return offset;
	}
}
