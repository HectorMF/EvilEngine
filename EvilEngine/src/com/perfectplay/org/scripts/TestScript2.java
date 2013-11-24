package com.perfectplay.org.scripts;

import com.badlogic.gdx.math.Vector3;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.delegates.WorldDelegate;

public class TestScript2 extends Script implements WorldDelegate{
	Vector3 position = null;
	@Override
	public void initialize() {
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		Vector3 temp = components.get(Transform.class).getPosition();
		System.out.println(temp);
		/*if(position == null) position = temp;
		if(temp != position){
			System.out.println("MOVED FROM: " + position + " TO: " + temp);
			
		}
		position = temp;*/
		
	}


}
