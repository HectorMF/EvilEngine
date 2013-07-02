package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class Physics extends Component{
	private Body physicsBody;
	private float velocityZ;
	private Vector2 forceZ; //(force,point) respectively
	

	public Physics(Body physicsBody) {
		this(physicsBody, 0f);
	}
	
	public Physics(Body physicsBody, float velocityZ) {
		this.physicsBody = physicsBody;
		this.velocityZ = velocityZ;
		this.forceZ = new Vector2(0f, 0f);
	}

	public Vector3 getVelocity(){
		Vector2 bodyVelocity = physicsBody.getLinearVelocity();
		return new Vector3(bodyVelocity.x, bodyVelocity.y, velocityZ);
	}
	
	public void setVelocity(Vector3 newVelocity){
		physicsBody.setLinearVelocity(newVelocity.x, newVelocity.y);
		velocityZ = newVelocity.z;
	}
	
	public void applyForces(Vector3 force, Vector3 point){
		physicsBody.applyForce(force.x, force.y, point.x, point.y);
		forceZ = new Vector2(force.z, point.z); //Needs to be changed to either be a list of forces
		//or automatically merge the new force with the current force.
	}
	
	public Vector2 getForceZ(){
		return forceZ;
	}
	
	public Body getBody() {
		return physicsBody;
	}
	
}
