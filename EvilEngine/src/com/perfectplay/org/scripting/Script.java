package com.perfectplay.org.scripting;

import java.util.HashSet;

import com.artemis.Entity;
import com.perfectplay.org.level.Level;
import com.perfectplay.org.utils.ComponentBag;

public abstract class Script {
	protected Entity entity;
	protected Level world;
	protected ComponentBag components;
	private HashSet<Entity> entities = new HashSet<Entity>();
	
	protected Script() {}
	
	void setEntity(Entity entity, ComponentBag components){
		this.entity = entity;
		this.world = (Level) entity.getWorld();	
		this.components = components;
		//if this entity hasn't been registered, register it and call initialize.
		if(!this.entities.contains(entity)){
			this.entities.add(entity);
			initialize();
		}
	}
	
	public abstract void initialize();
	
	public abstract void onUpdate();

}
