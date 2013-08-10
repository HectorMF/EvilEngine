package com.perfectplay.org.scripting.delegates;

import com.artemis.Entity;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.scripting.Delegate;

public abstract class PhysicsDelegate implements Delegate {

	public abstract void onCollide(Entity entity, RigidBody body);

}
