package com.perfectplay.org.events;


public class CollisionEvent extends Event{
	private boolean onCollide = true;
	
	public boolean occursOnCollide(){
		return onCollide;
	}
	public void Fire(){
		System.out.println("collision occured");
	}
}
