package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public interface TransformDelegate extends Delegate{
	
	public abstract void onMove(Entity entity, Transform transform);
	public abstract void onRotate(Entity entity, Transform transform);
	
}
