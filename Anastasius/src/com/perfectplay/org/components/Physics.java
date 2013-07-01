package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class Physics extends Component{
	private float xVelocity;
	private float yVelocity;
	private float zVelocity;

	public Physics() {
		this(0f,0f,0f);
	}

	public Physics(float xVelocity, float yVelocity, float zVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.zVelocity = zVelocity;
	}
	
	public Vector3 getVelocity() {
		return new Vector3(xVelocity, yVelocity, zVelocity);
	}
	
	public float getXVelocity() {
		return xVelocity;
	}
	
	public float getYVelocity() {
		return yVelocity;
	}
	
	public float getZVelocity() {
		return zVelocity;
	}
	
	public void setXVelocity(float xVelocity){
		this.xVelocity = xVelocity;
	}
	
	public void setYVelocity(float yVelocity){
		this.yVelocity = yVelocity;
	}
	
	public void setZVelocity(float zVelocity){
		this.zVelocity = zVelocity;
	}
	
	public void setPosition(float xVelocity, float yVelocity, float zVelocity){
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.zVelocity = zVelocity;
	}
	
	public void addXVelocity(float xVelocity){
		this.xVelocity += xVelocity;
	}
	
	public void addYVelocity(float yVelocity){
		this.yVelocity += yVelocity;
	}
	
	public void addZ(float z){
		this.zVelocity += zVelocity;
	}
	
}
