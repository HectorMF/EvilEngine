package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.EventRegion;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.listeners.RegionContactListener;

public class RegionSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Transform> spatialComponents;
	@Mapper
	ComponentMapper<EventRegion> regions;

	RegionContactListener contactListener;

	@SuppressWarnings("unchecked")
	public RegionSystem() {
		super(Aspect.getAspectForAll(Transform.class, EventRegion.class));
		contactListener = new RegionContactListener();
	//	PhysicsSystem.getWorld().setContactListener(contactListener);
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		// activate the body
		regions.get(e).getBody().setActive(true);
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
		// deactivate the body
		regions.get(e).getBody().setActive(false);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> arg0) {
	}

}