package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public class TestScript extends Script implements TransformDelegate{
	private boolean isMoving = false;
	@Override
	public void onMove(Entity entity, Transform transform) {
		System.out.println("Script " + this.getID() + ": onMove call to entity " + entity.getId() +". Position: " + transform.getX() +" : " + transform.getY());
		//transform.setWidth((float)(transform.getWidth()+10*Gdx.graphics.getDeltaTime()));
		isMoving = true;
	}

	@Override
	public void onRotate(Entity entity, Transform transform) {
		System.out.println("Entity: " + entity.getId() +" has transformed!");
		
	}

	@Override
	public void onStay(Entity entity, Transform transform) {
		//testing onStop call functionality
		if(isMoving){
			isMoving = false;
			System.out.println("Entity: " + entity.getId() +" just stopped moved.");
			
		}
		
		// TODO Auto-generated method stub
		//System.out.println("Entity: " + entity.getId() +" hasn't moved.");
		
	}
	

}
