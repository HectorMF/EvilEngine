package com.perfectplay.org.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;

public abstract class SpriteLayer {
	private float parallaxSpeedX;
	
	private float parallaxSpeedY;
	
	private boolean enabled;
	
	private int id;

	public SpriteLayer() {
		this.id = 0;
		parallaxSpeedX = 1f;
		parallaxSpeedY = 1f;
	}

	public SpriteLayer(int id, float parallaxSpeedX, float parallaxSpeedY) {
		this.id = id;
		this.parallaxSpeedX = parallaxSpeedX;
		this.parallaxSpeedY = parallaxSpeedY;
	}

	public void setID(int id){
		this.id = id;
	}

	public abstract void add(Renderable renderable,
			SpatialComponent spatialComponent);

	public abstract void remove(Renderable renderable,
			SpatialComponent spatialComponent);

	public abstract void render(SpriteBatch batch);

	public abstract void update(float dt);

	public boolean isEnabled() {
		return enabled;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public int getID() {
		return id;
	}
	
	public float getParallaxSpeedX(){
		return parallaxSpeedX;
	}
	
	public float getParallaxSpeedY(){
		return parallaxSpeedY;
	}

	public void setParallaxSpeedX(float speedX){
		this.parallaxSpeedX = speedX;
	}
	
	public void setParallaxSpeedY(float speedY){
		this.parallaxSpeedY = speedY;
	}
}
