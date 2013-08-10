package com.perfectplay.org.utils;

import com.artemis.Entity;

public class SpatialNode {
	private Entity entity;
	private Spatial spatial;
	private boolean isActive;

	public SpatialNode(Entity entity, Spatial spatial) {
		this.entity = entity;
		this.spatial = spatial;
	}

	public Entity getEntity() {
		return entity;
	}

	public Spatial getSpatial() {
		return spatial;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public boolean isEnabled() {
		return entity.isEnabled();
	}

	public void disable() {
		entity.disable();
	}

	public void enable() {
		entity.enable();
	}
}
