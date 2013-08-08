package com.perfectplay.org.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;

public abstract class SpriteLayer{
	private static int UID = 0;
	
	private int id;
	
	public SpriteLayer(){
		this.id = UID;
		UID++;
	}
	
	public SpriteLayer(int id){
		this.id = id;
	}
	
	private boolean enabled;
	
	public abstract void add(Renderable renderable, SpatialComponent spatialComponent);
	
	public abstract void remove(Renderable renderable, SpatialComponent spatialComponent);
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void update(float dt);
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void enable(){
		this.enabled = true;
	}
	
	public void disable(){
		this.enabled = false;
	}
	
	public int getID(){
		return id;
	}
}
