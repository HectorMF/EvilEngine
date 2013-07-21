package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public abstract class TransformDelegate extends Delegate<Transform>{
	
	public TransformDelegate() {
		super(Transform.class);
	}
	
	public abstract void onMove(Entity entity, Transform transform);
	public abstract void onRotate(Entity entity, Transform transform);
	
}
