package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Transform;

public abstract class PhysicsDelegate implements Delegate{

	public abstract void onCollide(Entity entity, RigidBody body);
	
}
