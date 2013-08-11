package com.perfectplay.org.scripting.delegates;

import com.artemis.Entity;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.scripting.Delegate;

public interface SpatialDelegate extends Delegate {

	/*
	 * Called when the entity is moved. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onMove(Entity entity, SpatialComponent spatialComponent);

	/*
	 * Called when the entity rotates. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onRotate(Entity entity, SpatialComponent spatialComponent);

	/*
	 * Called when the entity doesn't move. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onStay(Entity entity, SpatialComponent spatialComponent);

}
