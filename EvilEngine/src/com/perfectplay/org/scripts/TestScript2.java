package com.perfectplay.org.scripts;

import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.delegates.CollisionDelegate;

public class TestScript2 extends Script implements CollisionDelegate {

	@Override
	public void onBeginCollision() {
		// TODO Auto-generated method stub
		System.out.println("Collision 2");
		System.out.println();
		
		
	}

	@Override
	public void onEndCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
