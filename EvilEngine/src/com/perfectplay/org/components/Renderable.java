package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;

public class Renderable extends Component {

	private ISprite sprite;
	// offset from x and y spatial coordinate
	private Vector2 offset;
	// coordinate used as the center of rotation
	private Vector2 origin;
	// transforms across the x and y axis
	private boolean horizontalFlip;
	private boolean verticalFlip;
	private Color color;
	private float alpha;

	private int layerID = -1;
	
	public Renderable(ISprite sprite) {
		this(sprite, Vector2.Zero, Vector2.Zero, Color.WHITE, 1f);
	}

	public Renderable(ISprite sprite, Vector2 offset, Vector2 origin,
			Color color, float alpha) {
		this.sprite = sprite;
		this.offset = offset;
		this.color = color;
		this.alpha = alpha;
		this.origin = origin;
		this.horizontalFlip = false;
		this.verticalFlip = false;
	}
	
	public void setLayer(int id){
		this.layerID = id;
	}
	
	public int getLayer(){
		return layerID;
	}

	public ISprite getSprite() {
		return sprite;
	}

	public void setSprite(ISprite sprite) {
		this.sprite = sprite;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	public boolean getHorizontalFlip() {
		return horizontalFlip;
	}

	public void setHorizontalFlip(boolean flip) {
		this.horizontalFlip = flip;
	}

	public boolean getVerticalFlip() {
		return verticalFlip;
	}

	public void setVerticalFlip(boolean flip) {
		this.verticalFlip = flip;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Renderable))
			return false;
		return this == ((Renderable) obj);
	}

}
