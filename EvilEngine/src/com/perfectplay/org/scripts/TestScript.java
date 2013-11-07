package com.perfectplay.org.scripts;

import com.artemis.World;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.delegates.CollisionDelegate;
import com.perfectplay.org.scripting.delegates.GeneralDelegate;
import com.perfectplay.org.scripting.delegates.TransformDelegate;

public class TestScript extends Script implements TransformDelegate, GeneralDelegate, CollisionDelegate{

	private boolean isMoving = false;

	@Override
	public void onMove() {
		Transform t = entity.getComponent(Transform.class);
		System.out.println("Script " 
				+ ": onMove call to entity " + entity.getId() + ". Position: "
				+ t.getSpatial().getX() + " : "
				+ t.getSpatial().getY());
		// transform.setWidth((float)(transform.getWidth()+10*Gdx.graphics.getDeltaTime()));
		isMoving = true;
	}

	@Override
	public void onRotate() {
		System.out.println("Entity: " + entity.getId() + " has transformed!");

	}

	@Override
	public void onStay() {
		// testing onStop call functionality
		if (isMoving) {
			isMoving = false;
			System.out.println("Entity: " + entity.getId()
					+ " just stopped moved.");

		}

		// TODO Auto-generated method stub
		//System.out.println("Entity: " + entity.getId() +" hasn't moved.");

	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void onUpdate() {
		//System.out.println(entity.getId() + "UPDATED");
	
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		System.out.println(entity.getId() + " Has been DISABLED");
	}

	@Override
	public void onEnable() {
		System.out.println(entity.getId() + " Has been ENABLED");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeginCollision() {
		System.out.println("collide");
	}

	@Override
	public void onEndCollision() {
		// TODO Auto-generated method stub
		
	}

}
