package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.ISprite;
import com.perfectplay.org.level.SpriteLayer;

public class Renderable extends Component {

	private ISprite sprite;
	private float zIndex;
	// offset from x and y spatial coordinate
	private Vector2 offset;
	// coordinate used as the center of rotation
	private Vector2 origin;
	// transforms across the x and y axis
	private boolean horizontalFlip;
	private boolean verticalFlip;
	
	private float scaleX = 1,scaleY = 1;
	private float width = -1, height = -1;
	
	private Color color;
	private float alpha;

	private int layerID = -1;
	
	public Renderable(ISprite sprite) {
		this(sprite, Vector2.Zero, Vector2.Zero, Color.WHITE, 1f, 1f,1f, null);
	}
	public Renderable(ISprite sprite, SpriteLayer layer) {
		this(sprite, Vector2.Zero, Vector2.Zero, Color.WHITE, 1f, 1f, 1f, layer);
	}
	
	public Renderable(ISprite sprite, Vector2 offset, Vector2 origin, Color color, float alpha){
		this(sprite, offset, origin, color, 1f, 1f, alpha, null);
	}
	
	public Renderable(ISprite sprite, float width, float height, Vector2 origin, Vector2 offset,
						Color color, float alpha, SpriteLayer layer) {
		this.zIndex = 1;
		this.height = height;
		this.width = width;
		this.sprite = sprite;
		this.offset = offset;
		this.color = color;
		this.alpha = alpha;
		this.origin = origin;
		this.horizontalFlip = false;
		this.verticalFlip = false;
		if(layer != null)
			this.layerID = layer.getID();
	}
	
	public Renderable(ISprite sprite, Vector2 origin, Vector2 offset,
			Color color, float scaleX, float scaleY, float alpha, SpriteLayer layer) {
			this.zIndex = 1;
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			this.sprite = sprite;
			this.offset = offset;
			this.color = color;
			this.alpha = alpha;
			this.origin = origin;
			this.horizontalFlip = false;
			this.verticalFlip = false;
			if(layer != null)
				this.layerID = layer.getID();
	}
	
	public Renderable setLayer(int id){
		this.layerID = id;
		return this;
	}
	
	public int getLayer(){
		return layerID;
	}

	public ISprite getSprite() {
		return sprite;
	}

	public Renderable setSprite(ISprite sprite) {
		this.sprite = sprite;
		return this;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public Renderable setOffset(Vector2 offset) {
		this.offset = offset;
		return this;
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public Renderable setOrigin(Vector2 origin) {
		this.origin = origin;
		return this;
	}

	public boolean getHorizontalFlip() {
		return horizontalFlip;
	}

	public Renderable setHorizontalFlip(boolean flip) {
		this.horizontalFlip = flip;
		return this;
	}

	public boolean getVerticalFlip() {
		return verticalFlip;
	}

	public Renderable setVerticalFlip(boolean flip) {
		this.verticalFlip = flip;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public Renderable setColor(Color color) {
		this.color = color;
		return this;
	}

	public float getAlpha() {
		return alpha;
	}

	public Renderable setAlpha(float alpha) {
		this.alpha = alpha;
		return this;
	}
	
	public float getWidth(){
		return width;
	}
	
	public Renderable setWidth(float width){
		this.width = width;
		return this;
	}
	
	public float getHeight(){
		return height;
	}
	
	public Renderable setHeight(float height){
		this.height = height;
		return this;
	}
	
	public float getScaleX(){
		return scaleX;
	}
	
	public Renderable setScaleX(float scaleX){
		this.scaleX = scaleX;
		return this;
	}
	
	public float getScaleY(){
		return scaleY;
	}
	
	public Renderable setScaleY(float scaleY){
		this.scaleY = scaleY;
		return this;
	}
	
	public float getZIndex(){
		return zIndex;
	}
	
	public Renderable setZIndex(float index){
		this.zIndex = index;
		return this;
	}
}
