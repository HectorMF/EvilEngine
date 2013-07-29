package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public interface TransformDelegate extends Delegate{
	
	/*
	 * Called when the entity is moved. Passes in the transform as well as the 
	 * entity object. 
	 */
	public abstract void onMove(Entity entity, Transform transform);
	
	/*
	 * Called when the entity rotates. Passes in the transform as well as the 
	 * entity object. 
	 */
	public abstract void onRotate(Entity entity, Transform transform);
	
	/*
	 * Called when the entity doesn't move. Passes in the transform as well as the 
	 * entity object. 
	 */
	public abstract void onStay(Entity entity, Transform transform);
	
}
