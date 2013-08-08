package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.scripting.delegates.SpatialDelegate;

public class TestScript extends Script implements SpatialDelegate {
	private boolean isMoving = false;

	@Override
	public void onMove(Entity entity, SpatialComponent spatial) {
		System.out.println("Script " + this.getID()
				+ ": onMove call to entity " + entity.getId() + ". Position: "
				+ spatial.getSpatial().getX() + " : "
				+ spatial.getSpatial().getY());
		// transform.setWidth((float)(transform.getWidth()+10*Gdx.graphics.getDeltaTime()));
		isMoving = true;
	}

	@Override
	public void onRotate(Entity entity, SpatialComponent spatial) {
		System.out.println("Entity: " + entity.getId() + " has transformed!");

	}

	@Override
	public void onStay(Entity entity, SpatialComponent spatial) {
		// testing onStop call functionality
		if (isMoving) {
			isMoving = false;
			System.out.println("Entity: " + entity.getId()
					+ " just stopped moved.");

		}

		// TODO Auto-generated method stub
		// System.out.println("Entity: " + entity.getId() +" hasn't moved.");

	}

}
