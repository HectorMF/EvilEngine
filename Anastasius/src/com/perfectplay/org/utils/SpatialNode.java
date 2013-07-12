package com.perfectplay.org.utils;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public class SpatialNode {
	private Entity entity;
	private Transform transform;
	private boolean isActive;
	
	public SpatialNode(Entity entity, Transform transform){
		this.entity = entity;
		this.transform = transform;
	}
	
	public Entity getEntity(){
		return entity;
	}
	
	public Transform getTransform(){
		return transform;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean active){
		this.isActive = active;
	}
	
	public boolean isEnabled(){
		return entity.isEnabled();
	}
	
	public void disable(){
		entity.disable();
	}
	
	public void enable(){
		entity.enable();
	}
}
