package com.perfectplay.org.level;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class SpriteLayer {
	private float parallaxSpeedX;
	private float parallaxSpeedY;
	
	private boolean enabled;
	private static int ID = 0;
	private int uID;
	
	public SpriteLayer() {
		this(1f,1f);
	}

	public SpriteLayer(float parallaxSpeedX, float parallaxSpeedY) {
		this.parallaxSpeedX = parallaxSpeedX;
		this.parallaxSpeedY = parallaxSpeedY;
		uID = ID; 
		ID++;
	}
	
	public abstract void add(Entity e);

	public abstract void remove(Entity e);

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
	
	public int getID(){
		return uID;
	}
	
	public void setID(int id){
		this.uID = id;
	}
	
	
}
