package com.perfectplay.org.utils;

import java.util.PriorityQueue;
import java.util.Queue;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class BodyRemover {
	private static Queue<Body> bodiesScheduledForRemoval;
	private static BodyRemover instance = new BodyRemover();
	private static World world;
	
	private BodyRemover(){
		bodiesScheduledForRemoval = new PriorityQueue<Body>();
	}
	public void setWorld(World world){
		BodyRemover.world = world;
	}
	public void scheduleBodyForRemoval(Body body){
		bodiesScheduledForRemoval.add(body);
	}
	public static BodyRemover getInstance(){
		return instance;
	}
	public void removeBodies(){
		for(int i = 0; i < bodiesScheduledForRemoval.size(); i++){
			Body body = bodiesScheduledForRemoval.remove();
			world.destroyBody(body);
			body.setUserData(null);
            body = null;
		}
	}
}
