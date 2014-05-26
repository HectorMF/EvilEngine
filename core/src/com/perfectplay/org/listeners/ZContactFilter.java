package com.perfectplay.org.listeners;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.Spatial;

public class ZContactFilter implements ContactFilter {

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		// TODO Auto-generated method stub
		return false;
	}
/*
	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Entity entityA = (Entity) fixtureA.getBody().getUserData();
		Entity entityB = (Entity) fixtureB.getBody().getUserData();

		if (entityA == entityB)
			return false;

		Spatial spatialA = entityA.getComponent(Transform.class);
		Spatial spatialB = entityB.getComponent(Transform.class);

		if (spatialA == null || spatialB == null)
			return false;
		float minA = spatialA.getZ() - spatialA.getDepth();
		float maxA = spatialA.getZ();
		float minB = spatialB.getZ() - spatialB.getDepth();
		float maxB = spatialB.getZ();

		// zs match, this is a collision, filter based on filter data
		if (minA < maxB && minB < maxA) {
			Filter filterA = fixtureA.getFilterData();
			Filter filterB = fixtureB.getFilterData();

			if (filterA.groupIndex == filterB.groupIndex
					&& filterA.groupIndex != 0) {
				return filterA.groupIndex > 0;
			}

			boolean collide = (filterA.maskBits & filterB.categoryBits) != 0
					&& (filterA.categoryBits & filterB.maskBits) != 0;
			return collide;
		}
		return false;
	}*/
}
