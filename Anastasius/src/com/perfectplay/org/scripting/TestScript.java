package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public class TestScript extends Script implements TransformDelegate{

	@Override
	public void onMove(Entity entity, Transform transform) {
		System.out.println("Script " + this.getID() + ": onMove applied to entity " + entity.getId() +". Position: " + transform.getX() +" : " + transform.getY());
		//transform.setWidth((float)(transform.getWidth()+10*Gdx.graphics.getDeltaTime()));
	}

	@Override
	public void onRotate(Entity entity, Transform transform) {
		System.out.println("Entity: " + entity.getId() +" has transformed!");
		
	}
	

}
