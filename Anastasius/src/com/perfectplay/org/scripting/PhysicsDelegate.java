package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Transform;

public abstract class PhysicsDelegate extends Delegate<RigidBody>{
	
	public PhysicsDelegate() {
		super(RigidBody.class);
	}
		
	public abstract void onCollide(Entity entity, RigidBody body);
	public abstract void onRotate(Entity entity, Transform transform);
}
