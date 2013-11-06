package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.artemis.World;

public abstract class Script {
	protected Entity entity;
	protected World world;
	
	protected Script() {}
	
	protected void setEntity(Entity entity){
		this.entity = entity;
		this.world = entity.getWorld();
		initialize();
	}
	
	public abstract void initialize();

}
