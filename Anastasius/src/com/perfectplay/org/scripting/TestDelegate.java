package com.perfectplay.org.scripting;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public class TestDelegate extends TransformDelegate{

	@Override
	public void onMove(Entity entity, Transform transform) {
		System.out.println("Entity: " + entity.getId() +" Position: " + transform.getX() +" : " + transform.getY());
		//transform.setWidth((float)(transform.getWidth()+10*Gdx.graphics.getDeltaTime()));
	}

	@Override
	public void onRotate(Entity entity, Transform transform) {
		System.out.println("Entity: " + entity.getId() +" has transformed!");
		
	}
	

}
