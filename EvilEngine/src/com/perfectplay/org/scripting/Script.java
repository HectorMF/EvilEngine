package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.artemis.World;
import com.perfectplay.org.utils.ComponentBag;

public abstract class Script {
	protected Entity entity;
	protected World world;
	protected ComponentBag components;
	
	protected Script() {}
	
	protected void setEntity(Entity entity, ComponentBag components){
		this.entity = entity;
		this.world = entity.getWorld();	
		this.components = components;
	}
	
	public abstract void initialize();

}
