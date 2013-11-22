package com.perfectplay.org.scripts;

import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.delegates.CollisionDelegate;
import com.perfectplay.org.scripting.delegates.WorldDelegate;

public class TestScript2 extends Script implements WorldDelegate{

	@Override
	public void onUpdate() {
	//	System.out.println("test");// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		System.out.println("enablt");
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		System.out.println("dis");
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		System.out.println("remove 2");
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub
		System.out.println("ADDED 2");
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
