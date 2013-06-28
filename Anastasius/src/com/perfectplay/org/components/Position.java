package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class Position extends Component {

	private float x;
	private float y;
	private float z;
	
	public Position() {
	}
	
	public Position(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3 getPosition() {
		return new Vector3(x,y,z);
	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public void addZ(float z) {
		this.z += z;
	}
}
