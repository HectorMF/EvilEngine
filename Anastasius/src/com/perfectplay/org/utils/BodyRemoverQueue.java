package com.perfectplay.org.utils;

import java.util.PriorityQueue;
import java.util.Queue;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class BodyRemoverQueue {
	private static Queue<Body> bodiesScheduledForRemoval;
	private static BodyRemoverQueue instance = new BodyRemoverQueue();
	private static World world;

	private BodyRemoverQueue() {
		bodiesScheduledForRemoval = new PriorityQueue<Body>();
	}

	public void setWorld(World world) {
		BodyRemoverQueue.world = world;
	}

	public void scheduleBodyForRemoval(Body body) {
		bodiesScheduledForRemoval.add(body);
	}

	public static BodyRemoverQueue getInstance() {
		return instance;
	}

	public void removeBodies() {
		for (int i = 0; i < bodiesScheduledForRemoval.size(); i++) {
			Body body = bodiesScheduledForRemoval.remove();
			world.destroyBody(body);
			body.setUserData(null);
			body = null;
		}
	}
}
