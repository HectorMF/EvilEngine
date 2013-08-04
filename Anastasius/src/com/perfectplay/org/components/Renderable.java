package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;
import com.perfectplay.org.graphics.SpriteLayer;

public class Renderable extends Component {
	private static int UID = 1;
	
	private int id;
	
	private ISprite sprite;
	
	private Vector2 offset;
	
	private boolean horizontalFlip;
	private boolean verticalFlip;
	
	private float scaleX = 1;
	private float scaleY = 1;
	
	private float originX;
	private float originY;
	
	private SpriteLayer layer;
	
	public Renderable(ISprite sprite, SpriteLayer layer) {
		this(sprite, new Vector2(0,0), layer);
	}
	
	public Renderable(ISprite sprite, Vector2 offset, SpriteLayer layer){
		this.sprite = sprite;
		this.offset = offset;
		this.layer = layer;
		this.id = UID;
		UID++;
	}
	
	public SpriteLayer getSpriteLayer(){
		return layer;
	}
	
	public ISprite getSprite() {
		return sprite;
	}
	
	public Vector2 getOffset(){
		return offset;
	}
	
	public boolean getHorizontalFlip(){
		return horizontalFlip;
	}
	
	public boolean getVerticalFlip(){
		return verticalFlip;
	}
	
	public float getOriginX(){
		return originX;
	}
	
	public float getScaleX(){
		return scaleX;
	}
	
	public float getScaleY(){
		return scaleY;
	}
	public float getOriginY(){
		return originY;
	}
	
	public int getID(){
		return id;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
	    	return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Renderable))
	    	return false;
	    return getID() == ((Renderable) obj).getID();
	}
	
}
