package com.perfectplay.org.events;

import com.artemis.Entity;


public class CollisionEvent extends Event{
	private boolean onCollide = true;
	
	public boolean occursOnCollide(){
		return onCollide;
	}
	public void beginCollision(Entity e1, Entity e2){
		System.out.println(e1 + " has detected " + e2 + " in range. ");
	}
	public void endCollision(Entity e1, Entity e2){
		System.out.println(e1 + " is no longer in range of "+ e2 + ".");
	}
}
