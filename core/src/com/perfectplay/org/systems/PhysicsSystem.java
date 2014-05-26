package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.listeners.ZContactFilter;
import com.perfectplay.org.utils.RigidBodySpatial;
import com.perfectplay.org.utils.Spatial;

public class PhysicsSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Transform> transforms;
	@Mapper
	ComponentMapper<RigidBody> physics;

	protected static World world;

	@SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, RigidBody.class));
		PhysicsSystem.world = world;
		//PhysicsSystem.world.setContactFilter(new ZContactFilter());
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		Spatial spatial = transforms.get(e).getSpatial();
		//check to see if the transform has a rigid body spatial
		if(!(spatial instanceof RigidBodySpatial)){
			//No RigidBody-backed spatial.
			RigidBodySpatial newSpatial = new RigidBodySpatial(physics.get(e).getBody());
			newSpatial.setSpatial(spatial);
			transforms.get(e).setSpatial(newSpatial);
		}
		physics.get(e).getBody().setActive(true);
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
		physics.get(e).getBody().setActive(false);
	}

	public static World getWorld() {
		return world;
	}

	@Override
	protected final void processEntities(ImmutableBag<Entity> entities) {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	public static Body createBody(BodyDef definition) {
		return world.createBody(definition);
	}
}